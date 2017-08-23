package hubble.backend.business.services.models.measures;

public abstract class Quantity extends Status {

    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
