package servlet;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import data.CartDB;
import data.CheckoutDB;
import data.CustomerDB;
import data.DBUtil;
import jakarta.persistence.EntityManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Book;
import model.Cart;
import model.Checkout;
import model.Customer;

@WebServlet("/login")
public class LogInSignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String url = "/shop.jsp";
        ServletContext sc = getServletContext();
        String action = request.getParameter("action");
        if (action == null) {
            action = "cart";
        }
        if (action.equals("shop")) {
            url = "/index.jsp";
        }
        else if (action.equals("login")) {

            String email = request.getParameter("email");
            String password = request.getParameter("pass");
            Customer customer = CustomerDB.findCustomer(email);
            if(customer != null)
            {
                if(customer.getPassword().equals(password))
                {
                    HttpSession session = request.getSession();
                    session.setAttribute("customer",customer);
                    Cart cart = em.find(Cart.class, customer.getCustomerID());
                    if(cart == null)
                    {
                        cart = new Cart();
                        cart.setCustomer(customer);
                        CartDB.addNewCart(cart);
                    }
                    String rememberMe = request.getParameter("cookie");
                    if((rememberMe != null)&&rememberMe.equals("checked"))
                    {
                        Cookie[] cookies = request.getCookies();
                        for (Cookie cookie : cookies) {
                            cookie.setMaxAge(0);
                        }
                        Cookie emailCookie = new Cookie("email", email);
                        Cookie passwordCookie = new Cookie("password", password);
                        int maxAge = 30 * 24 * 60 * 60; // Số giây
                        emailCookie.setMaxAge(maxAge);
                        passwordCookie.setMaxAge(maxAge);
                        response.addCookie(emailCookie);
                        response.addCookie(passwordCookie);
                    }
                    /*
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                    response.setHeader("Expires", "0"); // Proxies.
                    */
                    if(customer.getAdmin() == 1)
                    {
                        url = "/adminpage.jsp";
                    }
                    else
                    {
                        List<Book> bookcart = cart.getBook();
                        session.setAttribute("bookcart",bookcart);
                        url = "/shop.jsp";
                    }
                }
                else
                {
                    url = "/login.jsp";
                    String wrong = "WRONG PASSWORD !";
                    request.setAttribute("wrongpassword", wrong);
                }
            }
            else
            {
                url = "/login.jsp";
                String wrong = "ACCOUNT IS NOT EXIST";
                request.setAttribute("wrongpassword", wrong);
            }

        }
        else if (action.equals("signup"))
        {
            String email = request.getParameter("email");
            Customer customer = CustomerDB.findCustomer(email);
            if(customer != null ) {
                request.setAttribute("message", "EMAIL EXISTED");
                url = "/signup.jsp";

            }
            else
            {
                customer = new Customer();
                customer.setEmail(email);
                customer.setCustomerID(CustomerDB.generateId());
                String name = request.getParameter("name");
                customer.setCustomerName(name);
                String dateString = request.getParameter("date");
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
                String gender = request.getParameter("gender");
                customer.setGender(gender);
                String password = request.getParameter("password");
                customer.setPassword(password);
                String address = request.getParameter("address");
                customer.setAddress(address);
                String phoneNum = request.getParameter("phoneNumber");
                customer.setPhoneNum(phoneNum);
                customer.setAdmin(0);
                CustomerDB.insertCustomer(customer);
                Cart cart = new Cart();
                cart.setCustomer(customer);
                CartDB.addNewCart(cart);

                Checkout checkout = new Checkout();
                checkout.setCustomer(customer);
                CheckoutDB.addNewChekout(checkout);
                url = "/login.jsp";
            }
        }
        else if (action.equals("logout")) {

            HttpSession session = request.getSession();
            session.removeAttribute("customer");
            // Xóa giỏ hàng hiện tại từ session
            session.invalidate();
            url = "/shop.jsp";
        }
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