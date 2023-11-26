<%@ page import="model.Category" %><%--
  Created by IntelliJ IDEA.
  User: Harris
  Date: 11/25/2023
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <style>
        table {
            border-collapse: collapse;
            width: 75%;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container"></div>
        <form action="test" method="post">
            <input type="hidden" name="action" value="add_cate">
            <div class="mt-3">
                <input placeholder="Search Author" type="text" class="form-control" id="author_name" name="author_name">
            </div>
            <c:if test="${not empty message}">
                <p style="color: red"><span>*</span>${message}</p>
            </c:if>
            <div class="mt-3">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>

<div class="container">
    <table>
        <tr>
            <th>Author ID</th>
            <th>Author Name</th>
        </tr>

        <c:forEach var="a" items="${authors}">
            <tr>
                <c:if test="${not empty a.authorID and not empty a.authorName}">
                    <td>${a.authorID}</td>
                    <td>${a.authorName}</td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
