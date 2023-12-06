package servlet;
import java.io.*;
import java.util.List;

import data.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        String url = "/accounttable.jsp";
        ServletContext sc = getServletContext();

        String action = request.getParameter("action");
        if(action.equals("clickEdit"))
        {
            String customerID = request.getParameter("customerID");
            Customer editcustomer = em.find(Customer.class, customerID);
            HttpSession session = request.getSession();
            session.setAttribute("editcustomer",editcustomer);
            url = "/edituser.jsp";
        }
        else if(action.equals("edituser"))
        {
            String username = request.getParameter("customerName");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String phoneNum = request.getParameter("phoneNum");
            String cardNum = request.getParameter("cardNum");
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("editcustomer");
            customer.setCustomerName(username);
            customer.setPassword(password);
            customer.setAddress(address);
            customer.setPhoneNum(phoneNum);
            customer.setCardNum(cardNum);
            CustomerDB.updateCustomer(customer);
            url = "/accounttable.jsp";
        }

        em.close();


        sc.getRequestDispatcher(url)
                .forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}