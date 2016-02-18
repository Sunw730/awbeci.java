<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="header-content">
            <div class="content-info">
            <span>
                <img src="${user.avatarUrl}" alt="${user.avatarUrl}" width="40" height="40"></span>
                <ul>
                    <li>
                        <a href="${user.name}" class="username">${user.name}</a>
                    </li>
                    <li>
                        <small class="dynamic-time"><spring:eval expression="user.createDt"/></small>
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
    <div class="container-center">
        <div class="container-header">
            最新动态
        </div>
        <div class="header-content">
            <div class="content-info">
            <span>
                <img src="http://static.awbeci.com/img/avatar/20160128134040" width="40" height="40"></span>
                <ul>
                    <li>
                        <a href="#" class="username">zhangwei</a>
                    </li>
                    <li>
                        <small class="dynamic-time">2015-11-11 12:32</small>
                    </li>
                </ul>
            </div>
            <div class="content-body">
                添加了网址：<a id="4a723a16-f72b-4251-a44c-bafff75b1c66" target="_blank" href="https://twitter.com/"
                         categoryid="8fefc360-7eb5-4a21-92b4-e0e54a2a03f3"><img width="16px" height="16px"
                                                                                style="margin-right:5px;"
                                                                                src="http://static.awbeci.com/img/siteicon/20160202213925.ico">twitter
            </a><a id="4a723a16-f72b-4251-a44c-bafff75b1c66" target="_blank" href="https://twitter.com/"
                   categoryid="8fefc360-7eb5-4a21-92b4-e0e54a2a03f3"><img width="16px" height="16px"
                                                                          style="margin-right:5px;"
                                                                          src="http://static.awbeci.com/img/siteicon/20160202213925.ico">twitter
            </a><a id="4a723a16-f72b-4251-a44c-bafff75b1c66" target="_blank" href="https://twitter.com/"
                   categoryid="8fefc360-7eb5-4a21-92b4-e0e54a2a03f3"><img width="16px" height="16px"
                                                                          style="margin-right:5px;"
                                                                          src="http://static.awbeci.com/img/siteicon/20160202213925.ico">twitter
            </a><a id="4a723a16-f72b-4251-a44c-bafff75b1c66" target="_blank" href="https://twitter.com/"
                   categoryid="8fefc360-7eb5-4a21-92b4-e0e54a2a03f3"><img width="16px" height="16px"
                                                                          style="margin-right:5px;"
                                                                          src="http://static.awbeci.com/img/siteicon/20160202213925.ico">twitter
            </a><a id="4a723a16-f72b-4251-a44c-bafff75b1c66" target="_blank" href="https://twitter.com/"
                   categoryid="8fefc360-7eb5-4a21-92b4-e0e54a2a03f3"><img width="16px" height="16px"
                                                                          style="margin-right:5px;"
                                                                          src="http://static.awbeci.com/img/siteicon/20160202213925.ico">twitter
            </a>
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
                            <span class="badge">${topUrl.topUrlCount}</span>
                            <a href="${user.name}/navigation?url=${topUrl.topUrl}">${topUrl.topUrl}</a>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div>
    </div>

</div>
</body>
</html>
