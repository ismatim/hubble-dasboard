package hubble.backend.business.services.models.measures;

public abstract class Average extends Status {

    private Integer average;

    public Integer get() {
        return average;
    }

    public void set(Integer average) {
        this.average = average;
    }
}
