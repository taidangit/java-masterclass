<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/29/2019
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title><tiles:insertAttribute name="title" /></title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" >

    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css"
    rel="stylesheet">

    <%--jQuery--%>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

    <!-- data table -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>

    <style>
    .bd-placeholder-img {
    font-size: 1.125rem;
    text-anchor: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    }

    @media (min-width: 768px) {
    .bd-placeholder-img-lg {
    font-size: 3.5rem;
    }
    }
    </style>
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/carousel.css" rel="stylesheet">
</head>
<body>
    <tiles:insertAttribute name="header" />

    <main role="main">

        <tiles:insertAttribute name="body" />

        <tiles:insertAttribute name="footer" />

    </main>

</body>
</html>
