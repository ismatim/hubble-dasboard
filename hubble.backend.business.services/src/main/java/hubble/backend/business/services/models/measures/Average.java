package hubble.backend.business.services.models.measures;

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
