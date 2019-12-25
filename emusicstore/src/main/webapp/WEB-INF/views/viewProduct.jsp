<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/22/2019
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<br><br>
<div class="container-wrapper">
    <div class="container" ng-app = "cartApp">
        <div class="page-header">
            <h3>${titlePage}</h3>
            <p class="lead">Here is the detail information of the product!</p>
        </div>

        <div class="row">
            <div class="col-sm-4 col-md-4">
               <img src="${pageContext.request.contextPath}/resources/images/${product.productId}.png"
                    class="img-responsive" alt="${product.name}"
                style="width: 350px;"/>
            </div>

            <div class="col-sm-8 col-md-8">
                <h3>${product.name}</h3>
                <p>${product.description}</p>
                <p>
                    <strong>Manufacturer</strong> : ${product.manufacturer}
                </p>
                <p>
                    <strong>Category</strong> : ${product.category.name}
                </p>
                <p>
                    <strong>Condition</strong> : ${product.condition}
                </p>
                <h4>$ ${product.price}</h4>

                <br>

                <p>

                    <a href="${pageContext.request.contextPath}/cart/add/${product.productId}" class="btn btn-warning">
                        <i class="fa fa-shopping-cart"></i>
                        Add to cart
                    </a>
                </p>

            </div>
        </div>

        <security:authorize access="hasRole('ADMIN')">
            <a href="${pageContext.request.contextPath}/admin/productInventory"
               class="btn btn-primary"><i class="fa fa-arrow-left"></i>Back to product inventory</a>

        </security:authorize>

        <security:authorize access="hasRole('USER')">
            <a href="${pageContext.request.contextPath}/product/list"
               class="btn btn-primary"><i class="fa fa-arrow-left"></i>Back to product list</a>

        </security:authorize>
    </div>
</div>
