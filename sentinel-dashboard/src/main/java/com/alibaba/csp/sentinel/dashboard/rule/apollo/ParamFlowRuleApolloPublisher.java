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
package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 热点key限流规则
 * @link https://github.com/hosaos/apollo-sentinel-dashboard
 */
@Component("paramFlowRuleApolloPublisher")
public class ParamFlowRuleApolloPublisher extends BaseApolloRulePublisher {

    @Override
    public void publish(String appName, Object rules) throws Exception {
        List<ParamFlowRuleEntity> paramFlowRuleEntityList = (List<ParamFlowRuleEntity>) rules;
        //去掉一些无用属性
        for (ParamFlowRuleEntity paramFlowRuleEntity : paramFlowRuleEntityList) {
            paramFlowRuleEntity.setGmtCreate(null);
            paramFlowRuleEntity.setGmtModified(null);
            paramFlowRuleEntity.setIp(null);
            paramFlowRuleEntity.setPort(null);
        }

        super.publish(appName, rules);
    }

    @Override
    protected String getDataId(String appName) {
        return ApolloConfigUtil.getParamFlowDataId(appName);
    }
}
