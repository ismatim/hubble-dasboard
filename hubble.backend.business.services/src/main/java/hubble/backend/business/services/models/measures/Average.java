package hubble.backend.business.services.models.measures;

public abstract class Average extends Status {

    private Float average;

    public Average() {
        unitMeasure = Unit.MEASURES.PERCENTAGE;
    }

    public Float get() {
        return average;
    }

    public void set(Float average) {
        this.average = average;
    }
}
