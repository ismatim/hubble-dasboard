package hubble.backend.tests.models;

import hubble.backend.providers.models.apppulse.AvailabilityDataProviderModel;

public class AvailabilityDataProviderModelTest extends AvailabilityDataProviderModel {

    public void print() {
        System.out.println(this.getApplicationName());
        System.out.println(this.getApplicationId());
        System.out.println(this.getTransactionId());
        System.out.println(this.getTransactionName());
        System.out.println(this.getLocationName());
        System.out.println(this.getLocationId());
        System.out.println(this.getScriptName());
        System.out.println(this.getServerName());
        System.out.println(this.getApplicationName());
    }
}
