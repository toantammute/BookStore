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
            List<Book> books = BookDB.getAllBook();
            if(books.size() != 0)
            {
                request.setAttribute("books",books);
            }
            url = "/shop.jsp";
        }
        else if(action.equals("add_book"))
        {
            String bookName = request.getParameter("bookName");
            String priceString = request.getParameter("price");
            Integer price = Integer.parseInt(priceString);
            Book book = new Book();
            Part img = request.getPart("imageBookFront");
            InputStream imageInputStream = img.getInputStream();
            byte[] imageData = imageInputStream.readAllBytes();
            book.setImageBookFront(imageData);
            Part img1 = request.getPart("imageBookBack");
            InputStream imageInputStream1 = img1.getInputStream();
            byte[] imageData1 = imageInputStream1.readAllBytes();
            book.setImageBookBack(imageData1);
            book.setBookID(BookDB.generateId());
            book.setBookName(bookName);
            book.setPrice(price);
            BookDB.insertBook(book);
            request.setAttribute("book",book);
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