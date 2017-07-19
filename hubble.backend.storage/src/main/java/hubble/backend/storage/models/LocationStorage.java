package hubble.backend.storage.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LocationStorage {
    
    private String locationId;
    private String locationName;
    private boolean isInternal;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean isIsInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }
    
}
