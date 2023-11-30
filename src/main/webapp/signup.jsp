<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>SIGN UP</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!--===============================================================================================-->
  <link rel="icon" type="image/png" href="img/icons/favicon.ico"/>
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="css/util.css">
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
  <div class="container-login100" style="background-image: url('img/bg-01.jpg');">
    <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
      <form class="login100-form validate-form" method="post" action="login">
        <input type="hidden" name="action" value="signup">
        <span class="login100-form-title p-b-49">
						SIGN UP
					</span>

        <div class="wrap-input100 validate-input m-b-23" data-validate = "Name is reauired">
          <span class="label-input100">Name</span>
          <input class="input100" type="name" name="name" placeholder="Type your name">
          <span class="focus-input100" data-symbol="&#xf206;"></span>
        </div>

        <div class="wrap-input100 validate-input m-b-23">
          <span class="label-input100">DOB</span>
          <input class="input100" type="date" name="date">
          <span class="focus-input100" data-symbol="&#xf206;"></span>
        </div>

        <div class="wrap-input100 validate-input m-b-23">
          <span class="label-input100">Gender</span>
          <div class="radio-container">
            <input type="radio" id="male" name="gender" value="male">
            <label for="male">Male</label>

            <input type="radio" id="female" name="gender" value="female">
            <label for="female">Female</label>

            <input type="radio" id="other" name="gender" value="other">
            <label for="other">Other</label>
          </div>
        </div>



        <div class="wrap-input100 validate-input" data-validate="Password is required">
          <span class="label-input100">Password</span>
          <input class="input100" type="password" name="password" placeholder="Type your password">
          <span class="focus-input100" data-symbol="&#xf190;"></span>
        </div>

        <div class="wrap-input100 validate-input" data-validate="Address is required">
          <span class="label-input100">Address</span>
          <input class="input100" type="text" name="address" placeholder="Type your address">
          <span class="focus-input100" data-symbol="&#xf190;"></span>
        </div>

        <div class="wrap-input100 validate-input" data-validate="Email is required">
          <span class="label-input100">Email</span>
          <input class="input100" type="email" name="email" placeholder="Type your email">
          <span class="focus-input100" data-symbol="&#xf190;"></span>
        </div>

        <div class="wrap-input100 validate-input" data-validate="Phone Number is required">
          <span class="label-input100">Phone Number</span>
          <input class="input100" type="phoneNumber" name="phoneNumber" placeholder="Type your phone number">
          <span class="focus-input100" data-symbol="&#xf190;"></span>
        </div>

        <br>

        <div class="container-login100-form-btn">
          <div class="wrap-login100-form-btn">
            <div class="login100-form-bgbtn"></div>
            <button class="login100-form-btn" type="submit">
              Sign Up
            </button>
          </div>
        </div>


      </form>
    </div>
  </div>
</div>


<div id="dropDownSelect1"></div>

<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>