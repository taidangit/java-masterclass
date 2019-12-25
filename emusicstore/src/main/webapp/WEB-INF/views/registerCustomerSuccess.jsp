<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/24/2019
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Confirmation</title>

    <!-- Reference Bootstrap files -->
    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">

    <script src="${pageContext.request.contextPath}/resources/jquery/jquery-3.2.1.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1 class="alert alert-success">Customer registered successfully!</h1>
                </div>
            </div>
        </section>

        <section class="container">
            <p>
                <a href="${pageContext.request.contextPath}/showMyLoginPage">Login with new user</a>
            </p>
        </section>

    </div>
</div>
</body>
</html>