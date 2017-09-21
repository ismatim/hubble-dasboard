package hubble.backend.providers.transports.interfaces;

import hubble.backend.providers.configurations.environments.AlmProviderEnvironment;
import java.util.Map;
import org.json.JSONObject;

public interface AlmTransport {

    public void login();

    public void logout();

    public boolean isAuthenticated();

    public AlmProviderEnvironment getEnvironment();

    public Map<String, String> getSessionCookies();

    public JSONObject getAllDefects(Map<String, String> sessionCookies);

    public JSONObject getOpenDefects(Map<String, String> sessionCookies);
}
