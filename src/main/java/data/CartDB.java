package data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.Book;
import model.Cart;
import model.Customer;
import model.Publisher;

import java.util.List;

public class CartDB {
    public static void addToCart(Customer customer, Book book)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        String queryString = "SELECT c FROM Cart c WHERE LOWER(c.customer.customerID) = LOWER(:cus_id)";
        Query query = em.createQuery(queryString, Cart.class);
        query.setParameter("cus_id", customer.getCustomerID() );
        try
        {
            Cart cart = (Cart) query.getSingleResult();
            cart.getBook().add(book);
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
