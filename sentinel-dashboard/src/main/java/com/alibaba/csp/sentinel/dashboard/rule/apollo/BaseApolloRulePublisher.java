package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @link https://github.com/hosaos/apollo-sentinel-dashboard
 */
@Component
public abstract class BaseApolloRulePublisher implements DynamicRulePublisher {

    @Autowired
    protected ApolloOpenApiClient apolloOpenApiClient;
    @Value("${apollo.env}")
    private String env;

    @Override
    public void publish(String appName, Object rules) throws Exception {
        AssertUtil.notEmpty(appName, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        String dataId = getDataId(appName);

        pushRulesToApollo(appName, dataId, rules);
    }

    protected abstract String getDataId(String appName);

    /**
     * 推送规则到apollo
     *
     * @param appName
     * @param dataId
     * @return
     */
    protected void pushRulesToApollo(String appName, String dataId, Object rules) {
        String masterUser = "apollo";
        String clusterName = "default";

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(dataId);
        openItemDTO.setValue(JSON.toJSONString(rules));
        openItemDTO.setDataChangeCreatedBy(masterUser);
        apolloOpenApiClient.createOrUpdateItem(appName, env, clusterName, ApolloConfigUtil.getNamespaceName(), openItemDTO);

        // Release configuration
        NamespaceReleaseDTO namespaceReleaseDTO = new NamespaceReleaseDTO();
        namespaceReleaseDTO.setEmergencyPublish(true);
        namespaceReleaseDTO.setReleasedBy(masterUser);
        namespaceReleaseDTO.setReleaseTitle("Modify or add configurations");
        apolloOpenApiClient.publishNamespace(appName, env, clusterName, ApolloConfigUtil.getNamespaceName(), namespaceReleaseDTO);
    }
}
