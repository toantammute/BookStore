package model;

import jakarta.persistence.*;

@Entity
public class Stock {
    @Id
    private String stockID;
    private Integer quantity;
    private Integer importPrice;
    @OneToOne(optional = false, cascade = {CascadeType.REMOVE},fetch = FetchType.EAGER)
    private Book book;

    //Getter and setter
    public String getStockID() {
        return stockID;
    }
    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

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
