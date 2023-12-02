package data;

import jakarta.persistence.*;
import model.*;

import java.util.ArrayList;
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

    public static void removeToCart(Cart cart, Book book)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        Cart newCart = em.find(Cart.class, cart.getCustomer().getCustomerID());
        newCart.getBook().remove(book);
        em.merge(newCart);
        trans.commit();
        em.close();
    }

    public static List<Book> getAllCart(Customer customer)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        Cart newCart = em.find(Cart.class, customer.getCustomerID());
        return newCart.getBook();
    }
    public static List<String> checkBookFavorite(Cart cart)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        List<String> list = new ArrayList<>();
        for (var book1: cart.getBook()) {
            list.add(book1.getBookID());
        }
        return list;
    }


}
