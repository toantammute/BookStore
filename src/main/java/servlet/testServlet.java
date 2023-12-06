package servlet;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import data.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Author;
import model.Book;
import model.Category;
import model.Publisher;

@WebServlet("/test")
@MultipartConfig()
public class testServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/shop.jsp";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            url = "/shop.jsp";  // default action
        }

        // perform action and set URL to appropriate page

        else if (action.equals("shop")) {
            List<Book> books = BookDB.getAllBook();
            if(books.size() != 0)
            {
                request.setAttribute("books",books);
            }
            url = "/shop.jsp";
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}