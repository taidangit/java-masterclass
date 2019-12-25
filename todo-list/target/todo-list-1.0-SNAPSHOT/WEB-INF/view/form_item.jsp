<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 2/19/2019
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="academy.learnprogramming.util.AttributeNames" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <form:form modelAttribute="${AttributeNames.TODO_ITEM}" method="POST">
            <table>
                <tr>
                    <td><label>Title</label></td>
                    <td><form:input path="title"/></td>
                </tr>
                <tr>
                    <td><label>Deadline</label></td>
                    <td><form:input path="deadline"/></td>
                </tr>
                <tr>
                    <td><label>Details</label></td>
                    <td><form:textarea path="detail"/></td>
                </tr>
                <tr>
                    <td><input type="submit" class="btn btn-info" value="Save"/></td>
                </tr>
            </table>
        </form:form>

    </div>
</body>
</html>
