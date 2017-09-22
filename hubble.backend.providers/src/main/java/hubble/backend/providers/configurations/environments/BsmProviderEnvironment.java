package hubble.backend.providers.configurations.environments;

public interface BsmProviderEnvironment {
    public String getSoapEndpointUrl();
    public String getSoapAction();
    public String getUserName();
    public String getPassword();
}
