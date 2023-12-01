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

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        String url = "/shop.jsp";
        ServletContext sc = getServletContext();
        String action = request.getParameter("action");
        if (action == null) {
            action = "cart";
        }
        if (action.equals("shop")) {
            url = "/index.jsp";
        }
        else if (action.equals("search")) {
            String search = request.getParameter("search");
            List<Book> books = BookDB.searchBook(search);
            request.setAttribute("books",books);
            url = "/shop.jsp";
        }
        else if(action.equals("checkUser"))
        {
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            if(customer == null)
            {
                url = "/login.jsp";
            }
            else
            {
                String aim = request.getParameter("aim");
                if(aim.equals("addtofavorite"))
                {
                    trans.begin();
                    String bookID = request.getParameter("bookID");
                    Cart cart = em.find(Cart.class, customer.getCustomerID());
                    int flag = CartDB.checkListBook(cart, em.find(Book.class,bookID));
                    if(flag == 1)
                    {
                        cart.getBook().add(em.find(Book.class, bookID));
                        List<Book> books = cart.getBook();
                        session.setAttribute("bookcart", books);
                        trans.commit();
                    }
                    url = "/shop.jsp";

                }
                else if(aim.equals("addtocart"))
                {
                    trans.begin();

                    String bookID = request.getParameter("bookID");
                    Book book = em.find(Book.class, bookID);

                    // tao lineitem moi
                    LineItem lineItem = new LineItem();
                    lineItem.setLineItemID(LineItemDB.generateId());
                    lineItem.setItem(book); // 1
                    Integer quantity = 1;
                    lineItem.setQuantity(quantity);

                    customer = (Customer) session.getAttribute("customer");
                    Checkout checkout = em.find(Checkout.class, customer.getCustomerID());

                    checkout = CheckoutDB.addItem(checkout,lineItem);
                    List<LineItem> lineItemList = checkout.getLineItemList();
                    session.setAttribute("listlineitem", lineItemList);
                    url = "/checkout.jsp";
                }
            }
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