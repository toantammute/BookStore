package data;
import jakarta.persistence.*;
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
    public static void insertCategory(Category category) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
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
            String queryString = "SELECT c FROM Category c";
            Query query = em.createQuery(queryString, Category.class);
            List<Category> rows = query.getResultList();
            return rows;
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CREATE NEW ID FAIL", e);
        }
        finally {
            em.close();
        }
    }
}
