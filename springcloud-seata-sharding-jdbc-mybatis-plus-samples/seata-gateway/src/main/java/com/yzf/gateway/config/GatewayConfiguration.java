/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Configuration
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolvers.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 配置SentinelGatewayFilter
     *
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter(){
        return new SentinelGatewayFilter();
    }

    /**
     * 配置SentinelGatewayBlockExceptionHandler，限流后异常处理
     *   自定义限流异常页面
     * @return
     */
    @Bean
    @Order(-1)
    public GpSentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler2(){
        return new GpSentinelGatewayBlockExceptionHandler(viewResolvers,serverCodecConfigurer);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    // 自定义限流异常页面
    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                Map result = new HashMap();
                result.put("Suc","false");
                result.put("ErrCode",0);
                result.put("Info","接口被限流了");
                return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(result));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

    @PostConstruct
    public void doInit(){
        initGatewayRules();
        initCustomizedApis();
    }

    private void initGatewayRules(){
        Set<GatewayFlowRule> rules=new HashSet<>();
        GatewayFlowRule gatewayFlowRule=new GatewayFlowRule("order_service").setCount(1).setIntervalSec(1);

        GatewayFlowRule customerFlowRule=new GatewayFlowRule("my_customized_api").
                setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME).
                setCount(1).setIntervalSec(1);
        rules.add(gatewayFlowRule);
        rules.add(customerFlowRule);
        GatewayRuleManager.loadRules(rules);
    }

    private void initCustomizedApis(){
        Set<ApiDefinition> definitions=new HashSet<>();
        ApiDefinition apiDefinition=new ApiDefinition("my_customized_api");
        apiDefinition.setPredicateItems(new HashSet<ApiPredicateItem>(){{
            add(new ApiPathPredicateItem().setPattern("/foo/**"));
            add(new ApiPathPredicateItem().setPattern("/gateway/**").setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
        }});
        definitions.add(apiDefinition);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }
}
