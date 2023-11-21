package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Collection;

@Entity
public class Cart {
    @Id
    @OneToOne(optional = false)
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany
    private Collection<Book> book;

    public Collection<Book> getBook() {
        return book;
    }

    public void setBook(Collection<Book> book) {
        this.book = book;
    }
}
