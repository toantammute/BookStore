<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="data.CheckoutDB" %>
<%@ page import="model.Customer" %>
<%@ page import="data.CartDB" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="description" content="">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- The above 4 meta tags must come first in the head; any other head content must come after these tags -->

  <!-- Title  -->
  <title>BOOKSTORE ONLINE | Favourite</title>

  <!-- Favicon  -->
  <link rel="icon" href="img/core-img/favicon.ico">

  <!-- Core Style CSS -->
  <link rel="stylesheet" href="css/core-style.css">
  <link rel="stylesheet" href="style.css">

</head>

<body>
<!-- Search Wrapper Area Start -->
<div class="search-wrapper section-padding-100">
  <div class="search-close">
    <i class="fa fa-close" aria-hidden="true"></i>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="search-content">
          <form action="#" method="get">
            <input type="search" name="search" id="search" placeholder="Type your keyword...">
            <button type="submit"><img src="img/core-img/search.png" alt=""></button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- Search Wrapper Area End -->

<!-- ##### Main Content Wrapper Start ##### -->
<div class="main-content-wrapper d-flex clearfix">

  <!-- Mobile Nav (max width 767px)-->
  <div class="mobile-nav">
    <!-- Navbar Brand -->
    <div class="amado-navbar-brand">
      <a href="shop.jsp"><img src="img/core-img/logo.png" alt=""></a>
    </div>
    <!-- Navbar Toggler -->
    <div class="amado-navbar-toggler">
      <span></span><span></span><span></span>
    </div>
  </div>

  <!-- Header Area Start -->
  <header class="header-area clearfix">
    <!-- Close Icon -->
    <div class="nav-close">
      <i class="fa fa-close" aria-hidden="true"></i>
    </div>
    <!-- Logo -->
    <div class="logo">
      <a href="shop.jsp"><img src="img/core-img/logo.png" alt=""></a>
    </div>
    <!-- Amado Nav -->
    <nav class="amado-nav">
      <ul>
        <c:if test="${not empty customer}">
          <p>Hello, ${customer.customerName} !</p>
          <li><a href="account.jsp">ACCOUNT</a></li>
          <li><a href="login?action=logout">LOG OUT</a></li>
        </c:if>
        <c:if test="${empty customer}">
          <li><a href="login.jsp">LOGIN</a></li>
        </c:if>
        <li><a href="test?action=shop">Shop</a></li>
      </ul>
    </nav>
    <!-- Button Group -->
    <%--            <div class="amado-btn-group mt-30 mb-100">--%>
    <%--                <a href="#" class="btn amado-btn mb-15">%Discount%</a>--%>
    <%--                <a href="#" class="btn amado-btn active">New this week</a>--%>
    <%--            </div>--%>
    <!-- Cart Menu -->
    <%
      Integer size = 0;
      try{size = CheckoutDB.getSize((Customer) session.getAttribute("customer"));}
      catch (Exception e) {
        size = 0;
      }
    %>
    <div class="cart-fav-search mb-100">
      <a href="checkout.jsp" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart <span>(<%=size%>)</span></a>
      <a href="cart.jsp" class="fav-nav"><img src="img/core-img/favorites.png" alt=""> Favourite</a>
    </div>
    <!-- Social Button -->
    <div class="social-info d-flex justify-content-between">
      <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
      <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
      <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
      <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
    </div>
  </header>
  <!-- Header Area End -->

  <div class="cart-table-area section-padding-100">
    <div class="container-fluid">
      <div class="row">
        <div class="col-12 col-lg-8">
          <div class="cart-title mt-50">
            <h2>Shopping Favourite</h2>
          </div>

          <div class="cart-table clearfix">
            <table class="table table-responsive">
              <thead>
              <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th> </th>
              </tr>
              </thead>
              <tbody>
              <% List<Book> bookcart = CartDB.getAllCart((Customer) session.getAttribute("customer"));
                session.setAttribute("bookcart",bookcart);
              %>
              <c:forEach var="book" items="${bookcart}">
                <tr>
                  <td class="cart_product_img">
                    <!--< a href="#"><img src="img/bg-img/cart1.jpg" alt="Product"></a> -->
                    <a href="product_details?bookID=${book.bookID}"><img src="img/product-img/img.png" alt="Product"></a>
                  </td>
                  <td class="cart_product_desc">
                    <h5>${book.bookName}</h5>
                  </td>
                  <td class="price">
                    <span>${book.priceFormat}</span>
                  </td>
                  <td class="qty">
                    <form action="cart" method="post">
                      <input type="hidden" name="action" value="cart">
                      <input type="hidden" name="bookID" value="${book.bookID}">
                      <input type="submit" value="Add To Cart">
                    </form>
                    <br>
                    <form action="cart" method="post">
                      <input type="hidden" name="action" value="removefromfavorite">
                      <input type="hidden" name="bookID" value="${book.bookID}">
                      <input type="submit" value="Remove">
                    </form>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- ##### Main Content Wrapper End ##### -->
</div>
  <!-- ##### Footer Area Start ##### -->
  <footer class="footer_area clearfix">
    <div class="container">
      <div class="row align-items-center">
        <!-- Single Widget Area -->
        <div class="col-12 col-lg-4">
          <div class="single_widget_area">
            <!-- Logo -->
            <div class="footer-logo mr-50">
              <a href="shop.jsp"><img src="img/core-img/logo2.png" alt=""></a>
            </div>
            <!-- Copywrite Text -->
            <p class="copywrite"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
              Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved
            </p>
          </div>
        </div>
        <!-- Single Widget Area -->
        <div class="col-12 col-lg-8">
          <div class="single_widget_area">
            <!-- Footer Menu -->
            <div class="footer_menu">
              <nav class="navbar navbar-expand-lg justify-content-end">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#footerNavContent" aria-controls="footerNavContent" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars"></i></button>
                <div class="collapse navbar-collapse" id="footerNavContent">
                  <ul class="navbar-nav ml-auto">
                    <%--                                        <li class="nav-item active">--%>
                    <%--                                            <a class="nav-link" href="login.jsp">Home</a>--%>
                    <%--
                                                      </li>--%>

                    <li class="nav-item">
                      <a class="nav-link" href="shop.jsp">Shop</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="cart.jsp">Favorite</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="checkout.jsp">Cart</a>
                    </li>
                  </ul>
                </div>
              </nav>
            </div>
          </div>
        </div>
      </div>
    </div>
  </footer>
  <!-- ##### Footer Area End ##### -->

  <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
  <script src="js/jquery/jquery-2.2.4.min.js"></script>
  <!-- Popper js -->
  <script src="js/popper.min.js"></script>
  <!-- Bootstrap js -->
  <script src="js/bootstrap.min.js"></script>
  <!-- Plugins js -->
  <script src="js/plugins.js"></script>
  <!-- Active js -->
  <script src="js/active.js"></script>

</body>

</html>