<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/24/2019
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<br><br>
<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Customer Management Page</h1>
            <p class="lead">This is the customer management page.</p>
        </div>

        <table class="table table-striped table-bordered table-hover" id="myTable">
            <thead>
                <tr class="thead-dark">
                    <th class="text-center">Name</th>
                    <th class="text-center">Email</th>
                    <th class="text-center">Phone</th>
                    <th class="text-center">Username</th>
                    <th class="text-center">Enabled</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td class="text-center">${customer.name}</td>
                        <td class="text-center">${customer.email}</td>
                        <td class="text-center">${customer.phone}</td>
                        <td class="text-center">${customer.username}</td>
                        <td class="text-center">${customer.enabled}</td>
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