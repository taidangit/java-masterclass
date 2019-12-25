<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/22/2019
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<br><br>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Administrator page</h1>
            <p class="lead">This is the administrator page!</p>
        </div>

        <h3>
            Welcome: <security:authentication property="principal.username" /> |
            Role(s): <security:authentication property="principal.authorities" />

        </h3>


        <h3>
            <a href="${pageContext.request.contextPath}/admin/productInventory">Product Inventory</a>
        </h3>

        <p>Here you can view, check and modify the product inventory!</p>

        <br><br>
        <h3>
            <a href="${pageContext.request.contextPath}/admin/customer">Customer Managementy</a>
        </h3>

        <p>Here you can view the customer information!</p>

    </div>
</div>
