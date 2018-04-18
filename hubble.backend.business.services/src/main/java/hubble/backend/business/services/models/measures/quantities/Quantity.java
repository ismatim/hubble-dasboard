package hubble.backend.business.services.models.measures.quantities;

import hubble.backend.business.services.models.measures.Status;
import hubble.backend.business.services.models.measures.Unit;

public abstract class Quantity extends Status {

    private Integer quantity;

    public Quantity() {
        unitMeasure = Unit.MEASURES.QUANTITY;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
