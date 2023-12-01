package servlet;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import data.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        String url = "/thanks.jsp";
        ServletContext sc = getServletContext();
        Invoice invoice = new Invoice();
        invoice.setInvoiceID(InvoiceDB.generateId());
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        invoice.setCustomer(customer);

        String address = request.getParameter("address");
        invoice.setAddress(address);

        String phoneNum = request.getParameter("phoneNum");
        session.setAttribute("phoneNum", phoneNum);

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date orderDate = new Date();
        invoice.setOrderDate(orderDate);
        String orderDateString = outputFormat.format(orderDate);
        session.setAttribute("orderDate",orderDateString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderDate);
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date deliveryDate = calendar.getTime();
        String deliveryDateString = outputFormat.format(deliveryDate);
        session.setAttribute("deliveryDate",deliveryDateString);
        invoice.setDeliDate(deliveryDate);

        String payment = request.getParameter("payment");
        if(payment.equals("cash"))
        {
            invoice.setStatus("UNPAID");
        }
        else invoice.setStatus("PAID");

        String totalAmount = CheckoutDB.getTotalCurrencyFormat(customer);
        invoice.setTotalAmount(totalAmount);


        String totalPay = CheckoutDB.getTotalDiscountCurrencyFormat(customer);
        invoice.setTotalPay(totalPay);

        Integer discount = CheckoutDB.getDiscount(customer);
        invoice.setDiscount(discount);


        Checkout checkout = em.find(Checkout.class, customer.getCustomerID());

        List<LineItem> lineItemList = checkout.getLineItemList();
        session.setAttribute("listlineitem",lineItemList);
        CheckoutDB.removeCheckout(customer);
        invoice.setLineItem(lineItemList);
        em.persist(invoice);
        trans.commit();
        session.setAttribute("invoice",invoice);
        em.close();
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