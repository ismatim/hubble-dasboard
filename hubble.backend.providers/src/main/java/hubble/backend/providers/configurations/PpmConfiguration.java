package hubble.backend.providers.configurations;

public interface PpmConfiguration {

    public String getApplicationFieldName();

    public String getTransactionFieldName();

    public String getProviderOrigin();

    public String getProviderName();

    public String getRequestTypeIds();

    public String getApplicationValueToIdMap();
}
