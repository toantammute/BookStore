package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
public class Discount {
    @Id
    private String discountID;
    private Integer percentage;
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Temporal(TemporalType.DATE)
    private Date dateExpire;

    //Getter and setter
    public String getDiscountID() {
        return discountID;
    }
    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public Integer getPercentage() {
        return percentage;
    }
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateExpire() {
        return dateExpire;
    }
    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }
}
