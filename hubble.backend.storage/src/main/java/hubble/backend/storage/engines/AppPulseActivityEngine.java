package hubble.backend.storage.engines;

import hubble.backend.storage.models.AppPulseActivityStorage;

public class AppPulseActivityEngine implements Engine<AppPulseActivityStorage> {

    @Override
    public void save(AppPulseActivityStorage entity) {
        System.out.println("Entity" + entity.toString() + " has been saved.");
    }
    
}
