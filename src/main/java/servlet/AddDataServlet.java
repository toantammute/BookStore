package servlet;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import data.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

@WebServlet("/add")
public class AddDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        String url="";
        ServletContext sc = getServletContext();


        String action = request.getParameter("action");
        if(action.equals("addnewcategory"))
        {
            trans.begin();
            Category category = new Category();
            String categoryname = request.getParameter("categoryName");
            category.setCategoryID(CategoryDB.generateId());
            category.setCategoryName(categoryname);
            CategoryDB.insertCategory(category);
            trans.commit();
            url = "/categorytable.jsp";
        }
        else if(action.equals("addnewauthor"))
        {
            trans.begin();
            Author author = new Author();
            String authorname = request.getParameter("authorName");
            author.setAuthorID(AuthorDB.generateId());
            author.setAuthorName(authorname);
            AuthorDB.insertAuthor(author);
            trans.commit();
            url = "/authortable.jsp";
        }
        else if(action.equals("addnewpublisher"))
        {
            trans.begin();
            Publisher publisher = new Publisher();
            String publisherName = request.getParameter("publisherName");
            publisher.setPublisherID(PublisherDB.generateId());
            publisher.setPublisherName(publisherName);
            PublisherDB.insertPublisher(publisher);
            trans.commit();
            url = "/publishertable.jsp";
        }
        else if(action.equals("addnewuser"))
        {
            trans.begin();
            String customerEmail = request.getParameter("email");

            Customer customer = CustomerDB.findCustomer(customerEmail);
            if(customer != null ) {
                request.setAttribute("message", "EMAIL EXISTED");
                url = "/addnewuser.jsp";
            }
            else
            {
                customer = new Customer();
                customer.setCustomerID(CustomerDB.generateId());
                customer.setEmail(customerEmail);
                String customerName = request.getParameter("customerName");
                customer.setCustomerName(customerName);

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
                customer.setBirthday(dob);


                String customerPassword = request.getParameter("password");
                customer.setPassword(customerPassword);

                String customerGender = request.getParameter("gender");
                customer.setGender(customerGender);

                String customerAddress = request.getParameter("address");
                customer.setAddress(customerAddress);

                String customerPhoneNum = request.getParameter("phoneNum");
                customer.setPhoneNum(customerPhoneNum);

                String customerisAdmin = request.getParameter("isAdmin");
                if(customerisAdmin.equals("true")) customer.setAdmin(1);
                else customer.setAdmin(0);
                CustomerDB.insertCustomer(customer);
                Cart cart = new Cart();
                cart.setCustomer(customer);
                CartDB.addNewCart(cart);
                Checkout checkout = new Checkout();
                checkout.setCustomer(customer);
                CheckoutDB.addNewChekout(checkout);
                trans.commit();
                url = "/accounttable.jsp";
            }
        }
        else if(action.equals("addnewbook"))
        {
            trans.begin();
            Book book = new Book();
            book.setBookID(BookDB.generateId());
            String bookName = request.getParameter("bookName");
            book.setBookName(bookName);
            String language = request.getParameter("bookLanguage");
            book.setLanguage(language);
            String importPriceString = request.getParameter("bookImportPrice");
            Double importPrice = 0D;
            try
            {
                importPrice = Double.parseDouble(importPriceString);
            }catch (Exception e){}
            String quantityString = request.getParameter("bookQuantity");
            int quantity= 1;
            try
            {
                quantity = Integer.parseInt(quantityString);
            }catch (Exception e){}
            book.setPrice(importPrice*150);
            Stock stock = new Stock();
            stock.setBook(book);
            stock.setQuantity(quantity);
            stock.setImportPrice(importPrice);
            em.persist(stock);

            String description = request.getParameter("bookDescription");
            book.setDescription(description);

            String dateString = request.getParameter("bookPublishYear");
            Date publishYear = null;
            if (dateString != null && !dateString.isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    publishYear = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            book.setPublisherYear(publishYear);

            String publisherID = request.getParameter("bookPublisher");
            Publisher publisher = em.find(Publisher.class, publisherID);
            book.setPublisher(publisher);

            String categoryID = request.getParameter("bookCategory");
            Category category  = em.find(Category.class, categoryID);
            book.setCategory(category);

            String[] authors = request.getParameterValues("bookAuthor");
            for(String authorID : authors)
            {
                Author author = new Author();
                author = em.find(Author.class, authorID);
                book.getAuthor().add(author);
                author.getBook().add(book);
            }
            BookDB.insertBook(book);
            //PublisherDB.insertPublisher(publisher);
            trans.commit();
            url = "/booktable.jsp";
        }


        sc.getRequestDispatcher(url).forward(request,response);

    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}