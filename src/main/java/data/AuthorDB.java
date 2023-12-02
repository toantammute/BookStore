package data;

import jakarta.persistence.*;
import model.Author;
import model.Book;

import java.util.List;

public class AuthorDB {
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            String lastId;
            try
            {
                TypedQuery<String> query = em.createQuery(
                        "SELECT a.authorID FROM Author a ORDER BY a.authorID DESC", String.class);
                query.setMaxResults(1);
                lastId = query.getSingleResult();
            }catch(NoResultException e)
            {
                return "AUTH0000";
            }
            int number = Integer.parseInt(lastId.substring(4));
            number++; // Tăng giá trị số lên 1
            String newId = String.format("AUTH%04d", number);
            return newId;
        }catch(Exception e)
        {
            throw new RuntimeException("CREATE NEW ID FAIL", e);
        }
        finally {
            em.close();
        }
    }

    public static void insertAuthor(Author author) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(author);
        trans.commit();
        em.close();
    }

    public static List<Author> getAuthorList(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT a FROM Author a ORDER BY a.authorID ASC";
            Query query = em.createQuery(queryString, Author.class);
            List<Author> rows = query.getResultList();
            return rows;
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CANNOT GET AUTHORS", e);
        }
        finally {
            em.close();
        }
    }

    public static List<Author> searchAuthor(Author author)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT a FROM Author a WHERE LOWER(a.authorName) LIKE LOWER(:name) OR UPPER(a.authorName) LIKE UPPER(:name) ORDER BY a.authorID ASC";
            Query query = em.createQuery(queryString, Author.class);
            query.setParameter("name", "%" + author.getAuthorName() + "%");
            List<Author> authors = query.getResultList();
            if(authors.size() == 0 )
            {
                return null;
            }
            else return authors;
        }
        catch (Exception e)
        {
            throw new RuntimeException("CANNOT GET FIND", e);
        }
        finally {
            em.close();
        }
    }

    public static void deleteAuthor(Author author, StringBuilder error) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            String queryString = "SELECT a FROM Author a where a.authorID = :authorID";
            Query query = em.createQuery(queryString, Author.class);
            query.setParameter("authorID", author.getAuthorID());
            try {
                Author author1 = (Author) query.getSingleResult();
                em.remove(author1);
                trans.commit();
            } catch (NoResultException e) {
                error.append("AUTHOR ID DOES NOT EXIST ");
            }
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public static Author searchAuthorBook(String authorName, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT a FROM Author a WHERE LOWER(a.authorName) = LOWER(:name) OR UPPER(a.authorName) = UPPER(:name)";
            Query query = em.createQuery(queryString, Author.class);
            query.setParameter("name", authorName);
            Author author = (Author) query.getSingleResult();
            if(author != null)
            {
                return author;
            }
            else return null;
        }
        catch (Exception e)
        {
            throw new RuntimeException("CANNOT GET FIND", e);
        }
        finally {
            em.close();
        }
    }

    public static List<Author> getAllAuthor(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT b FROM Author b ORDER BY b.authorID ASC";
            Query query = em.createQuery(queryString, Author.class);
            List<Author> rows = query.getResultList();
            return rows;
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CANNOT GET BOOK", e);
        }
        finally {
            em.close();
        }
    }

    public static List<Book> getAuthorBook(Author author)
    {
        return author.getBook();
    }
}
