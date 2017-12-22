package hubble.backend.providers.tests;

import java.io.File;
import java.io.FileReader;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class JiraBaseUnitTest {
    
    String pathLocation = "/src/test/resources/fakes/jira/";
    
    public JSONObject loadFakeResponse() {
        String filePath = new File("").getAbsolutePath();
        String jsonLocation = String.format("%s%s%s",filePath, pathLocation, "fake-data-list-1.json");
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(jsonLocation));
            JSONObject fakeResponse = new JSONObject(String.valueOf(object));
            return fakeResponse;
            
        } catch(Exception e) {
            //loggear
            return null;
        }
    }
}
