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

@WebServlet("/editbook")
public class EditBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        String url = "/booktable.jsp";
        ServletContext sc = getServletContext();

        String action = request.getParameter("action");
        if(action.equals("clickEdit"))
        {
            String bookID = request.getParameter("bookID");
            Book book = em.find(Book.class, bookID);
            Stock stock = em.find(Stock.class, book.getBookID());
            HttpSession session = request.getSession();
            session.setAttribute("editbook",book);
            session.setAttribute("editstock",stock);
            url = "/editbook.jsp";
        }
        else if(action.equals("editbook"))
        {
            String importPriceString = request.getParameter("bookImportPrice");
            Double importPrice = Double.parseDouble(importPriceString);
            String quantityString  = request.getParameter("bookQuantity");
            Integer quantity = Integer.parseInt(quantityString);
            Double sellPrice = importPrice * 1.5;
            String description = request.getParameter("bookDescription");
            HttpSession session = request.getSession();
            Book book = (Book) session.getAttribute("editbook");
            Stock stock = (Stock) session.getAttribute("editstock");
            book.setPrice(sellPrice);
            book.setDescription(description);
            stock.setQuantity(quantity);
            stock.setBook(book);
            stock.setImportPrice(importPrice);
            BookDB.updateBook(book);
            StockDB.updateStock(stock);
            url = "/booktable.jsp";
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