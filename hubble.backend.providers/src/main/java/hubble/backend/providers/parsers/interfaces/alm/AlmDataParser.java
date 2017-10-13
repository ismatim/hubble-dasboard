package hubble.backend.providers.parsers.interfaces.alm;

import hubble.backend.providers.models.alm.AlmDefectProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.storage.models.IssueStorage;
import java.util.List;
import org.json.JSONObject;

public interface AlmDataParser extends Parser {
    
    public AlmDefectProviderModel parse(JSONObject data);
    public List<JSONObject> parseList(JSONObject data);
    public IssueStorage convert(AlmDefectProviderModel almProviderModel);
}
