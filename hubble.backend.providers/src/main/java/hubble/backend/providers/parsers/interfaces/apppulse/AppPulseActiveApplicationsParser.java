package hubble.backend.providers.parsers.interfaces.apppulse;

import hubble.backend.providers.models.apppulse.ApplicationProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.storage.models.ApplicationStorage;
import java.util.List;
import org.json.JSONObject;

public interface AppPulseActiveApplicationsParser extends Parser {

    public ApplicationProviderModel parse(JSONObject data);

    public List<ApplicationStorage> getApplicationStorage(); //Testing Purpose
}
