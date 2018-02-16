package hubble.backend.providers.parsers.interfaces.jira;

import hubble.backend.providers.models.jira.JiraApplicationProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import org.json.JSONObject;

public interface JiraApplicationParser extends Parser {

    public JiraApplicationProviderModel parse(JSONObject data);

}
