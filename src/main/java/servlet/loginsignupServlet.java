package servlet;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.CustomerDB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Customer;

@WebServlet("/login")
public class loginsignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

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
                    session.setAttribute("user",customer);
                    url = "/shop.jsp";
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
        else if (action.equals("signup")) {
            Customer customer = new Customer();
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
            String email = request.getParameter("email");
            customer.setEmail(email);
            String phoneNum = request.getParameter("phoneNumber");
            customer.setPhoneNum(phoneNum);
            CustomerDB.insertCustomer(customer);
            customer.setAdmin(0);
            url = "/login.jsp";
        }
        else if (action.equals("logout")) {
            HttpSession session = request.getSession();
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