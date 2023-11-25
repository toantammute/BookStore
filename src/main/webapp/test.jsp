<%@ page import="model.Category" %><%--
  Created by IntelliJ IDEA.
  User: Harris
  Date: 11/25/2023
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="test" method="post">
    <input type="hidden" name="action" value="add_cate">
    <input type="text" name="category_name">
    <input type="submit" value="Submit" class="margin_left">
</form>
<table>
    <tr>
        <th>Category ID</th>
        <th>Category Name</th>
    </tr>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
    <c:forEach var="c" items="${categories}">
        <tr>
            <c:if test="${not empty c.categoryID and not empty c.categoryName}">
                <td>${c.categoryID}</td>
                <td>${c.categoryName}</td>
            </c:if>
        </tr>
    </c:forEach>
</table>
</body>
</html>
