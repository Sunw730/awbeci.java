<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <link href="/css/mymain.css" rel="stylesheet">
    <script src="/js/views/mymain.js"></script>
</head>
<body>
<div class="container" id="container-main">
    <div class="row">
        <div class="mycol-1">
            <div class="thumbnail">
                <img src="http://static.awbeci.com/img/avatar/20160108211734" alt="...">
                <div class="caption">
                    <div class="userinfoname">${user.name}</div>
                    <div class="userinfonicename">${user.niceName}</div>
                    <ul class="userinfocontent">
                        <li>
                            <span aria-hidden="true" class="octicon octicon-location"></span>
                            ${user.location}
                        </li>
                        <li>
                            <span aria-hidden="true" class="octicon octicon-mail"></span>
                            <a class="email"
                               href="mailto:${user.email}" title="${user.email}">
                                ${user.email}</a>
                        </li>
                        <li>
                            <span aria-hidden="true"
                                  class="octicon octicon-link"></span>
                            <a
                                    href="${user.url}" class="url" rel="nofollow me" title="${user.url}">
                                ${user.url}</a>
                        </li>
                        <li>
                            <span aria-hidden="true" class="octicon octicon-clock"></span>
                            <span
                                    class="join-label">加入于 </span>
                            <time class="join-date" datetime="<spring:eval expression="user.createDt" />" day="numeric"
                                  is="local-time"
                                  month="short" year="numeric" title="<spring:eval expression="user.createDt" />">
                                <spring:eval expression="user.createDt"/>
                            </time>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="mycol-2">
            <div class="setting-content">
                <div class="person-info-content">
                    <ul id="myTab" class="nav nav-tabs">
                        <li class="active">
                            <a href="#following" data-toggle="tab">
                                正在关注<span class="badge">${followingsCount}</span>
                            </a>
                        </li>
                        <li>
                            <a href="#follower" data-toggle="tab">
                                关注者<span class="badge">4</span>
                            </a></li>
                         <span class="pull-right">
                             <a href="#" class="btn btn-success btn-sm">
                                 <span aria-hidden="true" class="octicon octicon-person"></span>关注 </a></span>
                    </ul>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="following">
                            <ul class="media-list">
                                <c:forEach items="${followings}" var="following">
                                    <li class="media">
                                        <div class="media-left">
                                            <a href="#">
                                                <img width="75" height="75" class="media-object"
                                                     src="http://static.awbeci.com/img/avatar/20160108211734" alt="...">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">zhangwei</h4>
                                            <p><span aria-hidden="true" class="octicon octicon-clock">2016-1-1</span>
                                            </p>

                                            <div class="media-body-btn"><a href="#" class="btn btn-default btn-sm">
                                                <span aria-hidden="true" class="octicon octicon-person"></span>已关注 </a>
                                            </div>

                                        </div>
                                    </li>
                                </c:forEach>

                            </ul>
                        </div>
                        <div class="tab-pane fade" id="follower">
                            <ul class="media-list">
                                <li class="media">
                                    <div class="media-left">
                                        <a href="#">
                                            <img width="75" height="75" class="media-object"
                                                 src="http://static.awbeci.com/img/avatar/20160108211734" alt="...">
                                        </a>
                                    </div>
                                    <div class="media-body">
                                        <h4 class="media-heading">zhangwei</h4>
                                        <p><span aria-hidden="true" class="octicon octicon-clock">2016-1-1</span></p>

                                        <div class="media-body-btn"><a href="#" class="btn btn-default btn-sm">
                                            <span aria-hidden="true" class="octicon octicon-person"></span>已关注 </a>
                                        </div>

                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
