package data;

import jakarta.persistence.*;
import model.Author;
import model.Book;
import model.Publisher;

import java.util.List;

public class PublisherDB {
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            String lastId;
            try
            {
                TypedQuery<String> query = em.createQuery(
                        "SELECT p.publisherID FROM Publisher p ORDER BY p.publisherID DESC", String.class);
                query.setMaxResults(1);
                lastId = query.getSingleResult();
            }catch(NoResultException e)
            {
                return "PUBL0000";
            }
            int number = Integer.parseInt(lastId.substring(4));
            number++; // Tăng giá trị số lên 1
            String newId = String.format("PUBL%04d", number);
            return newId;
        }catch(Exception e)
        {
            throw new RuntimeException("CREATE NEW ID FAIL", e);
        }
        finally {
            em.close();
        }
    }

    public static void insertPublisher(Publisher publisher) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(publisher);
        trans.commit();
        em.close();
    }

    public static List<Publisher> getPublisherList(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT p FROM Publisher p ORDER BY p.publisherID ASC";
            Query query = em.createQuery(queryString, Publisher.class);
            List<Publisher> rows = query.getResultList();
            return rows;
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CANNOT GET PUBLISHERS", e);
        }
        finally {
            em.close();
        }
    }

    public static List<Publisher> searchPublisher(String publisherName, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT p FROM Publisher p WHERE LOWER(p.publisherName) LIKE LOWER(:name) OR UPPER(p.publisherName) LIKE UPPER(:name) ORDER BY p.publisherID ASC";
            Query query = em.createQuery(queryString, Publisher.class);
            query.setParameter("name", "%" + publisherName + "%");
            List<Publisher> publishers = query.getResultList();
            if(publishers.size() == 0 )
            {
                error.append("PUBLISHER DOES NOT EXIST ");
                return null;
            }
            else return publishers;
        }
        catch (Exception e)
        {
            throw new RuntimeException("CANNOT GET FIND", e);
        }
        finally {
            em.close();
        }
    }

    public static void deletePublisher(String publisherID, StringBuilder error) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            String queryString = "SELECT p FROM Publisher p where p.publisherID = :publisherID";
            Query query = em.createQuery(queryString, Publisher.class);
            query.setParameter("publisherID", publisherID);
            try {
                Publisher author = (Publisher) query.getSingleResult();
                em.remove(author);
                trans.commit();
            } catch (NoResultException e) {
                error.append("PUBLISHER ID DOES NOT EXIST ");
            }
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public static void updatePublisher(String publisherID, String publisherName, StringBuilder error) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        String queryString = "SELECT p FROM Publisher p WHERE LOWER(p.publisherName) = LOWER(:name) OR UPPER(p.publisherName) = UPPER(:name)";
        Query query = em.createQuery(queryString, Publisher.class);
        query.setParameter("name", publisherName );
        List<Publisher> publishers = query.getResultList();
        if(publishers.size() == 0)
        {
            trans.begin();
            try {
                String queryString1 = "SELECT p FROM Publisher p where p.publisherID = :publisherID";
                Query query1 = em.createQuery(queryString1, Publisher.class);
                query1.setParameter("publisherID", publisherID);
                try {
                    Publisher publisher = (Publisher) query1.getSingleResult();
                    publisher.setPublisherName(publisherName);
                    em.merge(publisher);
                    trans.commit();
                } catch (NoResultException e) {
                    error.append("PUBLISHER ID DOES NOT EXIST ");
                }
            } catch (Exception e) {
                trans.rollback();
                throw new RuntimeException(e);
            } finally {
                em.close();
            }
        }else
        {
            error.append("PUBLISHER EXISTED ");
            em.close();
        }
    }


    public static Publisher searchPublisherBook(String publisherName, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT p FROM Publisher p WHERE LOWER(p.publisherName) = LOWER(:name) OR UPPER(p.publisherName) = UPPER(:name)";
            Query query = em.createQuery(queryString, Publisher.class);
            query.setParameter("name", publisherName);
            Publisher a = (Publisher) query.getSingleResult();
            if(a != null)
            {
                return a;
            }
            else
            {
                error.append("CANNOT FIND");
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
}
