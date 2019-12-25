<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 2/19/2019
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="academy.learnprogramming.util.Mappings" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Item</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
    <div class="container">

        <table>
            <tr>
                <td><label>ID</label></td>
                <td>${todoItem.id}</td>
            </tr>
            <tr>
                <td><label>Title</label></td>
                <td>${todoItem.title}</td>
            </tr>
            <tr>
                <td><label>Deadline</label></td>
                <td>${todoItem.deadline}</td>
            </tr>
            <tr>
                <td><label>Details</label></td>
                <td>${todoItem.detail}</td>
            </tr>

        </table>

        <c:url var="tableUrl" value="${Mappings.ITEMS}"/>
        <a href="${tableUrl}">Back to list todo</a>
    </div>
</body>
</html>
