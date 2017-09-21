package hubble.backend.providers.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/ppm.config.properties")
public class PpmConfigurationImpl implements PpmConfiguration {

    @Value("${businessApplication.fieldName}")
    private String applicationFieldName;
    @Value("${transaction.fieldName}")
    private String transactionFieldName;
    @Value("${provider.origin}")
    private String providerOrigin;
    @Value("${provider.name}")
    private String providerName;
    @Value("${requestTypeIds}")
    private String requestTypeIds;
    @Value("${businessApplication.valuesToIdMap}")
    private String applicationValueToIdMap;

    @Override
    public String getApplicationFieldName() {
        return applicationFieldName;
    }
    
    @Override
    public String getTransactionFieldName() {
        return transactionFieldName;
    }

    @Override
    public String getProviderOrigin() {
        return providerOrigin;
    }

    @Override
    public String getProviderName() {
        return providerName;
    }

    @Override
    public String getRequestTypeIds() {
        return requestTypeIds;
    }

    @Override
    public String getApplicationValueToIdMap() {
        return applicationValueToIdMap;
    }
}
