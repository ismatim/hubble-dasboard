package hubble.backend.business.services.models.measures.rules;

import hubble.backend.business.services.models.measures.*;

public abstract class Rule<T extends Comparable> extends Status {

    private T value;

    public Rule() {
        unitMeasure = Unit.MEASURES.PERCENTAGE;
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
