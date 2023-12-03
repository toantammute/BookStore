package data;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    public static double getAllTotalAmount(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            double total = 0;
            List<Invoice> inv = getInvoiceList();
            for (var item: inv)
            {
                total += item.getTotalAmountDouble();
            }
            return total;
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
    public static double getAllTotalPay(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            double total = 0;
            List<Invoice> inv = getInvoiceList();
            for (var item: inv)
            {
                total += item.getTotalPayDouble();
            }
            return total;
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
    public static double getTotalImportPrice(){
        double revenue;
        return revenue = getAllTotalAmount()*0.5;
    }
    public static double getProfit(){
        double profit =0;
        return profit = getAllTotalPay()-getTotalImportPrice();
    }

}
