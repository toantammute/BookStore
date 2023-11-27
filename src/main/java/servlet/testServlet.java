package servlet;
import java.io.*;
import java.util.List;

import data.AuthorDB;
import data.CategoryDB;
import data.PublisherDB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Author;
import model.Category;
import model.Publisher;

@WebServlet("/test")
public class testServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/test.jsp";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            url = "/test.jsp";  // default action
        }

        // perform action and set URL to appropriate page

        else if (action.equals("add_cate")) {
            StringBuilder error = new StringBuilder();
            String search = request.getParameter("test");
            PublisherDB.updatePublisher("PUBL0001","HAHA",error);
            request.setAttribute("message",error);
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