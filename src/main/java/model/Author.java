package model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Author {
    @Id
    private String authorID;
    private String authorName;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Book> book;

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

    public Collection<Book> getBook() {
        return book;
    }
    public void setBook(Collection<Book> book) {
        this.book = book;
    }
}
