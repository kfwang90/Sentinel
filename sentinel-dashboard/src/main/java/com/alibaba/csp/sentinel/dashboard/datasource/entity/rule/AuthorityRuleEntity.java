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
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;

import java.util.Date;

/**
 * @author Eric Zhao
 * @since 0.2.1
 */
public class AuthorityRuleEntity implements RuleEntity {

    private Long id;
    private String app;
    private String ip;
    private Integer port;
    private String limitApp;
    private String resource;
    /**
     * Mode: 0 for whitelist; 1 for blacklist.
     */
    private Integer strategy;

    private Date gmtCreate;
    private Date gmtModified;

    public static AuthorityRuleEntity fromAuthorityRule(String app, String ip, Integer port, AuthorityRule rule) {
        AuthorityRuleEntity entity = new AuthorityRuleEntity();
        entity.setApp(app);
        entity.setIp(ip);
        entity.setPort(port);
        entity.setLimitApp(rule.getLimitApp());
        entity.setResource(rule.getResource());
        entity.setStrategy(rule.getStrategy());
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

    @Override
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public AuthorityRule toRule() {
        AuthorityRule authorityRule = new AuthorityRule();
        authorityRule.setResource(this.resource);
        authorityRule.setLimitApp(this.limitApp);
        authorityRule.setStrategy(this.strategy);
        return authorityRule;
    }

    public void setPort(Integer port) {
        this.port = port;
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

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }
}
