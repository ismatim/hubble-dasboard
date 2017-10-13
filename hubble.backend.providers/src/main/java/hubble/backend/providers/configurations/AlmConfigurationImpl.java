package hubble.backend.providers.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/alm.config.properties")
public class AlmConfigurationImpl implements AlmConfiguration {

    @Value("${alm.businessApplication.fieldName}")
    private String applicationFieldName;
    @Value("${alm.status.fieldName}")
    private String statusFieldName;
    @Value("${alm.transaction.fieldName}")
    private String transactionFieldName;
    @Value("${alm.provider.origin}")
    private String providerOrigin;
    @Value("${alm.provider.name}")
    private String providerName;
    @Value("${alm.status.openValues}")
    private String statusOpenValues;
    @Value("${alm.businessApplication.valuesToIdMap}")
    private String applicationValueToIdMap;


    @Override
    public String getApplicationFieldName() {
        return applicationFieldName;
    }

    @Override
    public String getStatusFieldName() {
        return statusFieldName;
    }

    @Override
    public String getTransactionFieldName() {
        return transactionFieldName;
    }

    public String getProviderOrigin() {
        return providerOrigin;
    }

    public void setProviderOrigin(String providerOrigin) {
        this.providerOrigin = providerOrigin;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getStatusOpenValues() {
        return statusOpenValues;
    }

    public void setStatusOpenValues(String statusOpenValues) {
        this.statusOpenValues = statusOpenValues;
    }

    public String getApplicationValueToIdMap() {
        return applicationValueToIdMap;
    }

    public void setApplicationValueToIdMap(String applicationValueToIdMap) {
        this.applicationValueToIdMap = applicationValueToIdMap;
    }
}
