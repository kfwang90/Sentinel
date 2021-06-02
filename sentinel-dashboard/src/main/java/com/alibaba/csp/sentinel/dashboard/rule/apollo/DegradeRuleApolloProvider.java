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

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 降级规则
 * @link https://github.com/hosaos/apollo-sentinel-dashboard
 */
@Component("degradeRuleApolloProvider")
public class DegradeRuleApolloProvider extends BaseApolloRuleProvider {

    @Override
    public List<DegradeRule> getRules(String appName) throws Exception {
        return super.getRules(appName);
    }

    @Override
    protected String getDataId(String appName) {
        return ApolloConfigUtil.getDegradeDataId(appName);
    }

    @Override
    protected Class getRuleClazz() {
        return DegradeRuleEntity.class;
    }
}
