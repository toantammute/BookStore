package servlet;
import java.io.*;
import java.util.List;

import data.AuthorDB;
import data.CategoryDB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Author;
import model.Category;

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
            String author_name = request.getParameter("author_name");
            String author_id = "AUTH0010";
            List<Author> authors = AuthorDB.searchAuthor(author_name,error);
            if(authors != null)
            {
                request.setAttribute("authors",authors);
            }
            request.setAttribute("message",error);
            AuthorDB.updateAuthor("AUTH0010","changed",error);
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