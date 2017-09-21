package hubble.backend.providers.parsers.interfaces.ppm;

import hubble.backend.providers.models.ppm.PpmProgramIssueProviderModel;
import hubble.backend.providers.parsers.interfaces.Parser;
import hubble.backend.storage.models.WorkItemStorage;
import org.json.JSONObject;

public interface PpmDataParser extends Parser {

    public PpmProgramIssueProviderModel parse(JSONObject data);

    public WorkItemStorage convert(PpmProgramIssueProviderModel ppmProgramIssueProviderModel);

}
