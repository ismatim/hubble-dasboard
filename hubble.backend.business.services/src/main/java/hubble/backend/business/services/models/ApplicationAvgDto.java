package hubble.backend.business.services.models;

import hubble.backend.business.services.models.measures.AvailabilityAverage;
import hubble.backend.business.services.models.measures.AvailabilityQuantity;
import hubble.backend.business.services.models.measures.PerformanceAverage;
import hubble.backend.business.services.models.measures.PerformanceQuantity;

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

    public void setAvailabilityAverage(int availabilityAverage) {
        this.availabilityAverage.set(availabilityAverage);
    }

    public void setPerformanceAverage(int performanceAverage) {
        this.getPerformanceAverage().set(performanceAverage);
    }

    public Integer getPerformanceAverageValue() {
        return this.performanceAverage.get();
    }

    public Integer getAvailabilityAverageValue() {
        return this.availabilityAverage.get();
    }
}
