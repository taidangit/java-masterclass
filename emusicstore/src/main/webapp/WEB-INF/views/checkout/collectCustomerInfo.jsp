<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/27/2019
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<br><br>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Customer</h1>
            <p class="lead">Customer Details</p>
        </div>

        <form:form modelAttribute="order" class="form-horizontal">

            <input type="hidden" name="_flowExecutionKey"
                   value="${flowExecutionKey}" />

            <h3>Basic Info</h3>
            <div class="form-group">
                <label class="control-label col-md-3">Name</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.name" class="form-Control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Email</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.email" class="form-Control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Phone</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.phone" class="form-Control"/>
                </div>
            </div>

            <h3>Billing Address</h3>

            <div class="form-group">
                <label class="control-label col-md-3">Street Name</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.billingAddress.streetName" class="form-Control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Apartment Number</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.billingAddress.apartmentNumber" class="form-Control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">City</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.billingAddress.city" class="form-Control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">State</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.billingAddress.state" class="form-Control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Country</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.billingAddress.country" class="form-Control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Zipcode</label>
                <div class="col-md-6">
                    <form:input path="cart.customer.billingAddress.zipCode" class="form-Control"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-3 col-md-6">
                    <button class="btn btn-success" name="_eventId_customerInfoCollected">Next</button>
                    <button class="btn btn-primary" name="_eventId_cancel">Cancel</button>
                </div>
            </div>

        </form:form>
    </div>
</div>
