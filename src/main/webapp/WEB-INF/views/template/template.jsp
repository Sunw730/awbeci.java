<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><sitemesh:write property="title"/></title>
    <!-- Loading Bootstrap -->
    <link href="/js/Bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/Bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="/css/social-buttons.css" rel="stylesheet">
    <link href="/css/template.css" rel="stylesheet">
    <link href="/js/awbeci/themes/default/dropmenu.css" rel="stylesheet">
    <link href="/js/font-awesome/font-awesome.min.css" rel="stylesheet">
    <link href="/js/octicons/octicons.css" rel="stylesheet">

    <link href="http://static.bootcss.com/www/assets/ico/favicon.png" rel="shortcut icon">
    <link href="/js/lobibox/css/Lobibox.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <script src="/js/react-0.14.3/JSXTransformer.js"></script>
    <script src="/js/jquery/jquery-1.11.2.min.js"></script>
    <script src="/js/Bootstrap/js/bootstrap.min.js"></script>
    <script src="/js/lobibox/js/lobibox.min.js"></script>
    <script src="/js/views/common.js"></script>
    <sitemesh:write property="head"/>
    <%if (request.getSession().getAttribute("user") != null) {%>
    <style type="text/css">
        .navbar-nav > li > a {
            padding-top: 10px;
            padding-bottom: 10px;
        }

        .navbar-header {
            display: none
        }
    </style>
    <% }%>
</head>
<body>
<nav class="navbar navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand nav-logo" href="/">Awbeci</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar-collapse-01">
        </div>
    </div>
    <!-- /.navbar-collapse --> </nav>
<!-- /navbar -->

<sitemesh:write property="body"/>
<footer>
    <div class="myclearfix">
        <span>©2014-2015 awbeci <a href="http://www.miitbeian.gov.cn/">皖ICP备14011269号-2</a></span>
    </div>
</footer>
<!-- /menu -->
</body>
</html>