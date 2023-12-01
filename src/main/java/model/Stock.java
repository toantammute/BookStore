package model;

import jakarta.persistence.*;

@Entity
public class Stock {
    @Id
    @OneToOne(optional = false, cascade = {CascadeType.REMOVE},fetch = FetchType.EAGER)
    private Book book;
    private Integer quantity;
    private Integer importPrice;


    //Getter and setter

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getImportPrice() {
        return importPrice;
    }
    public void setImportPrice(Integer importPrice) {
        this.importPrice = importPrice;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
