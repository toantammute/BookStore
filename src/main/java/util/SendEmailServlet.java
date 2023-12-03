package util;

import data.CustomerDB;
import data.DBUtil;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;
import model.Invoice;
import model.LineItem;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@WebServlet("/sendEmail")
public class SendEmailServlet extends HttpServlet {

    // qkfq xwlj aprw zdan

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        String url = "/sendemaildone.jsp";
        HttpSession session1 = request.getSession();
        String buy = (String) session1.getAttribute("buy");
        if(buy != null && buy.equals("true"))
        {
            url = "/sendemailbill.jsp";
            final String username = "anhphamhoang033@gmail.com";
            final String password = "qkfq xwlj aprw zdan";
            Invoice invoice = (Invoice) session1.getAttribute("invoice");
            Properties properties = new Properties();
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.put("mail.smtp.port","587");
            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.starttls.enable","true");

            Session session = Session.getDefaultInstance(properties, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username,password);
                }
            });

            // dang nhap email
            String to = invoice.getCustomer().getEmail();
            String emailSubject = "Thank You For Supporting, "+invoice.getCustomer().getCustomerName();
            String content = "<html><body><h3>Hi, "+invoice.getCustomer().getCustomerName()+" ! </h3><br>";
            content += "<span>Here is your bill information : </span><br>";
            content += "<span>BILL ID : </span><strong>" + invoice.getInvoiceID()+"</strong>";
            content += "<table style='width:60%;'>";
            content += "<tr><th>Book Name</th><th>Quantity</th><th>Unit Price</th><th>Total Price</th></tr>";
            List<LineItem> lineItems = invoice.getLineItem();
            for (LineItem lineItem : lineItems) {
                String itemName = lineItem.getItem().getBookName();
                int quantity = lineItem.getQuantity();
                String price = lineItem.getUnitPrice();
                String totalprice = lineItem.getTotalPrice();

                // Create a row for each line item
                String lineItemRow = "<tr><td>" + itemName + "</td><td>" + quantity + "</td><td>" + price + "</td><td>" + totalprice + "</td></tr>";
                content += lineItemRow;
            }
            content += "</table><br>";
            content += "<span>Total Amount :</span> " + invoice.getTotalAmount() + "<br>";
            content += "<span>Discount :</span> " + invoice.getDiscount() + "%<br>";
            content += "<span>Total Pay :</span><strong> " + invoice.getTotalPay()+"</strong>";
            content += "</body></html>";
            String emailContent = content;
            try
            {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(emailSubject);
                message.setContent(emailContent, "text/html");
                Transport.send(message);
            }catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else
        {
            String email = request.getParameter("email");
            List<String> listEmail = CustomerDB.getEmailCustomerList();
            int flag = 0;
            String new_password = generateRandomPassword();
            for (var useremail: listEmail) {
                if(useremail.equals(email))
                {
                    flag = 1;

                    EntityManager em = DBUtil.getEmFactory().createEntityManager();
                    EntityTransaction trans = em.getTransaction();
                    trans.begin();
                    Customer customer = CustomerDB.findCustomer(useremail);
                    customer.setPassword(new_password);
                    CustomerDB.updateCustomer(customer);
                    em.merge(customer);
                    trans.commit();
                    em.close();
                    break;
                }
            }
            if(flag == 1)
            {
                url = "/sendemaildone.jsp";
                final String username = "anhphamhoang033@gmail.com";
                final String password = "qkfq xwlj aprw zdan";

                Properties properties = new Properties();
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");

                Session session = Session.getDefaultInstance(properties, new Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

                // dang nhap email
                String to = email;
                String emailSubject = "Your New Password For BookStore Online";
                String content = "<h3><strong><span>***</span> PLEASE DO NOT SHARE WITH ANYONE <span>***</span></strong></h3><br>" +
                        "YOUR NEW PASSWORD: <div style=\"border: 1px solid black; padding: 5px; display: inline-block;\"><strong>" + new_password + "</strong></div>\n" +
                        "<h3><strong>YOU CAN CHANGE THIS PASSWORD IN YOUR ACCOUNT INFORMATION</strong></h3><br>";
                String emailContent = content;
                try
                {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    message.setSubject(emailSubject);
                    message.setContent(emailContent, "text/html");
                    Transport.send(message);
                }catch(Exception e)
                {
                    System.out.println(e);
                }
            }
            else
            {
                request.setAttribute("wrongemail",new String("EMAIL DOES NOT EXIST"));
                url = "/resetpassword.jsp";
            }
        }
        sc.getRequestDispatcher(url)
                .forward(request, response);
    }

    private String generateRandomPassword() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
