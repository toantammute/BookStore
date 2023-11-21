package model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
public class Invoice {
    @Id
    private String invoiceID;
    private String address;
    private String paymentMethod;
    private String status;
    //@Temporal(TemporalType.DATE)
    private Date orderDate;
    //@Temporal(TemporalType.DATE)
    private Date deliDate;
    private Integer totalAmount;
    private Integer totalPay;
    @OneToOne(optional = false)
    private Discount discount;
    @OneToMany
    private Collection<LineItem> lineItem;

    //Getter and setter
    public String getInvoiceID() {
        return invoiceID;
    }
    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliDate() {
        return deliDate;
    }
    public void setDeliDate(Date deliDate) {
        this.deliDate = deliDate;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalPay() {
        return totalPay;
    }
    public void setTotalPay(Integer totalPay) {
        this.totalPay = totalPay;
    }

    public Discount getDiscount() {
        return discount;
    }
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }



    public Collection<LineItem> getLineItem() {
        return lineItem;
    }
    public void setLineItem(Collection<LineItem> lineItem) {
        this.lineItem = lineItem;
    }

    @ManyToOne(optional = false)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
