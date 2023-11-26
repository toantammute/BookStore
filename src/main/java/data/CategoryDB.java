package data;
import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import model.Category;
import org.eclipse.persistence.jpa.jpql.parser.NullExpression;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CategoryDB {

    // GENERATE ID FUNCTION
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            String lastId;
            try
            {
                TypedQuery<String> query = em.createQuery(
                        "SELECT c.categoryID FROM Category c ORDER BY c.categoryID DESC", String.class);
                query.setMaxResults(1);
                lastId = query.getSingleResult();
            }catch(NoResultException e)
            {
                return "CATE0000";
            }
            int number = Integer.parseInt(lastId.substring(4));
            number++; // Tăng giá trị số lên 1
            String newId = String.format("CATE%04d", number);
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

    // INSERT CATEGORY
    public static void insertCategory(String categoryName) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            Category category = new Category();
            category.setCategoryID(generateId());
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

    public static List<Category> searchCategory(String categoryName, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT c FROM Category c WHERE LOWER(c.categoryName) LIKE LOWER(:name) OR UPPER(c.categoryName) LIKE UPPER(:name) ORDER BY c.categoryID ASC";
            Query query = em.createQuery(queryString, Category.class);
            query.setParameter("name", "%" + categoryName + "%");
            List<Category> categories = query.getResultList();
            if(categories.size() == 0 )
            {
                error.append("CATEGORY NAME DOES NOT EXIST");
                return null;
            }
            else return categories;
        }
        catch (Exception e)
        {
            throw new RuntimeException("CANNOT GET FIND", e);
        }
        finally {
            em.close();
        }
    }

    public static void deleteCategory(String categoryID, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            String queryString = "SELECT c FROM Category c where c.categoryID = :categoryID";
            Query query = em.createQuery(queryString, Category.class);
            query.setParameter("categoryID",categoryID);
            try
            {
                Category category = (Category) query.getSingleResult();
                em.remove(category);
                trans.commit();
            }catch(NoResultException e)
            {
                error.append("CATEGORY ID DOES NOT EXIST");
            }
        }catch (Exception e)
        {
            error.append(e);
            trans.rollback();
            throw new RuntimeException(e);
        }finally{
            em.close();
        }
    }

    public static void updateCategory(String categoryID, String categoryName, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            String queryString = "SELECT c FROM Category c where c.categoryID = :categoryID";
            Query query = em.createQuery(queryString, Category.class);
            query.setParameter("categoryID",categoryID);
            try
            {
                Category category = (Category) query.getSingleResult();
                category.setCategoryName(categoryName);
                em.merge(category);
                trans.commit();
            }catch(NoResultException e)
            {
                error.append("CATEGORY ID DOES NOT EXIST");
            }
        }catch (Exception e)
        {
            trans.rollback();
            throw new RuntimeException(e);
        }finally{
            em.close();
        }
    }





}
