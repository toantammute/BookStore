package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Stock {
    @Id
    private String stockID;
    private Integer quantity;
    private Integer importPrice;
    @OneToOne(optional = false)
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
