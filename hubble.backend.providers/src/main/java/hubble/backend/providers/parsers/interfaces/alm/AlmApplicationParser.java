package hubble.backend.providers.parsers.interfaces.alm;

import hubble.backend.providers.models.alm.AlmApplicationProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.storage.models.ApplicationStorage;
import java.util.List;
import org.json.JSONObject;

public interface AlmApplicationParser extends Parser {
    
    public AlmApplicationProviderModel parse(JSONObject data);
    public List<JSONObject> parseList(JSONObject data);
    public ApplicationStorage convert(AlmApplicationProviderModel almApplicationProviderModel);
}
