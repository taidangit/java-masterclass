<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/23/2019
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>

    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css"
          rel="stylesheet">

</head>
<body>
<div class="container">
    <div class=" col-md-4 col-md-offset-4" style="margin-top: 50px;">
        <div class="panel panel-primary">

            <div class="panel-heading">
                <h3 class="panel-title">Sign In</h3>
            </div>

            <div class="panel-body">

                <!-- Login Form -->
                <form action="${pageContext.request.contextPath}/authenticateTheUser"
                        method="POST" class="form-horizontal">

                    <!-- Place for messages: error, alert etc ... -->
                    <c:if test="${param.error != null}">

                        <div class="alert alert-danger" role="alert">Invalid username and
                            password.</div>

                    </c:if>

                    <c:if test="${param.logout != null}">

                        <div class="alert alert-success" role="alert">You have been logged out.
                        </div>

                    </c:if>

                    <!-- User name -->
                    <div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon">
                                <i class="fa fa-user"></i>
                            </span>
                        <input type="text" name="username" placeholder="username" class="form-control" required="required">
                    </div>

                    <!-- Password -->
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon">
                            <i class="fa fa-lock"></i>
                        </span>
                        <input type="password" name="password" placeholder="password" class="form-control" required="required">
                    </div>

                    <!-- Login/Submit Button -->
                    <button type="submit" class="btn btn-success btn-block">Sign in</button>

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />

                </form>

            </div>

        </div>
        <a href="${pageContext.request.contextPath}/register/showRegistrationForm"
           class="btn btn-primary" role="button" aria-pressed="true">Register New User</a>
    </div>

</div>
</body>
</html>