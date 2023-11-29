package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Book {
    @Id
    private String bookID;
    private String bookName;
    private Integer price;
    private String description;
    private String language;
    @Temporal(TemporalType.DATE)
    private Date publisherYear;
    private byte[] imageBook;
    @ManyToOne(optional = true)
    private Publisher publisher;
    @ManyToOne(optional = true)
    private Category category;
    @ManyToMany(cascade = {CascadeType.MERGE}, mappedBy = "book", fetch = FetchType.EAGER)
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

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
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

    public byte[] getImageBook() {
        return imageBook;
    }
    public void setImage(byte[] imageBook) {
        this.imageBook = imageBook;
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
}
