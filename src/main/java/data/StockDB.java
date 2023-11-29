package data;

import jakarta.persistence.*;
import model.Book;
import model.Category;
import model.Stock;

import java.util.List;

public class StockDB {
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            String lastId;
            try
            {
                TypedQuery<String> query = em.createQuery(
                        "SELECT s.stockID FROM Stock s ORDER BY s.stockID DESC", String.class);
                query.setMaxResults(1);
                lastId = query.getSingleResult();
            }catch(NoResultException e)
            {
                return "STOC0000";
            }
            int number = Integer.parseInt(lastId.substring(4));
            number++; // Tăng giá trị số lên 1
            String newId = String.format("STOC%04d", number);
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

    public static Stock searchStockBook(String bookName, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT s FROM Stock s WHERE LOWER(s.book.bookName) = LOWER(:name) OR UPPER(s.book.bookName) = UPPER(:name)";
            Query query = em.createQuery(queryString, Stock.class);
            query.setParameter("name", bookName);
            try{
                Stock a = (Stock) query.getSingleResult();
                return a;
            }catch(NoResultException e){
                return null;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("CANNOT GET FIND", e);
        }
        finally {
            em.close();
        }
    }

    public static Stock insertStockBook(StringBuilder error) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            Stock a = new Stock();
            a.setStockID(generateId());
            return a;
        }catch (Exception e)
        {
            return null;
        }
    }


    public static void updateBookQuantity(String bookName, Integer quantity, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            Book book = BookDB.searchExactlyBook(bookName,error);
            String queryString = "SELECT s FROM Stock s WHERE s.book.bookID = :bookID";
            Query query = em.createQuery(queryString, Stock.class);
            query.setParameter("bookID", book.getBookID());
            Stock stock = (Stock) query.getSingleResult();
            stock.setQuantity(quantity);
            trans.commit();
        }catch(Exception e)
        {
            System.out.println(e);
            trans.rollback();
        }finally {
            em.close();
        }
    }

    public static void deleteBookQuantity(String bookName, Integer quantity, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            Book book = BookDB.searchExactlyBook(bookName,error);
            String queryString = "SELECT s FROM Stock s WHERE s.book.bookID = :bookID";
            Query query = em.createQuery(queryString, Stock.class);
            query.setParameter("bookID", book.getBookID());
            Stock stock = (Stock) query.getSingleResult();
            em.remove(stock);
            trans.commit();
        }catch(Exception e)
        {
            System.out.println(e);
            trans.rollback();
        }finally {
            em.close();
        }
    }

}
