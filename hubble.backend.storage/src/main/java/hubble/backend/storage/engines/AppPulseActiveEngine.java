package hubble.backend.storage.engines;

import hubble.backend.storage.models.AvailabilityStorage;

public class AppPulseActiveEngine implements Engine<AvailabilityStorage> {

    @Override
    public void save(AvailabilityStorage entity) {
        System.out.println("Entity" + entity.toString() + " has been saved.");
    }
    
}
