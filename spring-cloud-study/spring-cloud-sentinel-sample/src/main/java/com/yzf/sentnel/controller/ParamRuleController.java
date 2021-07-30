package com.yzf.sentnel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import java.util.Collections;

/**
 * @author yuanzf
 * @version 1.0
 * @Description 热点数据流控
 * @date 2021/7/23 16:57
 * @className ParamRuleController
 */
@RestController
public class ParamRuleController {

//    private String resourceName="com.gupaoedu.book.sentinel.dynamic.springcloudsentineldynamicrule.ParamRuleController:sayHello(java.lang.String)";

    private String resourceName = "sayHi";

    @PostConstruct
    public void initParamRule() {
        ParamFlowRule rule = new ParamFlowRule(resourceName);
        rule.setParamIdx(0);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);
//        rule.setMaxQueueingTimeMs() // 最长排队等待时长
        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
    }

    @GetMapping("/sayHi")
    public String sayHi(@PathParam("id") String id, @PathParam("name") String name) {
//        注意点
//        热点参数的注意点，参数必须是基本类型或者String
        Entry entry = null;
        try {
            entry = SphU.entry(resourceName, EntryType.IN, 1, id);
            return "access success";
        } catch (BlockException e) {
            e.printStackTrace();
            return "block";
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }

    @SentinelResource
    @GetMapping("/sayHi2")
    public String sayHi2(@PathParam("id") String id) {
        return "sayHi2 access success";
    }
}
