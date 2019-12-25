<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 2/19/2019
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="academy.learnprogramming.util.Mappings" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Items</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3>Todo Items</h3>
    <hr/>
    <c:url var="addUrl" value="${Mappings.ADD_ITEM}"/>
    <a href="${addUrl}" class="btn btn-primary btn-sm mb-3">Add New Item</a>

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Detail</th>
                <th>Deadline</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${todoData.items}">

                <c:url var="viewUrl" value="${Mappings.VIEW_ITEM}">
                    <c:param name="id" value="${item.id}"/>
                </c:url>

                <c:url var="editUrl" value="${Mappings.ADD_ITEM}">
                    <c:param name="id" value="${item.id}"/>
                </c:url>

                <c:url var="deleteUrl" value="${Mappings.DELETE_ITEM}">
                    <c:param name="id" value="${item.id}"/>
                </c:url>

                <tr>
                    <td>${item.id}</td>
                    <td>${item.title}</td>
                    <td>${item.detail}</td>
                    <td>${item.deadline}</td>
                    <td>
                        <a href="${viewUrl}" class= "btn btn-success btn-sm ">View</a>
                        <a href="${editUrl}" class="btn btn-warning btn-sm ">Edit</a>
                        <a href="${deleteUrl}" class="btn btn-danger btn-sm" onclick="if (!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
