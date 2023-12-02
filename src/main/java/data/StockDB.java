package data;

import jakarta.persistence.*;
import model.Book;
import model.Category;
import model.Stock;

import java.util.List;

public class StockDB {
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

    public static void insertStockBook(Stock stock) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(stock);
        trans.commit();
        em.close();
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

    public static void deleteBookQuantity(Stock stock, Integer quantity)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        Stock stock1 = em.find(Stock.class, stock.getBook().getBookID());
        stock1.setQuantity(stock1.getQuantity()-quantity);
        em.merge(stock1);
        trans.commit();
        em.close();
    }



}
