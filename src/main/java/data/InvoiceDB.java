package data;

import jakarta.persistence.*;
import model.Customer;
import model.Invoice;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InvoiceDB {
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            String lastId;
            try
            {
                TypedQuery<String> query = em.createQuery(
                        "SELECT i.invoiceID FROM Invoice i ORDER BY i.invoiceID DESC", String.class);
                query.setMaxResults(1);
                lastId = query.getSingleResult();
            }catch(NoResultException e)
            {
                return "BILL0000";
            }
            int number = Integer.parseInt(lastId.substring(4));
            number++; // Tăng giá trị số lên 1
            String newId = String.format("BILL%04d", number);
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

    public static void insertInvoice(Invoice invoice)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(invoice);
        trans.commit();
        em.close();
    }

    public static List<Invoice> getInvoiceList(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT c FROM Invoice c ORDER BY c.invoiceID ASC";
            Query query = em.createQuery(queryString, Invoice.class);
            List<Invoice> rows = query.getResultList();
            return rows;
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CANNOT GET INVOICES", e);
        }
        finally {
            em.close();
        }
    }
}
