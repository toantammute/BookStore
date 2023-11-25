package data;
import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import model.Category;

import java.util.List;

public class CategoryDB {

    // GENERATE ID FUNCTION
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try
        {
            TypedQuery<String> query = em.createQuery(
                    "SELECT c.categoryID FROM Category c ORDER BY c.categoryID DESC", String.class);
            query.setMaxResults(1);
            String lastId = query.getSingleResult();
            if (lastId != null) {
                int number = Integer.parseInt(lastId.substring(4));
                number++; // Tăng giá trị số lên 1
                String newId = String.format("CATE%04d", number);
                return newId;
            } else {
                return "CATE0000";
            }
        }catch(Exception e)
        {
            System.out.print(e);
            trans.rollback();
            throw new RuntimeException("CREATE NEW ID FAIL", e);
        }
        finally {
            em.close();
        }
    }

    // INSERT CATEGORY
    public static void insertCategory(String categoryName) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            Category category = new Category();
            category.setCategoryName(categoryName);
            em.persist(category);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static List<Category> getCategoryList(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT c FROM Category c ORDER BY c.categoryID ASC";
            Query query = em.createQuery(queryString, Category.class);
            List<Category> rows = query.getResultList();
            return rows;
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CANNOT GET CATEGORIES", e);
        }
        finally {
            em.close();
        }
    }

    public static List<Category> searchCategory(String categoryName)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT c FROM Category c WHERE LOWER(c.categoryName) LIKE LOWER(:name) OR UPPER(c.categoryName) LIKE UPPER(:name) ORDER BY c.categoryID ASC";
            Query query = em.createQuery(queryString, Category.class);
            query.setParameter("name", "%" + categoryName + "%");
            List<Category> categories = query.getResultList();
            return categories;
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CANNOT GET FIND", e);
        }
        finally {
            em.close();
        }
    }

    public static void deleteCategory(String categoryID)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            String queryString = "SELECT c FROM Category c where c.categoryID = :categoryID";
            Query query = em.createQuery(queryString, Category.class);
            query.setParameter("categoryID",categoryID);
            Category category = (Category) query.getSingleResult();
            em.remove(category);
            trans.commit();
        }catch (Exception e)
        {
            trans.rollback();
            throw new RuntimeException(e);
        }finally{
            em.close();
        }
    }

    public static void updateCategory(String categoryID, String categoryName)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            String queryString = "SELECT c FROM Category c where c.categoryID = :categoryID";
            Query query = em.createQuery(queryString, Category.class);
            query.setParameter("categoryID",categoryID);
            Category category = (Category) query.getSingleResult();
            category.setCategoryName(categoryName);
            em.merge(category);
            trans.commit();
        }catch (Exception e)
        {
            trans.rollback();
            throw new RuntimeException(e);
        }finally{
            em.close();
        }
    }





}
