package hubble.backend.providers.configurations;

public interface AlmConfiguration {
    public String getApplicationFieldName();
    public String getStatusFieldName();
    public String getTransactionFieldName();
    public String getProviderOrigin();
    public String getProviderName();
    public String getStatusOpenValues();
    public String getApplicationValueToIdMap();
}
