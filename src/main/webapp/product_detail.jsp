<%@ page import="model.Customer" %>
<%@ page import="data.CheckoutDB" %>
<%@ page import="model.Book" %>
<%@ page import="jakarta.persistence.EntityTransaction" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="data.DBUtil" %>
<%@ page import="model.Stock" %><%--
  Created by IntelliJ IDEA.
  User: hoanganh033
  Date: 02/12/2023
  Time: 03:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>BOOKSTORE ONLINE | Product Detail</title>

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
        <!-- Cart Menu -->
        <%
            Integer size = 0;
            try{size = CheckoutDB.getSize((Customer) session.getAttribute("customer"));}
            catch (Exception e) {
                size = 0;
            }
        %>
        <div class="cart-fav-search mb-100">
            <% String url = "checkout.jsp";
                String url1 = "cart.jsp";
                Customer customer = (Customer) session.getAttribute("customer");
                if(customer == null)
                {
                    url = "login.jsp";
                    url1 = "login.jsp";
                }
            %>
            <a href="<%=url%>" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart <span>(<%=size%>)</span></a>
            <a href="<%=url1%>" class="fav-nav"><img src="img/core-img/favorites.png" alt=""> Favourite</a>
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

    <!-- Product Details Area Start -->
    <div class="single-product-area section-padding-100 clearfix">
        <div class="container-fluid">

            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mt-50">
                            <li class="breadcrumb-item"><a href="shop.jsp">Shop</a></li>
                            <li class="breadcrumb-item active" aria-current="page">${book_detail.bookName}</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="row">
                <div class="col-12 col-lg-7">
                    <div class="single_product_thumb">
                        <div id="product_details_slider" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li class="active" data-target="#product_details_slider" data-slide-to="0" style="background-image: url(img/product-img/img.png);">
                                </li>
                                <li data-target="#product_details_slider" data-slide-to="1" style="background-image: url(img/product-img/img_1.png);">
                                </li>

                            </ol>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <a class="gallery_img" href="img/product-img/img.png">
                                        <img class="d-block w-100" src="img/product-img/img.png" alt="First slide">
                                    </a>
                                </div>
                                <div class="carousel-item">
                                    <a class="gallery_img" href="img/product-img/img_1.png">
                                        <img class="d-block w-100" src="img/product-img/img_1.png" alt="Second slide">
                                    </a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-lg-5">
                    <div class="single_product_desc">
                        <!-- Product Meta Data -->
                        <div class="product-meta-data">
                            <div class="line"></div>
                            <p class="product-price" style="font-size: 30px">${book_detail.priceFormat}</p>
                            <a href="product_details?bookID=${book_detail.bookID}">
                                <h2>${book_detail.bookName}</h2>
                            </a>
                            <!-- Ratings & Review -->
                            <div class="ratings-review mb-15 d-flex align-items-center justify-content-between">
                                <div class="ratings">
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                </div>
                            </div>
                            <!-- Avaiable -->
                            <% Book book = (Book) session.getAttribute("book_detail");
                                EntityManager em = DBUtil.getEmFactory().createEntityManager();
                                Stock stock = em.find(Stock.class, book.getBookID());
                                if(stock.getQuantity() > 0)
                                {%>
                                <p style="color: #20d34a"><i class="fa fa-circle"></i> In Stock</p>
                                <%}else{%>
                                <p style="color: red"><i class="fa fa-circle"></i> Sold Out</p>
                                <%}%>

                        </div>

                        <div class="short_overview my-5">
                            <p style="font-size: 25px; color: #0b0b0b"><strong>Description</strong></p>
                            <p><i>${book_detail.description}</i></p>
                        </div>

                        <div class="short_overview my-5">
                            <p><strong>Language : </strong>${book_detail.language}</p>
                        </div>
                        <div class="short_overview my-5">
                            <p><strong>Author Name : </strong>
                            <c:forEach var="author" items="${authors}">
                                <a style="font-size: 17px; color: grey" href="shop?action=seachbyauthor&amp;authorid=${author.authorID}">${author.authorName}, </a>
                            </c:forEach>
                            </p>
                        </div>
                        <div class="short_overview my-5">
                            <p><strong>Category : </strong><a style="font-size: 17px; color: grey" href="shop?action=seachbycategory&amp;categoryid=${category.categoryID}">${category.categoryName}</a></p>
                        </div>
                        <div class="short_overview my-5">
                            <p><strong>Publisher : </strong>${publisher.publisherName}</p>
                        </div>


                        <!-- Add to Cart Form -->
                        <form class="cart clearfix" method="post" action="shop">
                            <input type="hidden" name="action" value="checkUser">
                            <input type="hidden" name="aim" value="addtocart">
                            <input type="hidden" name="bookID" value="${book_detail.bookID}">
                            <div class="cart-btn d-flex mb-50">
                                <p>Quantity</p>
                                <div class="quantity">
                                    <input type="number" class="qty-text" id="qty" step="1" min="1" max="300" name="quantity" value="1">
                                </div>
                            </div>
                            <button type="submit" class="btn amado-btn">Add to cart</button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Product Details Area End -->
</div>
<!-- ##### Main Content Wrapper End ##### -->

<!-- ##### Newsletter Area Start ##### -->

<!-- ##### Newsletter Area End ##### -->

<!-- ##### Footer Area Start ##### -->
<footer class="footer_area clearfix">
    <div class="container">
        <div class="row align-items-center">
            <!-- Single Widget Area -->
            <div class="col-12 col-lg-4">
                <div class="single_widget_area">
                    <!-- Logo -->
                    <div class="footer-logo mr-50">
                        <a href="index.jsp"><img src="img/core-img/logo2.png" alt=""></a>
                    </div>
                    <!-- Copywrite Text -->
                    <p class="copywrite"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a> & Re-distributed by <a href="https://themewagon.com/" target="_blank">Themewagon</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
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
                                    <li class="nav-item">
                                        <a class="nav-link" href="shop.jsp">Shop</a>
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
