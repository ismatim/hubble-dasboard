package hubble.backend.business.services.models.measures;

/**
 * The measure of quantities of work items.
 *
 * @author Ismael J. Tisminetzky
 */
public class WorkItemQuantity extends Quantity {

    private Integer criticalThreshold;

    /**
     * The current critical threshold for the measure. Below is ok, over is
     * critical.
     *
     * @return
     */
    public Integer getCriticalThreshold() {
        return criticalThreshold;
    }

    /**
     * Set the critical value for the measure of quantity of items to work.
     *
     * @param criticalThreshold
     */
    public void setCriticalThreshold(Integer criticalThreshold) {
        this.criticalThreshold = criticalThreshold;
    }
}
