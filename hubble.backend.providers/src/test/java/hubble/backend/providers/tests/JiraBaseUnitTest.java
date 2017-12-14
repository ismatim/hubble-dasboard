package hubble.backend.providers.tests;

import org.json.JSONObject;

public class JiraBaseUnitTest {
    
    String pathLocation = "/fakes/jira/";
    
    public JSONObject loadFakeResponse() {
        String json = String.format("%s%s", pathLocation, "fake-data-list-1.json");
        JSONObject fakeResponse = new JSONObject(json);
        return fakeResponse;
    }
}
