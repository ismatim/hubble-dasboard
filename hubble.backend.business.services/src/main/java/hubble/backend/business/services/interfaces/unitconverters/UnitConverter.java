package hubble.backend.business.services.interfaces.unitconverters;

import hubble.backend.business.services.models.measures.Unit.MEASURES;

/**
 * Defines units in the domain of business service for example: totals, averages
 * of time, distances, quantity, calculation, dimensions, downtime, uptime,
 * performance. TODO: Ésta interfaz debe estar implementada por cada uno de las
 * clases de mediciones para que permitan la libre conversión entre unidades de
 * las diferentes propiedades.
 *
 * @author Ismael J. Tisminetzky
 * @param <T> Generic measure for example: time to happens something, distances
 * between locations.
 */
public interface UnitConverter<T> {

    public T to(T model, MEASURES unitMeasure);
}
