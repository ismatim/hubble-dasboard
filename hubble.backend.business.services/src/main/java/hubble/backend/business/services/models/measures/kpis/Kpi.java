package hubble.backend.business.services.models.measures.kpis;

import hubble.backend.business.services.models.measures.Status;

public abstract class Kpi extends Status {

    private double value;

    public double get() {
        return this.value;
    }

    public void set(double value) {
        this.value = value;
    }
}
