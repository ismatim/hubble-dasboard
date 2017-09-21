package hubble.backend.providers.parsers.interfaces.ppm;

import hubble.backend.providers.models.ppm.PpmApplicationProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.storage.models.ApplicationStorage;
import org.json.JSONObject;

public interface PpmApplicationParser extends Parser {

    public PpmApplicationProviderModel parse(JSONObject data);

    public ApplicationStorage convert(PpmApplicationProviderModel ppmApplication);
}
