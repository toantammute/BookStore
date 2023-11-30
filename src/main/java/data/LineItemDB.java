package data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.Book;
import model.LineItem;

import javax.sound.sampled.Line;

public class LineItemDB {
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            String lastId;
            try
            {
                TypedQuery<String> query = em.createQuery(
                        "SELECT l.lineItemID FROM LineItem l ORDER BY l.lineItemID DESC", String.class);
                query.setMaxResults(1);
                lastId = query.getSingleResult();
            }catch(NoResultException e)
            {
                return "LINE0000";
            }
            int number = Integer.parseInt(lastId.substring(4));
            number++; // Tăng giá trị số lên 1
            String newId = String.format("LINE%04d", number);
            return newId;
        }catch(Exception e)
        {
            System.out.print(e);
            throw new RuntimeException("CREATE NEW ID FAIL", e);
        }
        finally {
            em.close();
        }
    }

    public static void insertLineItem(Book book, Integer quantity)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        LineItem lineItem = new LineItem();
        lineItem.setLineItemID(generateId());
        lineItem.setItem(book);
        lineItem.setQuantity(quantity);
        em.persist(lineItem);
        trans.commit();
        em.close();
    }
}
