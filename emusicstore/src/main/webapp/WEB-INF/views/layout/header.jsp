<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/29/2019
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">My Music Store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/product/list">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/about">About us</a>
                </li>
            </ul>
            <ul class="nav navbar-nav pull-right">

                <li class="nav-item">
                    <span class="nav-link">Welcome: <security:authentication property="principal.username" /></span>
                </li>
                <security:authorize access="hasRole('ADMIN')">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin">Admin</a></li>
                </security:authorize>

                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <li class="nav-item"><button type="submit" class="btn btn-info">Logout</button></li>
                </form:form>

                <security:authorize access="hasRole('USER')">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cart"><i class="fa
                    fa-shopping-cart"></i>[${cartSize}]</a></li>
                </security:authorize>


            </ul>

        </div>
    </nav>
</header>
