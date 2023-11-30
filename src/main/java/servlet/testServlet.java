package servlet;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import data.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Author;
import model.Category;
import model.Publisher;

@WebServlet("/test")
@MultipartConfig()
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
            /*
                <input type="text" name="book_name">
                <input type="number" name="price">
                <input type="text" name="description">
                <input type="text" name="language">
                <input type="date" name="publishYear">
                <input type="file" name="imageBook">
                <input placeholder="Author" type="text" class="form-control" id="author_name" name="author_name">
                <input placeholder="Publisher" type="text" class="form-control" id="publisher_name" name="publisher_name">
                <input placeholder="Category" type="text" class="form-control" id="category_name" name="category_name">
                <input type="number" name="quantity">

            String book_name = request.getParameter("book_name");
            String priceString = request.getParameter("price");
            Integer price = 0;
            if(priceString != null)
            {
                price = Integer.parseInt(priceString);
            }
            String description = request.getParameter("description");
            String language = request.getParameter("language");
            String dateString = request.getParameter("publishYear");
            Date publishYear = null;
            if (dateString != null && !dateString.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    publishYear = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            Part imageBook = request.getPart("imageBook");
            InputStream imageInputStream = imageBook.getInputStream();
            byte[] imageData = imageInputStream.readAllBytes();
            String author_name = request.getParameter("author_name");
            String publisher_name = request.getParameter("publisher_name");
            String category_name = request.getParameter("category_name");
            String quantityString = request.getParameter("quantity");
            Integer quantity = 0;
            try
            {
                quantity = Integer.parseInt(quantityString);
            }catch (Exception e)
            {
                System.out.println(e);
            }
            StringBuilder error = new StringBuilder();
            BookDB.insertBook(book_name, price, description, language, publishYear, imageData, publisher_name, category_name, author_name, quantity,error);
*/
            String customerName = request.getParameter("customerName");
            String dateString = request.getParameter("dob");
            Date dob = null;
            if (dateString != null && !dateString.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dob = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            String genderString = request.getParameter("gender");
            Integer gender = 0;
            try
            {
                gender = Integer.parseInt(genderString);
            }catch(Exception e)
            {
                System.out.println(e);
            }
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phoneNum = request.getParameter("phoneNum");
            String isAdminString = request.getParameter("isAdmin");
            Integer isAdmin = 0;
            try
            {
                isAdmin = Integer.parseInt(isAdminString);
            }catch(Exception e)
            {
                System.out.println(e);
            }
            String cardNum = request.getParameter("cardNum");
            StringBuilder error = new StringBuilder();

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