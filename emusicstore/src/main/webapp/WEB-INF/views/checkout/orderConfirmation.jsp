<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/27/2019
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<br/><br/>

<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Order</h1>

            <p class="lead">Order confirmation</p>
        </div>

        <div class="container">

                <form:form modelAttribute="order" class="form-horizontal">

                    <input type="hidden" name="_flowExecutionKey"
                           value="${flowExecutionKey}" />

                    <div class="col-md-6 col-sm-6">

                        <h1 align="center">Receipt</h1>

                        <div class="row">
                            <div class="col-sm-6 col-md-6">
                                <strong>Shipping Address</strong><br>
                                    ${order.cart.customer.shippingAddress.streetName}
                                <br>
                                    ${order.cart.customer.shippingAddress.apartmentNumber}
                                <br>
                                    ${order.cart.customer.shippingAddress.city}, ${order.cart.customer.shippingAddress.state}
                                <br>
                                    ${order.cart.customer.shippingAddress.country}, ${order.cart.customer.shippingAddress.zipCode}
                                <br>
                            </div>

                            <div class="col-sm-6 col-md-6">
                                <p>Shipping Date: <fmt:formatDate type="date" value="${now}"/></p>
                            </div>
                        </div>

                        <br>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <strong>Billing Address</strong><br>
                                    ${order.cart.customer.billingAddress.streetName}
                                <br>
                                    ${order.cart.customer.billingAddress.apartmentNumber}
                                <br>
                                    ${order.cart.customer.billingAddress.city}, ${order.cart.customer.billingAddress.state}
                                <br>
                                    ${order.cart.customer.billingAddress.country}, ${order.cart.customer.billingAddress.zipCode}
                                <br>
                            </div>
                        </div>

                        <div class="row">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th class="text-center">Product</th>
                                        <th class="text-center">Qty</th>
                                        <th class="text-center">Price</th>
                                        <th class="text-center">Sub Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="cartItem" items="${order.cart.cartItems}" >
                                        <tr>
                                            <td class="text-center"><em>${cartItem.product.name}</em></td>
                                            <td class="text-center" >${cartItem.quantity}</td>
                                            <td class="text-center" >${cartItem.product.price}</td>
                                            <td class="text-center" >${cartItem.subtotal}</td>
                                        </tr>
                                    </c:forEach>

                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td class="text-right">
                                            <h4><strong>Grand Total: </strong></h4>
                                        </td>
                                        <td class="text-center text-danger">
                                            <h4><strong>$ ${order.cart.grandTotal}</strong></h4>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <button class="btn btn-success" name="_eventId_backToCollectShippingDetail">Back</button>
                        <button class="btn btn-success" name="_eventId_orderConfirmed">Submit Order</button>
                        <button class="btn btn-primary" name="_eventId_cancel">Cancel</button>
                    </div>
                </form:form>

        </div>
    </div>
</div>

