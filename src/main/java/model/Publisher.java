package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Publisher {
    @Id
    private String publisherID;
    private String publisherName;
    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Collection<Book> books = new ArrayList<>();

    //Getter and setter
    public String getPublisherID() {
        return publisherID;
    }
    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    public Collection<Book> getBooks() {
        return books;
    }
    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
