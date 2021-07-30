/*
 * author: wangjian (jonath@163.com)
 * Copyright 2019
 */
package com.yzf.sentnel.controller.rule;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * author: wangjian (jonath@163.com)
 * date: 2021/7/23 11:47
 */
public class FlowRuleInitFunc implements InitFunc {
    @Override
    public void init() throws Exception {
        List<FlowRule> rules=new ArrayList<>();
        FlowRule rule=new FlowRule();
        // 限流阈值
        rule.setCount(1);
        // 资源名，即限流规则的作用对象
        rule.setResource("hello");
        // 调用关系限流策略
        rule.setStrategy(RuleConstant.STRATEGY_CHAIN);
        //  限流阈值类型（QPS 或并发线程数）
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 流控针对的调用来源，若为 default 则不区分调用来源
        rule.setLimitApp("default");
        // 流量控制效果（直接拒绝、Warm Up、匀速排队）
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
