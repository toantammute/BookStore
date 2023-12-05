package model;

import data.BookDB;
import data.DBUtil;
import jakarta.persistence.*;

import java.text.NumberFormat;
import java.util.*;

@Entity
public class Book {
    @Id
    private String bookID;
    private String bookName;
    private Double price;
    private String description;
    private String language;
    @Temporal(TemporalType.DATE)
    private Date publisherYear;



    @ManyToOne(optional = true)
    private Publisher publisher;
    @ManyToOne(optional = true)
    private Category category;
    @ManyToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Author> author = new ArrayList<>();

    //Getter and setter
    public String getBookID() {
        return bookID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getPublisherYear() {
        return publisherYear;
    }
    public void setPublisherYear(Date publisherYear) {
        this.publisherYear = publisherYear;
    }


    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Author> getAuthor() {
        return author;
    }
    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public String getPriceFormat() {
        Locale locale = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        return currency.format(this.price);
    }
    public int getQuantity() {
        return BookDB.getQuantityInStock(getBookID());
    }
    public double getImportPrice(){
        return price*0.5;
    }




}
