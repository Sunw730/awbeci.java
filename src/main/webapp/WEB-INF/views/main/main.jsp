<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新感觉,新体验</title>
    <link href="/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="/js/views/main.js"></script>
</head>
<body>
<div id="main-container" class="container">
    <div class="container-left">
        <div class="container-header">
            我的资料
        </div>
        <div class="header-content-body">
            <div class="header-content">
                <div class="content-info">
            <span>
                <img src="${user.avatarUrl}" alt="${user.avatarUrl}" width="40" height="40"></span>
                    <ul>
                        <li>
                            <a href="${user.name}" class="username">${user.name}</a>
                        </li>
                        <li>
                            <small class="dynamic-time">
                                加入于 <spring:eval expression="user.createDt"/></small>
                        </li>
                    </ul>
                </div>
                <div class="content-body">
                    <div class="body-content">
                        <a href="#" class="body-content-a">
                            <strong>${followingsCount}</strong>
                            <span>正在关注</span>
                        </a>
                        <a href="#" class="body-content-a">
                            <strong>${followersCount}</strong>
                            <span>关注者</span>
                        </a>
                        <a href="${user.name}/navigation" class="body-content-a">
                            <strong>${sitesCount}</strong>
                            <span>网址</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container-center">
        <div class="container-header">
            最新动态
        </div>
        <c:forEach items="${userDynamics}" var="userDynamic">
            <div class="header-content-body">
                <div class="header-content">
                    <div class="content-info">
                <span>
                    <img src="${userDynamic.user.avatarUrl}" width="40" height="40">
                </span>
                        <ul>
                            <li>
                                <a href="/${userDynamic.user.name}" class="username hoverUserName"
                                   id="${userDynamic.user.id}">
                                        ${userDynamic.user.name}
                                </a>
                            </li>
                            <li>
                                <small class="dynamic-time">
                                    <fmt:formatDate pattern="yyyy年MM月dd日 HH:mm"
                                                    value="${userDynamic.createDt}"></fmt:formatDate>
                                </small>
                            </li>
                        </ul>
                    </div>
                    <div class="content-body">
                        <c:if test="${userDynamic.action==1}">
                            添加了
                        </c:if>
                        <c:if test="${userDynamic.objType==1}">
                            网址：
                        </c:if>

                        <a target="_blank" href="${userDynamic.userSites.url}">
                            <img width="16px" height="16px" src="${userDynamic.userSites.icon}">
                                ${userDynamic.userSites.name}
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="userInfoDlg">
            <div class="header-content">
                <div class="content-info">
                    <span id="content-info-img">
                        <img src="" width="40" height="40">
                    </span>
                    <ul>
                        <li id="content-info-username">
                            <a href="" class="username"></a>
                        </li>
                        <li>
                            <small class="dynamic-time" id="dynamic-time">
                            </small>
                        </li>
                    </ul>
                </div>
                <div class="content-body">
                    <div class="body-content">
                        <a href="#" class="body-content-a">
                            <strong id="followingCount"></strong>
                            <span>正在关注</span>
                        </a>
                        <a href="#" class="body-content-a">
                            <strong id="followerCount"></strong>
                            <span>关注者</span>
                        </a>
                        <a id="sitesCountNavigation" href="" class="body-content-a">
                            <strong id="sitesCount"></strong>
                            <span>网址</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="hover-offset">
                <div class="arrow" style=""></div>
            </div>
        </div>
    </div>
    <div class="container-right">
        <div class="container-header">
            收藏最多
        </div>
        <div class="header-content">
            <div class="content-body">
                <ul class="list-group">
                    <c:forEach items="${topUrls}" var="topUrl">
                        <li class="list-group-item">
                            <img width="16px" height="16px"
                                 src="${topUrl.icon}">
                            <span class="badge">${topUrl.topUrlNameCount}</span>
                            <a href="${user.name}/navigation?domainName=${topUrl.topUrlName}">${topUrl.topUrlName}</a>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
