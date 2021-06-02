package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenNamespaceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @link https://github.com/hosaos/apollo-sentinel-dashboard
 */
public abstract class BaseApolloRuleProvider implements DynamicRuleProvider {

    @Autowired
    protected ApolloOpenApiClient apolloOpenApiClient;

    @Value("${apollo.env}")
    private String env;

    @Override
    public List getRules(String appName) throws Exception {
        String flowDataId = getDataId(appName);
        String rules = getRulesFromApollo(appName, flowDataId);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return JSON.parseArray(rules, getRuleClazz());
    }

    /**
     * 获取流控规则在apollo中key值
     *
     * @return
     */
    protected abstract String getDataId(String appName);

    /**
     * 获取流控规则对应clazz，JSON转换用
     *
     * @return
     */
    protected abstract Class getRuleClazz();

    /**
     * 从apollo获取配置
     *
     * @param appName
     * @param dataId
     * @return
     */
    protected String getRulesFromApollo(String appName, String dataId) {
        OpenNamespaceDTO openNamespaceDTO = apolloOpenApiClient.getNamespace(appName,
                env, "default", ApolloConfigUtil.getNamespaceName());

        String rules = openNamespaceDTO
                .getItems()
                .stream()
                .filter(p -> p.getKey().equals(dataId))
                .map(OpenItemDTO::getValue)
                .findFirst()
                .orElse("");

        return rules;
    }
}
