package hubble.backend.providers.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/ppm.config.properties")
public class PpmConfigurationImpl implements PpmConfiguration {

    @Value("${ppm.businessApplication.fieldName}")
    private String applicationFieldName;
    @Value("${ppm.transaction.fieldName}")
    private String transactionFieldName;
    @Value("${ppm.provider.origin}")
    private String providerOrigin;
    @Value("${ppm.provider.name}")
    private String providerName;
    @Value("${ppm.requestTypeIds}")
    private String requestTypeIds;
    @Value("${ppm.businessApplication.valuesToIdMap}")
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
