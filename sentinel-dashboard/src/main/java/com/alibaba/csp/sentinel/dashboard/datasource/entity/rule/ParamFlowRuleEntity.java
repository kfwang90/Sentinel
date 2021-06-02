/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.datasource.entity.rule;

import com.alibaba.csp.sentinel.slots.block.Rule;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowClusterConfig;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

/**
 * @author Eric Zhao
 * @since 0.2.1
 */
public class ParamFlowRuleEntity implements RuleEntity {

    private Long id;
    private String app;
    private String ip;
    private Integer port;
    private String limitApp;
    private String resource;
    private Date gmtCreate;
    private Date gmtModified;

    /**
     * The threshold type of flow control (0: thread count, 1: QPS).
     */
    private int grade = RuleConstant.FLOW_GRADE_QPS;

    /**
     * Parameter index.
     */
    private Integer paramIdx;

    /**
     * The threshold count.
     */
    private double count;

    /**
     * Traffic shaping behavior (since 1.6.0).
     */
    private int controlBehavior = RuleConstant.CONTROL_BEHAVIOR_DEFAULT;

    private int maxQueueingTimeMs = 0;
    private int burstCount = 0;
    private long durationInSec = 1;

    /**
     * Original exclusion items of parameters.
     */
    private List<ParamFlowItem> paramFlowItemList = new ArrayList<ParamFlowItem>();

    /**
     * Indicating whether the rule is for cluster mode.
     */
    private boolean clusterMode = false;
    /**
     * Cluster mode specific config for parameter flow rule.
     */
    private ParamFlowClusterConfig clusterConfig;

    public static ParamFlowRuleEntity fromParamFlowRule(String app, String ip, Integer port, ParamFlowRule rule) {
        ParamFlowRuleEntity entity = new ParamFlowRuleEntity();
        entity.setApp(app);
        entity.setIp(ip);
        entity.setPort(port);
        entity.setLimitApp(rule.getLimitApp());
        entity.setResource(rule.getResource());
        entity.setGrade(rule.getGrade());
        entity.setParamIdx(rule.getParamIdx());
        entity.setCount(rule.getCount());
        entity.setControlBehavior(rule.getControlBehavior());
        entity.setMaxQueueingTimeMs(rule.getMaxQueueingTimeMs());
        entity.setBurstCount(rule.getBurstCount());
        entity.setDurationInSec(rule.getDurationInSec());
        entity.setParamFlowItemList(rule.getParamFlowItemList());
        entity.setClusterMode(rule.isClusterMode());
        entity.setClusterConfig(rule.getClusterConfig());
        return entity;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @Override
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public Date getGmtCreate() {
        return gmtCreate;
    }

    @Override
    public ParamFlowRule toRule() {
        ParamFlowRule paramFlowRule = new ParamFlowRule();
        paramFlowRule.setResource(this.resource);
        paramFlowRule.setLimitApp(this.limitApp);
        paramFlowRule.setGrade(this.grade);
        paramFlowRule.setParamIdx(this.paramIdx);
        paramFlowRule.setCount(this.count);
        paramFlowRule.setControlBehavior(this.controlBehavior);
        paramFlowRule.setMaxQueueingTimeMs(this.maxQueueingTimeMs);
        paramFlowRule.setBurstCount(this.burstCount);
        paramFlowRule.setDurationInSec(this.durationInSec);
        paramFlowRule.setParamFlowItemList(this.paramFlowItemList);
        paramFlowRule.setClusterMode(this.clusterMode);
        paramFlowRule.setClusterConfig(this.clusterConfig);
        return paramFlowRule;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Integer getParamIdx() {
        return paramIdx;
    }

    public void setParamIdx(Integer paramIdx) {
        this.paramIdx = paramIdx;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public int getControlBehavior() {
        return controlBehavior;
    }

    public void setControlBehavior(int controlBehavior) {
        this.controlBehavior = controlBehavior;
    }

    public int getMaxQueueingTimeMs() {
        return maxQueueingTimeMs;
    }

    public void setMaxQueueingTimeMs(int maxQueueingTimeMs) {
        this.maxQueueingTimeMs = maxQueueingTimeMs;
    }

    public int getBurstCount() {
        return burstCount;
    }

    public void setBurstCount(int burstCount) {
        this.burstCount = burstCount;
    }

    public long getDurationInSec() {
        return durationInSec;
    }

    public void setDurationInSec(long durationInSec) {
        this.durationInSec = durationInSec;
    }

    public List<ParamFlowItem> getParamFlowItemList() {
        return paramFlowItemList;
    }

    public void setParamFlowItemList(List<ParamFlowItem> paramFlowItemList) {
        this.paramFlowItemList = paramFlowItemList;
    }

    public boolean isClusterMode() {
        return clusterMode;
    }

    public void setClusterMode(boolean clusterMode) {
        this.clusterMode = clusterMode;
    }

    public ParamFlowClusterConfig getClusterConfig() {
        return clusterConfig;
    }

    public void setClusterConfig(ParamFlowClusterConfig clusterConfig) {
        this.clusterConfig = clusterConfig;
    }

    public String getLimitApp() {
        return limitApp;
    }

    public void setLimitApp(String limitApp) {
        this.limitApp = limitApp;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
