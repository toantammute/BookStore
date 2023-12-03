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


        sc.getRequestDispatcher(url).forward(request,response);

    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}