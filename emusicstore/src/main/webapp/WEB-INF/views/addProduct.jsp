<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/22/2019
  Time: 9:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<br><br>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h3>${titlePage}</h3>
            <p class="lead">Fill the below information to add a product:</p>
        </div>

        <form:form modelAttribute="product"
                   action="${pageContext.request.contextPath}/product/save"
                   method="POST" enctype="multipart/form-data">

            <form:hidden path="productId" />

            <div class="form-group">
                <label class="control-label col-md-3">Name</label>
                <div class="col-md-6">
                    <form:input path="name" class="form-control" />
                    <form:errors path="name" cssClass="text-danger" />
                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Category</label>
                <div class="col-md-6">
                    <form:select path="category.categoryId" class="form-control">
                        <c:forEach var="category" items="${categories}">
                            <form:option value="${category.categoryId}">${category.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Price</label>
                <div class="col-md-6">
                    <form:input type="number" path="price" class="form-control" />
                    <form:errors path="price" cssClass="text-danger" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Condition</label>
                <div class="col-md-6">
                    <label class="checkbox-inline"><form:radiobutton path="condition"
                                                                     value="new" />New</label>
                    <label class="checkbox-inline"><form:radiobutton path="condition"
                                                                     value="used" />Used</label>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Status</label>
                <div class="col-md-6">
                    <label class="checkbox-inline"><form:radiobutton path="status"
                                                                     value="active" />Active</label>
                    <label class="checkbox-inline"><form:radiobutton path="status"
                                                                     value="inactive" />Inactive</label>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Unit In Stock</label>
                <div class="col-md-6">
                    <form:input type="number" path="unitInStock" class="form-control" />
                    <form:errors path="unitInStock" cssClass="text-danger" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Manufacturer</label>
                <div class="col-md-6">
                    <form:input path="manufacturer"  class="form-control" />
                    <form:errors path="manufacturer" cssClass="text-danger" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Description</label>
                <div class="col-md-6">
                    <form:textarea rows="6" path="description" class="form-control" />
                    <form:errors path="description" cssClass="text-danger" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-3">Upload Picture</label>
                <div class="col-md-6">
                    <form:input path="image" type="file" class="form-control" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-3 col-md-6">
                    <button type="submit" class="btn btn-success">Submit</button>
                    <a href="${pageContext.request.contextPath}/admin/productInventory" class="btn btn-primary">Cancel</a>
                </div>
            </div>

        </form:form>

    </div>
</div>
