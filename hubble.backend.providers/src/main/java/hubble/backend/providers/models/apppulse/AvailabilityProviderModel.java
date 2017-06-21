package hubble.backend.providers.models.apppulse;

import java.util.ArrayList;

public class AvailabilityProviderModel {

    private String lastRetrievedSequenceId;
    private Boolean hasMoreDataToFetch;
    private ArrayList<AvailabilityDataProviderModel> data;

    public String getLastRetrievedSequenceId() {
        return lastRetrievedSequenceId;
    }

    public void setLastRetrievedSequenceId(String lastRetrievedSequenceId) {
        this.lastRetrievedSequenceId = lastRetrievedSequenceId;
    }

    public Boolean getHasMoreDataToFetch() {
        return hasMoreDataToFetch;
    }

    public void setHasMoreDataToFetch(Boolean hasMoreDataToFetch) {
        this.hasMoreDataToFetch = hasMoreDataToFetch;
    }

    public ArrayList<AvailabilityDataProviderModel> getData() {
        return data;
    }

    public void setData(ArrayList<AvailabilityDataProviderModel> data) {
        this.data = data;
    }
}
