package hubble.backend.business.services.configurations;

import hubble.backend.business.services.models.measures.Unit.MEASURES;

public interface UnitConverter<T> {

    public T to(T model, MEASURES unitMeasure);
}
