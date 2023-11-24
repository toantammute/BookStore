package data;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class CategoryDB {
    // GENERATE ID FUNCTION
    public String generateId() {
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
}
