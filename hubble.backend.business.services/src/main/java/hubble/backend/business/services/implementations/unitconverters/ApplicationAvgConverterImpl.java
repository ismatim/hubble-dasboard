package hubble.backend.business.services.implementations.unitconverters;

import hubble.backend.business.services.interfaces.unitconverters.UnitConverter;
import hubble.backend.business.services.models.ApplicationAvgDto;
import hubble.backend.business.services.models.measures.Unit;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * Converts all the values of ApplicationAvg to a specific unit of measure for
 * example: seconds, minutes, months, percentage of.
 *
 * @author ismaeltisminetzky
 */
@Component
public class ApplicationAvgConverterImpl implements UnitConverter<ApplicationAvgDto> {

    @Override
    public ApplicationAvgDto to(ApplicationAvgDto model, Unit.MEASURES measure) {

        if (model == null) {
            return model;
        }

        if (measure == Unit.MEASURES.SECONDS) {
            return toSeconds(model);
        }

        return model;
    }

    private ApplicationAvgDto toSeconds(ApplicationAvgDto model) {
        if (model.getOkThreshold() != 0) {
            model.setOkThreshold(round((model.getOkThreshold() / 1000), 2));
        }

        if (model.getCriticalThreshold() != 0) {
            model.setCriticalThreshold(round((model.getCriticalThreshold() / 1000), 2));
        }

        if (model.getPerformanceAverageValue() != null && model.getPerformanceAverageValue() != 0) {
            model.setPerformanceAverage(round((model.getPerformanceAverageValue() / 1000), 2));
        }

        return model;
    }

    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
