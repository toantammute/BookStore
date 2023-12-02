<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="model.Customer" %>
<%@ page import="data.CheckoutDB" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>BOOKSTORE ONLINE | Change Password</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">
    <script src="https://kit.fontawesome.com/6931f33cbe.js" crossorigin="anonymous"></script>
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
                    <div class="checkout_details_area mt-50 clearfix">

                        <div class="cart-title">
                            <h2>Change Password</h2>
                        </div>

                        <form id="myForm" action="change-password" method="post" >
                            <div class="row">
                                <div class="col-md-12 mb-3">
                                    <p>Old password</p>
                                    <input type="password" class="form-control" name="oldpassword" placeholder="Type your old password..." required>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <p>New password</p>
                                    <input type="password" class="form-control"  name="newpassword" placeholder="Type your new password..." required>
                                </div>
                            </div>
                            <% String message = new String();
                                String flag = session.getAttribute("flag").toString();
                                if(flag.equals("1"))
                                {%>
                                    <p style="color: #0f7529; margin-left: 7px">Your change have been successfully saved <i class="fa-regular fa-circle-check fa-lg" style="color: #0f7529; margin-left: 7px"></i></p>
                                <%}else if(flag.equals("0")){%>
                                    <p style="color: red; margin-left: 7px">Wrong old password <i class="fa-regular fa-circle-xmark fa-lg" style="color: red;"></i></p>
                                <%}%>
                            <div class="row">
                                <div class="cart-btn mt-70" style="margin-right: 20px; margin-left: 20px">
                                    <a class="btn amado-btn w-100" onclick="submitSave()" >Save</a>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
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
<script language="JavaScript" >

    function submitSave() {
        var form = document.getElementById("myForm");
        // Gửi yêu cầu submit form
        form.submit();
    }
</script>
</body>

</html>