package hubble.backend.providers.parsers.interfaces.apppulse;

import hubble.backend.providers.models.apppulse.AvailabilityProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.storage.models.AvailabilityStorage;
import java.util.List;
import org.json.JSONObject;

public interface AppPulseActiveDataParser extends Parser {

    public AvailabilityProviderModel parse(JSONObject data);

    public List<AvailabilityStorage> getAvailabilitiesStorage(); //Testing Purpose
}
