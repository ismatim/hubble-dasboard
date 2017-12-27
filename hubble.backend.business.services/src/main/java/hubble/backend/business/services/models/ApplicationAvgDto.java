package hubble.backend.business.services.models;

import hubble.backend.business.services.models.measures.AvailabilityAverage;
import hubble.backend.business.services.models.measures.AvailabilityQuantity;
import hubble.backend.business.services.models.measures.PerformanceAverage;
import hubble.backend.business.services.models.measures.PerformanceQuantity;

/**
 * ApplicationAvg is an Application with its average value for Performance,
 * Availability, quantity of measures of Performance and Availability. The class
 * holds differente kind of calculation for example: 10 minutes, 1 hours, 24
 * hours, 1 month.
 *
 * @deprecated This is an old version because ApplicationAvgDto should be
 * composed and not inheritance by ApplicationDto
 * @author Ismael J. Tisminetzky / Alexander
 */
public class ApplicationAvgDto extends ApplicationDto {

    private AvailabilityAverage availabilityAverage;
    private PerformanceAverage performanceAverage;
    private PerformanceQuantity measuresQtyPerformance;
    private AvailabilityQuantity measuresQtyAvailability;

    public ApplicationAvgDto() {
        this.availabilityAverage = new AvailabilityAverage();
        this.measuresQtyAvailability = new AvailabilityQuantity();
        this.measuresQtyPerformance = new PerformanceQuantity();
        this.performanceAverage = new PerformanceAverage();
    }

    public AvailabilityAverage getAvailabilityAverage() {
        return availabilityAverage;
    }

    public void setAvailabilityAverage(AvailabilityAverage average) {
        this.availabilityAverage = average;
    }

    public PerformanceQuantity getMeasuresQtyPerformance() {
        return measuresQtyPerformance;
    }

    public void setMeasuresQtyPerformance(PerformanceQuantity measuresQtyPerformance) {
        this.measuresQtyPerformance = measuresQtyPerformance;
    }

    public AvailabilityQuantity getMeasuresQtyAvailability() {
        return measuresQtyAvailability;
    }

    public void setMeasuresQtyAvailability(AvailabilityQuantity measuresQtyAvailability) {
        this.measuresQtyAvailability = measuresQtyAvailability;
    }

    public void setMeasuresQtyAvailability(int size) {
        this.measuresQtyAvailability.setQuantity(size);
    }

    public PerformanceAverage getPerformanceAverage() {
        return performanceAverage;
    }

    public void setPerformanceAverage(PerformanceAverage performanceAverage) {
        this.performanceAverage = performanceAverage;
    }

    public void setAvailabilityAverage(Float availabilityAverage) {
        this.availabilityAverage.set(availabilityAverage);
    }

    public void setPerformanceAverage(Float performanceAverage) {
        this.getPerformanceAverage().set(performanceAverage);
    }

    public Float getPerformanceAverageValue() {
        return this.performanceAverage.get();
    }

    public Float getAvailabilityAverageValue() {
        return this.availabilityAverage.get();
    }
}
