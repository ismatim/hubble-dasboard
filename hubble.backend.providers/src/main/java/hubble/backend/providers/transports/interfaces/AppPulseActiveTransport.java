package hubble.backend.providers.transports.interfaces;

import org.json.JSONObject;

public interface AppPulseActiveTransport extends Transport<JSONObject> {

    public String getToken();

    public boolean hasMoreData();

    public void setLastRetrievedSequenceId(String lastRetrievedSequenceId);

    public JSONObject getApplications();
}
