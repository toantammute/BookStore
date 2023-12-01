package data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Cart;
import model.Checkout;
import model.Customer;
import model.LineItem;

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
            int flag = 0;
            for (var item: checkout.getLineItemList()) {
                if(item.getItem().getBookID().equals(newitem.getItem().getBookID()))
                {
                    item.setQuantity(item.getQuantity()+1);
                    flag = 1;
                }

            }
            if(flag == 0) // chua co trong list
            {
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
}
