package hubble.backend.business.services.models.measures.averages;

import hubble.backend.business.services.models.measures.Status;
import hubble.backend.business.services.models.measures.Unit;

public abstract class Average extends Status {

    private Double average;

    public Average() {
        unitMeasure = Unit.MEASURES.PERCENTAGE;
    }

    public Double get() {
        return average;
    }

    public void set(Double average) {
        this.average = average;
    }
}
