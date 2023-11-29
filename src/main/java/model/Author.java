package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Author {
    @Id
    private String authorID;
    private String authorName;
    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Book> book = new ArrayList<>();

    //Getter and setter
    public String getAuthorID() {
        return authorID;
    }
    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Book> getBook() {
        return book;
    }
    public void setBook(List<Book> book) {
        this.book = book;
    }
}
