<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/24/2019
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register New User Form</title>

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
        <div class="page-header">
            <h3>${titlePage}</h3>

            <p class="lead">Fill the below information to add a customer:</p>
        </div>

        <form:form class="form-horizontal"
                   action="${pageContext.request.contextPath}/register/processRegister"
                   modelAttribute="customer" method="POST">

            <c:if test="${registrationError != null}">
                <div class="alert alert-danger" role="alert">${registrationError}</div>
            </c:if>

            <c:if test="${emailMsg != null}">
                <div class="alert alert-danger" role="alert">${emailMsg}</div>
            </c:if>

            <h3>Basic Info</h3>
            <div class="form-group">
                <label class="control-label col-md-3">Name</label>
                <div class="col-md-6">
                    <form:input path="name" class="form-control" />
                    <form:errors path="name" cssClass="text-danger" />
                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Username</label>
                <div class="col-md-6">
                    <form:input path="username" class="form-control" />
                    <form:errors path="username" cssClass="text-danger" />
                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Password</label>
                <div class="col-md-6">
                    <form:password  path="password" class="form-control" />
                    <form:errors path="password" cssClass="text-danger" />
                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Email</label>
                <div class="col-md-6">
                    <form:input path="email" class="form-control" />
                    <form:errors path="email" cssClass="text-danger" />
                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Phone</label>
                <div class="col-md-6">
                    <form:input path="phone" class="form-control" />
                    <form:errors path="phone" cssClass="text-danger" />
                </div>

            </div>

            <h3>Billing Address</h3>
            <div class="form-group">
                <label class="control-label col-md-3">Street Name</label>
                <div class="col-md-6">
                    <form:input path="billingAddress.streetName" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Apartment Number</label>
                <div class="col-md-6">
                    <form:input path="billingAddress.apartmentNumber" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">City</label>
                <div class="col-md-6">
                    <form:input path="billingAddress.city" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">State</label>
                <div class="col-md-6">
                    <form:input path="billingAddress.state" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Country</label>
                <div class="col-md-6">
                    <form:input path="billingAddress.country" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Zipcode</label>
                <div class="col-md-6">
                    <form:input path="billingAddress.zipCode" class="form-control" />

                </div>

            </div>

            <h3>Shipping Address</h3>
            <div class="form-group">
                <label class="control-label col-md-3">Street Name</label>
                <div class="col-md-6">
                    <form:input path="shippingAddress.streetName" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Apartment Number</label>
                <div class="col-md-6">
                    <form:input path="shippingAddress.apartmentNumber" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">City</label>
                <div class="col-md-6">
                    <form:input path="shippingAddress.city" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">State</label>
                <div class="col-md-6">
                    <form:input path="shippingAddress.state" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Country</label>
                <div class="col-md-6">
                    <form:input path="shippingAddress.country" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Zipcode</label>
                <div class="col-md-6">
                    <form:input path="shippingAddress.zipCode" class="form-control" />

                </div>

            </div>

            <div class="form-group">
                <div class="col-md-offset-3 col-md-6">
                    <button type="submit" class="btn btn-success">Submit</button>
                    <a href="${pageContext.request.contextPath}/showMyLoginPage" class="btn btn-primary">Cancel</a>
                </div>
            </div>


        </form:form>

    </div>
</div>
</body>
</html>