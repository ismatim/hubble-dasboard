package hubble.backend.providers.configurations.environments;

public interface JiraProviderEnvironment {
	public String getHost();
    public String getPort();
    public String getUser();
    public String getPassword();
}
