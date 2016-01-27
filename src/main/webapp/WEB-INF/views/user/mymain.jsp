<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <img src="${current_user.avatarUrl}" alt="${current_user.avatarUrl}">
                <div class="caption">
                    <div class="userinfoname">${current_user.name}</div>
                    <div class="userinfonicename">${current_user.niceName}</div>
                    <ul class="userinfocontent">
                        <c:if test="${current_user.location != null}">
                            <li>
                                <span aria-hidden="true" class="octicon octicon-location"></span>
                                    ${current_user.location}
                            </li>
                        </c:if>

                        <li>
                            <span aria-hidden="true" class="octicon octicon-mail"></span>
                            <a class="email"
                               href="mailto:${current_user.email}" title="${current_user.email}">
                                ${current_user.email}</a>
                        </li>
                        <c:if test="${current_user.url != null}">
                            <li>
                            <span aria-hidden="true"
                                  class="octicon octicon-link"></span>
                                <a href="${current_user.url}" class="url" rel="nofollow me"
                                   title="${current_user.url}">${current_user.url}</a>
                            </li>
                        </c:if>

                        <li>
                            <span aria-hidden="true" class="octicon octicon-clock"></span>
                            <span
                                    class="join-label">加入于 </span>
                            <time class="join-date" datetime="<spring:eval expression="current_user.createDt" />"
                                  day="numeric"
                                  is="local-time"
                                  month="short" year="numeric"
                                  title="<spring:eval expression="current_user.createDt" />">
                                <spring:eval expression="current_user.createDt"/>
                            </time>
                        </li>
                    </ul>
                    <div class="websiteinfo">
                        <a href="${current_user.name}/navigation" class="btn btn-success  btn-block">
                            进入我的导航
                        </a>
                    </div>
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
                                关注者<span class="badge">${followersCount}</span>
                            </a></li>
                         <span class="pull-right">
                             <c:if test="${sessionScope.uid == null  || !isme}">
                                 <!-- 设置判断标签，下同 -->
                                 <c:set value="no" var="followFlag"></c:set>
                                 <c:forEach items="${myFollowings}" var="myFollowing">
                                     <c:if test="${current_user.id == myFollowing.followId}">
                                         <!-- 如果当前用户id等于我所关注的用户id，那么设置变量yes，下同 -->
                                         <c:set value="yes" var="followFlag"></c:set>
                                     </c:if>
                                 </c:forEach>
                                 <!-- 如果=yes说明已经关注了，下同 -->
                                 <c:choose>
                                     <c:when test="${fn:contains(followFlag, 'yes')}">
                                         <a href="javascript:void(0)" class="btn btn-default btn-sm"
                                            onclick="follow(this,'${sessionScope.current_user.id}','${sessionScope.current_user.name}')">
                                             <span aria-hidden="javascript:void(0)true"
                                                   class="octicon octicon-person"></span>
                                             取消关注 </a>
                                     </c:when>
                                     <c:otherwise>
                                         <a href="javascript:void(0)" class="btn btn-default btn-sm"
                                            onclick="follow(this,'${sessionScope.current_user.id}','${sessionScope.current_user.name}')">
                                             <span aria-hidden="true" class="octicon octicon-person"></span>
                                             关注 </a>
                                     </c:otherwise>
                                 </c:choose>
                             </c:if>
                        </span>
                    </ul>

                    <div id="myTabContent" class="tab-content">
                        <div class="tab-pane fade in active" id="following">
                            <ul class="media-list">
                                <c:forEach items="${followings}" var="following">
                                    <li class="media">
                                        <div class="media-left">
                                            <a href="${path}/${following.user.name}">
                                                <img width="75" height="75" class="media-object"
                                                     src="${following.user.avatarUrl}"
                                                     alt="${following.user.avatarUrl}">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">${following.user.name}</h4>
                                            <p><span aria-hidden="true" class="octicon octicon-clock">
                                                <fmt:formatDate pattern="yyyy年MM月dd日"
                                                                value="${following.user.createDt}"></fmt:formatDate>
                                            </span>
                                            </p>
                                            <div class="media-body-btn">
                                                <c:set value="no" var="followFlag"></c:set>
                                                <c:forEach items="${myFollowings}" var="myFollowing">
                                                    <c:if test="${following.followId == myFollowing.followId}">
                                                        <c:set value="yes" var="followFlag"></c:set>
                                                    </c:if>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="${fn:contains(followFlag, 'yes')}">
                                                        <a href="javascript:void(0)" class="btn btn-default btn-sm"
                                                           onclick="follow(this,'${following.user.id}','${following.user.name}')">
                                                            <span aria-hidden="true"
                                                                  class="octicon octicon-person"></span>
                                                            取消关注 </a>
                                                    </c:when>
                                                    <c:when test="${following.followId == sessionScope.uid}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="javascript:void(0)" class="btn btn-default btn-sm"
                                                           onclick="follow(this,'${following.user.id}','${following.user.name}')">
                                                            <span aria-hidden="true"
                                                                  class="octicon octicon-person"></span>
                                                            关注 </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="tab-pane fade" id="follower">
                            <ul class="media-list">
                                <c:forEach items="${followers}" var="follower">
                                    <li class="media">
                                        <div class="media-left">
                                            <a href="${path}/${follower.user.name}">
                                                <img width="75" height="75" class="media-object"
                                                     src="${follower.user.avatarUrl}" alt="${follower.user.avatarUrl}">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading">${follower.user.name}</h4>
                                            <p><span aria-hidden="true" class="octicon octicon-clock">
                                                <fmt:formatDate pattern="yyyy年MM月dd日"
                                                                value="${follower.user.createDt}"></fmt:formatDate>
                                            </span>
                                            </p>

                                            <div class="media-body-btn">
                                                <c:set value="no" var="followFlag"></c:set>
                                                <c:forEach items="${myFollowings}" var="myFollowing">
                                                    <c:if test="${follower.uid == myFollowing.followId}">
                                                        <c:set value="yes" var="followFlag"></c:set>
                                                    </c:if>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="${fn:contains(followFlag, 'yes')}">
                                                        <a href="javascript:void(0)" class="btn btn-default btn-sm"
                                                           onclick="follow(this,'${follower.user.id}','${follower.user.name}')">
                                                            <span aria-hidden="true"
                                                                  class="octicon octicon-person"></span>
                                                            取消关注 </a>
                                                    </c:when>
                                                    <c:when test="${follower.uid == sessionScope.uid}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="javascript:void(0)" class="btn btn-default btn-sm"
                                                           onclick="follow(this,'${follower.user.id}','${follower.user.name}')">
                                                            <span aria-hidden="true"
                                                                  class="octicon octicon-person"></span>
                                                            关注 </a>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
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
