
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/22/2019
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<br><br>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h3>All products</h3>
            <p class="lead">Checkout all the awesome products available now!</p>
        </div>

        <table class="table table-striped table-bordered table-hover" id="myTable">
            <thead>
                <tr class="thead-dark">
                    <th class="text-center">Photo Thumb</th>
                    <th class="text-center">Product Name</th>
                    <th class="text-center">Category</th>
                    <th class="text-center">Condition</th>
                    <th class="text-center">Price</th>
                    <th class="text-center">Detail</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td class="text-center">
                            <img src="${pageContext.request.contextPath}/resources/images/${product.productId}.png"
                                 class="img-responsive" alt="${product.name}" style="width:64px;"/>
                        </td>
                        <td class="text-center">${product.name}</td>
                        <td class="text-center">${product.category.name}</td>
                        <td class="text-center">${product.condition}</td>
                        <td class="text-center">$ ${product.price}</td>
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/product/view/${product.productId}"
                               class=" btn btn-success btn-sm"><i class="fa fa-hand-o-right"></i>Detail</a>
                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    $(document).ready(function() {
        $("#myTable").DataTable({
            "lengthMenu": [[1,2,3,5,10,-1], [1,2,3,5,10, "All"]],
            "ordering": false,
            stateSave: true
        });
    });
</script>
