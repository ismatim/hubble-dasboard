package hubble.backend.providers.transports.interfaces;

import hubble.backend.providers.configurations.environments.PpmProviderEnvironment;
import java.util.List;
import org.json.JSONObject;

public interface PpmTransport {

    public PpmProviderEnvironment getEnvironment();

    public String encodeToBase64(String userName, String password);

    public String decodeFromBase64(String encodedString);

    public List<JSONObject> getAllRequestTypes(String encodedAuthString);

    public List<JSONObject> getRequestTypes(String encodedAuthString, List<String> requestTypeIds);

    public List<JSONObject> getRequests(String encodedAuthString, String requestTypeId);

    public JSONObject getRequestDetails(String encodedAuthString, String requestId);

    public List<String> getConfiguredRequestTypes(String encodedAuthString);

}
