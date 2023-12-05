<%@ page import="java.util.Base64" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="model.*" %>
<%@ page import="data.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/6931f33cbe.js" crossorigin="anonymous"></script>
    <!-- Title  -->
    <title>BOOKSTORE ONLINE | Home</title>

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
                    <form action="shop" method="get">
                        <input type="hidden" name="action" value="search">
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
                    <p>Hi, ${customer.customerName} !</p>
                    <li><a href="account.jsp">ACCOUNT</a></li>
                    <li><a href="login?action=logout">LOG OUT</a></li>
                </c:if>
                <c:if test="${empty customer}">
                    <li><a href="login.jsp">LOGIN</a></li>
                </c:if>
                <li class="active"><a href="test?action=shop">Shop</a></li>
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
            <a href="#" class="search-nav"><img src="img/core-img/search.png" alt=""> Search</a>
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

    <div class="shop_sidebar_area">

        <!-- ##### Single Widget ##### -->
        <div class="widget brands mb-50">
            <!-- Widget Title -->
            <h6 class="widget-title mb-30">Author</h6>
            <% List<Author> authors = (List<Author>) request.getAttribute("authors");
                if(authors == null)
                {
                    authors = AuthorDB.getAllAuthor();
                    request.setAttribute("authors", authors);
                }else
                {
                    request.setAttribute("authors", authors);
                }
            %>
            <!--  Catagories  -->
            <div class="widget-desc">
                <!-- Single Form Check -->
                <c:forEach var="author" items="${authors}">
                    <div class="form-check">
                        <a href="shop?action=seachbyauthor&amp;authorid=${author.authorID}">${author.authorName}</a>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- ##### Single Widget ##### -->
        <div class="widget brands mb-50">
            <!-- Widget Title -->
            <h6 class="widget-title mb-30">Category</h6>
            <% List<Category> categories = (List<Category>) request.getAttribute("categories");
                if(categories == null)
                {
                    categories = CategoryDB.getCategoryList();
                    request.setAttribute("categories", categories);
                }else
                {
                    request.setAttribute("categories", categories);
                }
            %>
            <!--  Catagories  -->
            <div class="widget-desc">
                <!-- Single Form Check -->
                <c:forEach var="category" items="${categories}">
                    <div class="form-check">
                        <a href="shop?action=seachbycategory&amp;categoryid=${category.categoryID}">${category.categoryName}</a>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- ##### Single Widget ##### -->

    </div>

    <div class="amado_product_area section-padding-100">
        <div class="container-fluid">



            <div class="row">
                <% List<Book> books = (List<Book>) request.getAttribute("books");
                if(books == null)
                {
                    books = BookDB.getAllBook();
                    request.setAttribute("books", books);
                }else
                {
                    request.setAttribute("books", books);
                }
                %>
                <c:forEach var="book" items="${books}">
                    <!-- Single Product Area -->
                    <div class="col-12 col-sm-4 col-md-12 col-xl-4">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="img/product-img/img.png" alt="">
                                <!-- Hover Thumb -->
                                <img class="hover-img" src="img/product-img/img_1.png" alt="" height="10" weight="10">
                            </div>
                            <!-- Product Description -->
                            <div class="product-description d-flex align-items-center justify-content-between">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line"></div>
                                    <p style="margin-left: 10px" class="product-price">${book.priceFormat}</p>
                                    <a href="product_details?bookID=${book.bookID}">
                                        <h5 style="margin-left: 10px">${book.bookName}</h5>
                                    </a>
                                </div>
                                <!-- Ratings & Cart -->
                                <div class="ratings-cart text-right">
                                    <div class="ratings" style="margin-right: 10px">
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                        <i class="fa fa-star" aria-hidden="true"></i>
                                    </div>
                                    <br>
                                    <div class="cart" style="margin-bottom: 9px">
                                        <% if(customer != null) {
                                            EntityManager em = DBUtil.getEmFactory().createEntityManager();
                                            Cart cart = em.find(Cart.class, customer.getCustomerID());
                                            List<String> listid = CartDB.checkBookFavorite(cart);
                                            request.setAttribute("listid", listid);
                                        }%>
                                        <c:set var="bookIDToCheck" value="${book.bookID}" />
                                        <c:if test="${listid.contains(bookIDToCheck)}">
                                            <c:if test="${book.quantity > 0}">
                                                <a href="shop?action=checkUser&amp;aim=addtocart&amp;bookID=${book.bookID}" style="margin-right: 10px" data-toggle="tooltip" data-placement="left" title="Add to Cart"><i class="fa-solid fa-cart-shopping fa-2x"></i></a>
                                                <a href="cart?action=removefromfavorite1&amp;bookID=${book.bookID}" style="margin-right: 12px" data-toggle="tooltip" data-placement="left" title="Add to Favorite"><i class="fa-solid fa-heart fa-2x" size="100"></i></a>
                                            </c:if>
                                            <c:if test="${!(book.quantity > 0)}">
                                                <a href="cart?action=removefromfavorite1&amp;bookID=${book.bookID}" style="margin-right: 12px" data-toggle="tooltip" data-placement="left" title="Add to Favorite"><i class="fa-solid fa-heart fa-2x" size="100"></i></a>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${!listid.contains(bookIDToCheck)}">
                                            <c:if test="${book.quantity > 0}">
                                                <a href="shop?action=checkUser&amp;aim=addtocart&amp;bookID=${book.bookID}" style="margin-right: 10px" data-toggle="tooltip" data-placement="left" title="Add to Cart"><i class="fa-solid fa-cart-shopping fa-2x"></i></a>
                                                <a href="shop?action=checkUser&amp;aim=addtofavorite&amp;bookID=${book.bookID}" style="margin-right: 12px" data-toggle="tooltip" data-placement="left" title="Add to Favorite"><i class="fa-regular fa-heart fa-2x" size="100"></i></a>
                                            </c:if>
                                            <c:if test="${!(book.quantity > 0)}">
                                                <a href="shop?action=checkUser&amp;aim=addtofavorite&amp;bookID=${book.bookID}" style="margin-right: 12px" data-toggle="tooltip" data-placement="left" title="Add to Favorite"><i class="fa-regular fa-heart fa-2x" size="100"></i></a>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <!-------->
            </div>
            <!--  CHUYEN
            <div class="row">
                <div class="col-12">
                     Pagination
                    <nav aria-label="navigation">
                        <ul class="pagination justify-content-end mt-50">
                            <li class="page-item active"><a class="page-link" href="#">01.</a></li>
                            <li class="page-item"><a class="page-link" href="#">02.</a></li>
                            <li class="page-item"><a class="page-link" href="#">03.</a></li>
                            <li class="page-item"><a class="page-link" href="#">04.</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            -->
        </div>
    </div>
</div>
<!-- ##### Main Content Wrapper End ##### -->

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
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved </p>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
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
                                        <a class="nav-link" href="shop.jsp">Back to top page</a>
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
<script>
    function redirectToLink(authorID) {
        var link = document.getElementById(authorID);
        if (link) {
            link.click(); // Thực hiện sự kiện click trên thẻ <a>
        }
    }
</script>

</body>

</html>