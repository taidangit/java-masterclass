<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/24/2019
  Time: 10:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Cart</h1>
                    <p>All the selected products in your shopping cart</p>
                </div>
            </div>
        </section>

        <section class="container">
            <div>
                <div>
                    <c:choose>
                        <c:when test="${cartSize > 0}">
                            <a href="${pageContext.request.contextPath}/cart/removeAll/${cart.cartId}"
                               class="btn btn-danger pull-left" onclick="if (!(confirm('Are you sure you want to remove all product ?'))) return false">
                                <i class="fa fa-remove"></i>
                                Clear Cart
                            </a>
                            <a href="${pageContext.request.contextPath}/order/${cart.cartId}"
                               class="btn btn-success pull-right"><i class="fa fa-shopping-cart"></i>Check out
                            </a>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-danger pull-left" disabled><i class="fa fa-remove"></i>Clear Cart</button>
                            <button class="btn btn-success pull-right" disabled><i class="fa fa-shopping-cart"></i>Check out</button>
                        </c:otherwise>
                    </c:choose>

                </div>

                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="text-center">Image</th>
                            <th class="text-center">Product</th>
                            <th class="text-center">Price</th>
                            <th class="text-center">Quantity</th>
                            <th class="text-center">Sub Total</th>
                            <th class="text-center">Action</th>
                        </tr>
                    </thead>
                   <tbody>
                        <c:forEach var="cartItem" items="${cart.cartItems}">
                            <td class="text-center">
                                <img src="${pageContext.request.contextPath}/resources/images/${cartItem.product.productId}.png"
                                     class="img-responsive" alt="${product.name}" style="width:64px;"/>
                            </td>
                            <td class="text-center">${cartItem.product.name}</td>
                            <td class="text-center">$ ${cartItem.product.price}</td>
                            <td class="text-center">${cartItem.quantity}</td>
                            <td class="text-center">$ ${cartItem.subtotal}</td>
                            <td class="text-center">
                               <a href="${pageContext.request.contextPath}/cart/remove/${cartItem.cartItemId}"
                                  class="btn btn-danger btn-sm"
                                  onclick="if (!(confirm('Are you sure you want to remove this product ?'))) return false">
                               <i class="fa fa-remove"></i>remove</a></td>
                           </tr>
                        </c:forEach>

                        <tr>
                            <td></td>
                            <td></td>
                            <td class="text-center"><strong>Grand Total</strong></td>
                            <td></td>
                            <td class="text-center text-danger"><strong>$ ${cart.grandTotal}</strong></td>
                        </tr>
                   </tbody>


                </table>

                <a href="${pageContext.request.contextPath}/product/list" class="btn btn-primary">
                    <i class="fa fa-shopping-cart"></i>
                    Continue Shopping
                </a>
            </div>
        </section>

    </div>
</div>
