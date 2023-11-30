package data;

import jakarta.persistence.*;
import model.Book;
import model.Cart;
import model.Customer;
import model.Publisher;

import java.util.List;

public class CartDB {
    public static void addToCart(Customer customer)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        String queryString = "SELECT c FROM Cart c WHERE LOWER(c.customer.customerID) = LOWER(:cus_id)";
        Query query = em.createQuery(queryString, Cart.class);
        query.setParameter("cus_id", customer.getCustomerID() );
        try
        {
            Cart cart = (Cart) query.getSingleResult();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static Cart addNewCart(Cart cart)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.persist(cart);
            trans.commit();
            return cart;
        }catch(Exception e)
        {
            trans.rollback();
            System.out.println(e);
            return null;
        }finally
        {
            em.close();
        }
    }

    public static int checkListBook(Cart cart, Book book)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        for (var book1: cart.getBook()) {
            if (book1.getBookID() == book.getBookID())
            {
                return 0;
            }
        }
        return 1;
    }



}
