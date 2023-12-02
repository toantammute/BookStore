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

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        String url = "/account.jsp";
        ServletContext sc = getServletContext();

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        String changepassword = request.getParameter("changepassword");
        if(changepassword == null)
        {
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String cardNumber = request.getParameter("cardNumber");

            customer.setAddress(address);
            customer.setPhoneNum(phoneNumber);
            customer.setCardNum(cardNumber);

            CustomerDB.updateCustomer(customer);
            transaction.commit();
            url = "/accountsave.jsp";
        }
        else
        {
            url = "/changepassword.jsp";
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