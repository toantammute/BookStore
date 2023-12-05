<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<%@ page import="model.Customer" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="data.CategoryDB" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>BOOKSTORE ONLINE | Add New User</title>

    <!-- Custom fonts for this template -->
    <link href="admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="admin/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/6931f33cbe.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/form.css">
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="adminpage.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Hi, ${customer.customerName}</div>
        </a>

        <!-- -->
        <li class="nav-item">
            <a class="nav-link" href="profileadmin.jsp">
                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                <span>Profile</span></a>
        </li>


        <li class="nav-item">
            <a class="nav-link" href="#" data-toggle="modal" data-target="#logoutModal">
                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                <span>Log Out</span></a>
        </li>


        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item">
            <a class="nav-link" href="adminpage.jsp">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">


        <!-- Heading -->
        <div class="sidebar-heading">
            STORE INFORMATION
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
               aria-expanded="true" aria-controls="collapsePages">
                <i class="fas fa-fw fa-table"></i>
                <span>Tables</span></a>
            </a>
            <!-- auth,cate,book,inv,user
       -->
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="authortable.jsp">Author</a>
                    <a class="collapse-item" href="categorytable.jsp">Category</a>
                    <a class="collapse-item" href="booktable.jsp">Book</a>
                    <a class="collapse-item" href="publishertable.jsp">Publisher</a>
                    <a class="collapse-item" href="invoicetable.jsp">Invoice</a>
                    <a class="collapse-item" href="accounttable.jsp">User</a>
                </div>
            </div>
        </li>


        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#addnew"
               aria-expanded="true" aria-controls="collapsePages">
                <i class="fa-solid fa-plus" style="color: #ded9d9;"></i>
                <span>Add New</span></a>
            </a>
            <!-- auth,cate,book,inv,user
       -->
            <div id="addnew" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="addnewauthor.jsp">Author</a>
                    <a class="collapse-item" href="addnewcategory.jsp">Category</a>
                    <a class="collapse-item" href="addnewpublisher.jsp">Publisher</a>
                    <a class="collapse-item" href="addnewbook.jsp">Book</a>
                    <a class="collapse-item" href="addnewuser.jsp">User</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Charts -->




        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div class="container">
        <p style="font-size: 40px; color: #0b0b0b">ADD NEW USER</p>
        <form action="add" method="post">
            <input type="hidden" name="action" value="addnewuser">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <p><strong>User Name</strong></p>
                    <input type="text" class="form-control" name="customerName" placeholder="Type user's name..." required>
                </div>
                <div class="col-md-6 mb-3">
                    <p><strong>DOB</strong></p>
                    <input type="date" class="form-control"  name="dob" required>
                </div>
                <div class="col-md-6 mb-3">
                    <p><strong>Email</strong></p>
                    <input type="email" class="form-control"  name="email" required placeholder="Type user's email...">
                    <p style="color: red">${message}</p>
                    <%request.removeAttribute("message");%>
                </div>
                <div class="col-md-6 mb-3xa">
                    <p><strong>Password</strong></p>
                    <input type="text" class="form-control"  name="password" placeholder="Type user's password..." required>
                </div>
                <div class="col-md-6 mb-3">
                    <p><strong>Gender</strong></p>
                    <div>
                        <input type="radio" id="male" name="gender" value="Male"checked>
                        <label for="male" style="margin-right: 50px">Male</label>

                        <input type="radio" id="female" name="gender" value="Female">
                        <label for="female" style="margin-right: 50px">Female</label>

                        <input type="radio" id="other" name="gender" value="Other">
                        <label for="other">Other</label>
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <p><strong>Address</strong></p>
                    <input type="text" class="form-control"  name="address" placeholder="Type user's address..." required>
                </div>
                <div class="col-md-6 mb-3">
                    <p><strong>Phone Number</strong></p>
                    <input type="text" class="form-control"  name="phoneNum" placeholder="Type user's phone number..." required>
                </div>
                <div class="col-md-6 mb3">
                    <p><strong>is Admin</strong></p>
                    <select name="isAdmin" class="form-control">
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select>
                </div>
            </div>
            <input type="submit" value="Submit">
        </form>
    </div>



    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <i class="fa-solid fa-xmark"></i>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login?action=logout">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="admin/vendor/jquery/jquery.min.js"></script>
<script src="admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="admin/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="admin/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="admin/js/demo/datatables-demo.js"></script>
<script type="text/javascript">
    //auto expand textarea
    function adjust_textarea(h) {
        h.style.height = "20px";
        h.style.height = (h.scrollHeight)+"px";
    }
</script>
</body>

</html>