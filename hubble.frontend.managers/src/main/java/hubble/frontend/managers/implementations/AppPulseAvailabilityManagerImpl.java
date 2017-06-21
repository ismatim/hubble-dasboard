package hubble.frontend.managers.implementations;

import com.google.gson.JsonObject;
import hubble.frontend.managers.interfaces.AppPulseAvailabilityManager;

public class AppPulseAvailabilityManagerImpl implements AppPulseAvailabilityManager {
    
    @Override
    public JsonObject getSamples() {
        return new JsonObject();
    }
    
}
