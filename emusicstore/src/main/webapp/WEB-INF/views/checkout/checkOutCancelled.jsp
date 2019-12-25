<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/27/2019
  Time: 2:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<br/><br/>

<div class="container-wrapper">
    <div class="container">
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1 class="alert alert-danger">Checkout cancelled</h1>
                    <p>Your checkout process is cancelled. You may continue shopping.</p>
                </div>
            </div>
        </section>

        <section class="container">
            <p>
                <a href="${pageContext.request.contextPath}/product/list"
                   class="btn btn-default"><i class="fa fa-arrow-left"></i>Back to product list</a>
            </p>
        </section>

    </div>
</div>
