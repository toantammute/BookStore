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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String url = "/shop.jsp";
        ServletContext sc = getServletContext();
        String action = request.getParameter("action");
        if (action.equals("shop")) {
            url = "/shop.jsp";
        }
        else if (action.equals("cart")) {
            EntityTransaction trans = em.getTransaction();
            trans.begin();

            String bookID = request.getParameter("bookID");
            Book book = em.find(Book.class, bookID);

            // tao lineitem moi
            LineItem lineItem = new LineItem();
            lineItem.setLineItemID(LineItemDB.generateId());
            lineItem.setItem(book); // 1
            Integer quantity = 1;
            lineItem.setQuantity(quantity);


            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            Checkout checkout = em.find(Checkout.class, customer.getCustomerID());

            checkout = CheckoutDB.addItem(checkout,lineItem);
            List<LineItem> lineItemList = checkout.getLineItemList();
            session.setAttribute("listlineitem", lineItemList);
            url = "/checkout.jsp";

        }
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