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
            // get parameters from the request
            String categoryName = request.getParameter("category_name");

            // store data in User object
            Category category = new Category();
            category.setCategoryID(CategoryDB.generateId());
            category.setCategoryName(categoryName);
            CategoryDB.insertCategory(category);
            // validate the parameters

            List<Category> categories = CategoryDB.getCategoryList();
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