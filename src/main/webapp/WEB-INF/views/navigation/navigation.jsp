<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>我的导航</title>
    <link href="/js/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="/css/navigation.css" rel="stylesheet">
    <link href="/js/ladda/ladda-themeless.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/ladda/spin.min.js"></script>
    <script type="text/javascript" src="/js/ladda/ladda.min.js"></script>
    <script type="text/javascript" src="/js/dragsort-0.5.2/jquery.dragsort-0.5.2.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-select/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="/js/views/navigation.js"></script>
</head>
<body>
<div class="container" id="container-main">
    <c:if test="${sessionScope.user == null || !isme}">
        <div class="currUserInfo">
            <c:choose>
                <c:when test="${hadFollow}">
                    <ul>
                        <li><a href="javascript:void(0)" class="btn btn-default btn-sm"
                               onclick="follow(this,'${sessionScope.current_user.id}','${sessionScope.current_user.name}')">
                            <span aria-hidden="true" class="octicon octicon-person commonOction"></span>
                            取消关注</a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul>
                        <li><a href="javascript:void(0)" class="btn btn-default btn-sm"
                               onclick="follow(this,'${sessionScope.current_user.id}','${sessionScope.current_user.name}')">
                            <span aria-hidden="true" class="octicon octicon-person commonOction"></span>
                            关注</a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>

            <div class="currUserInfo-detail">
                <a href="/${sessionScope.current_user.name}">
                    <img src="${sessionScope.current_user.avatarUrl}" alt="" width="30" height="30">
                </a>
                <a href="/${sessionScope.current_user.name}">
                        ${sessionScope.current_user.name}
                </a>
                <c:if test="${sessionScope.current_user.niceName != null}">
                    ( ${sessionScope.current_user.niceName} )
                </c:if>
            </div>
        </div>
    </c:if>
    <div class="row">
        <div class="col-lg-3 rowcol-left">
            <div class="treeview-head">
                <strong>分类列表</strong>
                <div class="treeview-head-right">
                    <c:if test="${sessionScope.user != null && isme}">
                        <a href="javascript:void(0)" class="treeview-head-icon-right" onclick="addcategory(this)">
                            <span class="octicon octicon-plus"></span>
                            添加
                        </a>

                        <a href="javascript:void(0)" onclick="showEditCategoryBtn()">
                            <span class="octicon octicon-pencil"></span>
                            编辑
                        </a>
                    </c:if>
                    <a id="backCategory" href="javascript:void(0)" onclick="backCategory()" style="padding-left: 10px;">
                        <span class="octicon octicon-mail-reply"></span>
                        返回
                    </a>
                </div>
            </div>
            <div class="sidebar-module">
                <div class="sidebar-module-search">
                    <div class="btn-search">
                        <div class="form-group form-group-sm" style="margin-bottom: 0">
                            <input id="txtQueryCategory" type="text" class="form-control" placeholder="请输入查询内容">
                        </div>
                        <!-- /btn-group -->
                    </div>
                </div>

                <ul id="category-list" class="list-group">
                </ul>
            </div>
            <!-- /sidebar-module -->
            <div class="editnavdlg">
                <div class="dlgHeader">
                    <button type="button" id="categoryClose" class="close" aria-hidden="true">×</button>
                    <span class="header-title">编辑</span>
                </div>
                <div class="dlgBody">
                    <div class="form-group">
                        <input type="text" class="form-control" id="categoryName" placeholder="请输入名称">
                        <input type="hidden" id="categoryId">
                    </div>
                </div>
                <div class="categorytoolbar">
                    <button id="addCategory" type="button" class="btn btn-success" onclick="saveCategory()">
                        <span class="octicon octicon-check myoction"></span>确定

                    </button>
                    <button type="button" class="btn btn-danger btn-sm" onclick="canceleditNav()"><span
                            class="octicon octicon-x myoction"></span>取消
                    </button>
                </div>
            </div>
        </div>
        <div class="col-lg-9 rowcol-right">
            <div class="treeview-head">
                <strong>网址列表</strong>
                <div class="treeview-head-right">
                    <c:if test="${sessionScope.user != null && isme}">
                        <a href="javascript:void(0)" class="treeview-head-icon-right" onclick="showAddSiteDlg(this)">
                            <span class="octicon octicon-plus"></span>
                            添加
                        </a>

                        <a href="javascript:void(0)" onclick="showEditSiteOction()">
                            <span class=" octicon octicon-pencil"></span>
                            编辑
                        </a>
                    </c:if>
                </div>
            </div>
            <div id="showlink">
                <div class="btn-search">
                    <div class="form-group form-group-sm" style="margin-bottom: 0">
                        <input id="txtQuerySite" type="text" class="form-control" placeholder="请输入查询内容">
                    </div>
                    <!-- /btn-group -->
                </div>
                <ul id="showmysite">
                </ul>
                <div class="editlinkdlg">
                    <div class="dlgHeader">
                        <button type="button" id="siteClose" class="close" aria-hidden="true">×</button>
                        <span class="header-title">编辑</span>
                    </div>
                    <div class="dlgBody">
                        <div class="form-group">
                            <input type="hidden" id="siteid">
                            <input type="text" class="form-control" id="sitename" placeholder="请输入名称">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="siteurl" placeholder="请输入网址">
                        </div>
                        <div class="form-group">
                            <select id="siteType" class="selectpicker  show-tick" data-size="10">
                            </select>
                        </div>
                    </div>
                    <div class="sitetoolbar  form-group-sm">
                        <button type="button" class="btn btn-success btn-sm" onclick="saveSite()"><span
                                class="octicon octicon-check myoction"></span>确定
                        </button>
                        <button type="button" class="btn btn-danger btn-sm" onclick="canceleditLink()"><span
                                class="octicon octicon-x myoction"></span>取消
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script charset="gbk" src="http://www.baidu.com/js/opensug.js"></script>
</html>