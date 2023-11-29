package data;

import jakarta.persistence.*;
import model.*;

import javax.security.sasl.AuthorizeCallback;
import java.util.Date;
import java.util.List;

public class BookDB {
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            String lastId;
            try
            {
                TypedQuery<String> query = em.createQuery(
                        "SELECT b.bookID FROM Book b ORDER BY b.bookID DESC", String.class);
                query.setMaxResults(1);
                lastId = query.getSingleResult();
            }catch(NoResultException e)
            {
                return "BOOK0000";
            }
            int number = Integer.parseInt(lastId.substring(4));
            number++; // Tăng giá trị số lên 1
            String newId = String.format("BOOK%04d", number);
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


    public static void insertBook(String bookName, Integer price, String description, String language, Date publisherYear, byte[] imageBook, String publisherName, String categoryName, String authorName, Integer quantity,  StringBuilder error) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            Book book = new Book();
            book.setBookID(generateId());
            book.setBookName(bookName);
            book.setPrice(price);
            book.setDescription(description);
            book.setLanguage(language);
            book.setPublisherYear(publisherYear);
            book.setImage(imageBook);

            Publisher publisher = PublisherDB.searchPublisherBook(publisherName,error);
            book.setPublisher(publisher);
            publisher.getBooks().add(book);

            Category category = CategoryDB.searchCategoryBook(categoryName,error);
            book.setCategory(category);
            category.getBooks().add(book);

            //Author author = em.find(Author.class, AuthorDB.searchAuthorBook(authorName,error).getAuthorID());
            Author author = AuthorDB.searchAuthorBook(authorName,error);
            book.getAuthor().add(author);
            author.getBook().add(book);

            Stock stock = StockDB.searchStockBook(bookName,error);
            if(stock != null)
            {
                stock.setQuantity(stock.getQuantity() + quantity);
                stock.setImportPrice(price/2);
            }
            else
            {
                Stock stock1 = StockDB.insertStockBook(error);
                stock1.setQuantity(quantity);
                stock1.setImportPrice(price/2);
                stock1.setBook(book);
                em.persist(stock1);
            }
            em.persist(book);

            trans.commit();
        }catch(Exception e)
        {
            System.out.println(e);
        }finally
        {
            em.close();
        }
    }

    public static Book searchExactlyBook(String bookName, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT b FROM Book b WHERE LOWER(b.bookName) = LOWER(:name) OR UPPER(b.bookName) = UPPER(:name) ORDER BY b.bookID ASC";
            Query query = em.createQuery(queryString, Book.class);
            query.setParameter("name", bookName);
            Book book = (Book) query.getSingleResult();
            if(book == null )
            {
                error.append("BOOK DOES NOT EXIST");
                return null;
            }
            else return book;
        }
        catch (Exception e)
        {
            throw new RuntimeException("CANNOT GET FIND", e);
        }
        finally {
            em.close();
        }
    }




}
