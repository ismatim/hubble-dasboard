package hubble.backend.providers.configurations.environments;

public interface AlmProviderEnvironment {
    public String getHost();
    public String getPort();
    public String getUserName();
    public String getPassword();
    public String getDomain();    
    public String getProject();
}
