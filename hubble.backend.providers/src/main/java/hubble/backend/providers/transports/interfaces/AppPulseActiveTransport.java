package hubble.backend.providers.transports.interfaces;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public interface AppPulseActiveTransport extends Transport<JSONObject>{

    public String getToken();
    public boolean hasMoreData();
    public void setLastRetrievedSequenceId(String lastRetrievedSequenceId);
}
