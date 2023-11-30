package data;

import jakarta.persistence.*;
import model.Author;
import model.Cart;
import model.Customer;

import java.util.Date;
import java.util.List;

public class CustomerDB {
    public static String generateId() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            String lastId;
            try
            {
                TypedQuery<String> query = em.createQuery(
                        "SELECT c.customerID FROM Customer c ORDER BY c.customerID DESC", String.class);
                query.setMaxResults(1);
                lastId = query.getSingleResult();
            }catch(NoResultException e)
            {
                return "USER0000";
            }
            int number = Integer.parseInt(lastId.substring(4));
            number++; // Tăng giá trị số lên 1
            String newId = String.format("USER%04d", number);
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

    public static void insertCustomer(Customer customer)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(customer);
        trans.commit();
        em.close();
    }

    public static void updateCustomer(String customerID, String customerName, Date dob, String gender, String password, String address, String email, String phoneNum, Integer isAdmin, String cardNum, StringBuilder error)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try
        {
            Customer customer = em.find(Customer.class, customerID);
            try
            {
                trans.begin();
                customer.setCustomerName(customerName);
                customer.setBirthday(dob);
                customer.setGender(gender);
                customer.setPassword(password);
                customer.setAddress(address);
                customer.setEmail(email);
                customer.setPhoneNum(phoneNum);
                customer.setAdmin(isAdmin);
                customer.setCardNum(cardNum);
                em.merge(customer);
                trans.commit();
            }catch(NoResultException e)
            {
                trans.rollback();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("CAN NOT ADD", e);
        }
        finally {
            em.close();
        }
    }

    public static List<Customer> getCustomerList(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT c FROM Customer c ORDER BY c.customerID ASC";
            Query query = em.createQuery(queryString, Customer.class);
            List<Customer> rows = query.getResultList();
            return rows;
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CANNOT GET Customer", e);
        }
        finally {
            em.close();
        }
    }
    public static Customer findCustomer(String email){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            String queryString = "SELECT c FROM Customer c where c.email = :email";
            Query query = em.createQuery(queryString, Customer.class);
            query.setParameter("email", email);
            try
            {
                Customer a = (Customer) query.getSingleResult();
                return a;
            }catch(NoResultException e)
            {
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("CANNOT GET Customer", e);
        }
        finally {
            em.close();
        }
    }


}
