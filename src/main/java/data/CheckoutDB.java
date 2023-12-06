package data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.*;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CheckoutDB {
    public static void addNewChekout(Checkout checkout)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            em.persist(checkout);
            trans.commit();
        }catch(Exception e)
        {
            trans.rollback();
            System.out.println(e);
        }finally
        {
            em.close();
        }
    }


    public static Checkout addItem(Checkout checkout1, LineItem newitem)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {

            trans.begin();
            Checkout checkout = em.find(Checkout.class, checkout1.getCustomer().getCustomerID()); // checkout
            Stock stock =  em.find(Stock.class, newitem.getItem().getBookID());
            int flag = 0;
            for (var item: checkout.getLineItemList()) {
                if(item.getItem().getBookID().equals(newitem.getItem().getBookID()))
                {
                    if((item.getQuantity()+1) <= stock.getQuantity())
                    {
                        item.setQuantity(item.getQuantity()+1);
                        flag = 1;
                    }
                }

            }
            if(flag == 0) // chua co trong list
            {
                if(stock.getQuantity() >= newitem.getQuantity())
                checkout.getLineItemList().add(newitem);
            }
            em.merge(checkout);
            trans.commit();
            return checkout;
        }
        catch(Exception e)
        {
            trans.rollback();
            return null;
        }
        finally {
            em.close();
        }
    }

    public static Integer getSize(Customer customer)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        Checkout checkout = em.find(Checkout.class, customer.getCustomerID());
        return checkout.getLineItemList().size();
    }

    public static List<LineItem> getAllCheckout(Customer customer)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        Checkout checkout = em.find(Checkout.class, customer.getCustomerID());
        return checkout.getLineItemList();
    }


    public static Checkout removeItem(Checkout checkout1, Book book)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {

            trans.begin();
            Checkout checkout = em.find(Checkout.class, checkout1.getCustomer().getCustomerID()); // checkout
            for(int i = 0 ; i < checkout.getLineItemList().size() ; i++)
            {
                LineItem lineItem = checkout.getLineItemList().get(i);
                if(lineItem.getItem().getBookID().equals(book.getBookID()))
                {
                    checkout.getLineItemList().remove(lineItem);
                }
            }
            em.merge(checkout);
            trans.commit();
            return checkout;
        }
        catch(Exception e)
        {
            trans.rollback();
            return null;
        }
        finally {
            em.close();
        }
    }

    public static Checkout updateItem(Checkout checkout1, Book book, Integer quantity)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {

            trans.begin();
            Checkout checkout = em.find(Checkout.class, checkout1.getCustomer().getCustomerID()); // checkout
            for(int i = 0 ; i < checkout.getLineItemList().size() ; i++)
            {
                LineItem lineItem = checkout.getLineItemList().get(i);
                if(lineItem.getItem().getBookID().equals(book.getBookID()))
                {
                    lineItem.setQuantity(quantity);
                }
            }
            em.merge(checkout);
            trans.commit();
            return checkout;
        }
        catch(Exception e)
        {
            trans.rollback();
            return null;
        }
        finally {
            em.close();
        }
    }

    public static double totalCheckout(Customer customer)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        double sum = 0;
        Checkout checkout = em.find(Checkout.class, customer.getCustomerID());
        for (var item: checkout.getLineItemList())
        {
            sum += item.totalItemPrice();
        }
        return sum;
    }


    public static String getTotalCurrencyFormat(Customer customer) {
        Locale locale = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        return currency.format(totalCheckout(customer));
    }

    public static double totalDiscountCheckout(Customer customer)
    {
        Double total = totalCheckout(customer);
        if(total > 1000)
        {
            total *= 0.8;
        }
        else if(total > 500)
        {
            total *= 0.9;
        }
        return total;
    }

    public static String getTotalDiscountCurrencyFormat(Customer customer) {
        Locale locale = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        return currency.format(totalDiscountCheckout(customer));
    }

    public static int getDiscount(Customer customer)
    {
        Double total = totalCheckout(customer);
        if(total > 1000)
        {
            int discount = 20;
            return discount;
        }
        if(total > 500)
        {
            int discount = 10;
            return discount;
        }
        return 0;
    }

    public static Checkout removeCheckout(Customer customer)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {

            trans.begin();
            Checkout checkout = em.find(Checkout.class, customer.getCustomerID()); // checkout
            for(int i = 0 ; i < checkout.getLineItemList().size() ; i++)
            {
                LineItem lineItem = checkout.getLineItemList().get(i);
                checkout.getLineItemList().remove(lineItem);
                i--;
            }
            em.merge(checkout);
            trans.commit();
            return checkout;
        }
        catch(Exception e)
        {
            trans.rollback();
            return null;
        }
        finally {
            em.close();
        }
    }

    public static LineItem checkListBook(Checkout checkout, Customer customer, Book book)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        for (var item: checkout.getLineItemList()) {
            if (item.getItem().getBookID() == book.getBookID())
            {
                return item;
            }
        }
        return null;
    }
}
