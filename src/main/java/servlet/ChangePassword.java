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

@WebServlet("/change-password")
public class ChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        String url = "/changepasswordsave.jsp";
        ServletContext sc = getServletContext();

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        String oldpass = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        if(oldpass.equals(customer.getPassword()))
        {
            customer.setPassword(newpassword);
            CustomerDB.updateCustomer(customer);
            session.setAttribute("flag",new String("1"));
        }else
        {
            session.setAttribute("flag",new String("0"));
        }
        transaction.commit();
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