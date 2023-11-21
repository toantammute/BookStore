package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class LineItem {
    @Id
    private String lineItemID;
    private Integer quantity;
    @OneToOne(optional = false)
    private Book item;

    //Getter and setter
    public String getLineItemID() {
        return lineItemID;
    }
    public void setLineItemID(String lineItemID) {
        this.lineItemID = lineItemID;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Book getItem() {
        return item;
    }
    public void setItem(Book item) {
        this.item = item;
    }
}
