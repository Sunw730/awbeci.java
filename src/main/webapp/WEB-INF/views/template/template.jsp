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
    <c:if test="${sessionScope.user!=null}">
        <style type="text/css">
            .navbar-nav > li > a {
                padding-top: 10px;
                padding-bottom: 10px;
            }

            .navbar-header {
                display: none
            }
        </style>
    </c:if>
</head>
<body>
<nav class="navbar navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand nav-logo" href="/">Awbeci</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar-collapse-01">
            <c:choose>
                <c:when test="${sessionScope.uid!=null}">
                    <h1 class="logo"></h1>
                    <ul class="nav navbar-nav navbar-left">
                        <li><a href="/" class="header-nav-link">首页</a></li>
                        <li><a href="/${sessionScope.userName}" class="header-nav-link">个人主页</a></li>
                        <li><a href="/${sessionScope.userName}/navigation" class="header-nav-link">我的导航</a></li>
                        <li><a href="/aboutme" class="header-nav-link">关于</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-haspopup="true" aria-expanded="false">
                                <span data-toggle="tooltip" data-placement="bottom" title="查看">
                                    <img src="${sessionScope.user.avatarUrl}" width="20" height="20"> <span
                                        class="caret"></span></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="javascript:void(0)">当前登入账号：
                                    <br>${sessionScope.userName}</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="/${sessionScope.userName}">个人主页</a></li>
                                <li><a href="/${sessionScope.userName}/navigation">我的导航</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="/settings/account">设置</a></li>
                                <li><a href="javascript:void(0)" onclick="quit()">退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </c:when>
                <c:when test="${sessionScope.uid==null}">
                    <ul class="nav navbar-nav navbar-left">
                        <li><a href="/" class="header-nav-link">探索发现</a></li>
                        <li><a href="/" class="header-nav-link">功能特性</a></li>
                        <li><a href="/" class="header-nav-link">站点博客</a></li>
                        <li><a href="/aboutme" class="header-nav-link">关于</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <button type="button" class="btn btn-success btn-nav" onclick="region()">注册
                            </button>
                        </li>
                        <li>
                            <button type="button" class="btn btn-default btn-nav" onclick="login()">登录
                            </button>
                        </li>
                    </ul>
                </c:when>
            </c:choose>
        </div>
    </div>
    <!-- /.navbar-collapse -->
</nav>
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