package servlet;
import java.io.*;
import java.util.List;

import data.CategoryDB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
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
            //CategoryDB.updateCategory("CATE0001","Soccer");
            String categoryName = request.getParameter("category_name");
            request.setAttribute("categoryName",categoryName);
            List<Category> categories = CategoryDB.searchCategory(categoryName);
            if(categories.size() == 0)
            {
                String message = "Does not exist";
                request.setAttribute("message",message);
            }
            request.setAttribute("categories",categories);


        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}