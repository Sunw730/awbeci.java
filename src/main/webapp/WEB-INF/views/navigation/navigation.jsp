<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>个性导航</title>
    <link href="/js/bootstrap-treeview/src/css/bootstrap-treeview.css" rel="stylesheet">
    <link href="/js/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="/js/github/github.css" rel="stylesheet">
    <link href="/css/navigation.css" rel="stylesheet">
    <script type="text/javascript" src="/js/dragsort-0.5.2/jquery.dragsort-0.5.2.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap-treeview/src/js/bootstrap-treeview.js"></script>
    <script type="text/javascript" src="/js/bootstrap-select/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="/js/github/github.js"></script>
    <script type="text/javascript" src="/js/views/navigation.js"></script>
</head>
<body>
<div class="container" id="container-main">
    <div class="row">
        <div class="col-lg-3 rowcol-left">
            <div class="treeview-head">分类列表
                <div class="treeview-head-right">
                    <a href="#" class="treeview-head-icon-right">
                        <span id="addcategory" class="octicon octicon-plus" data-toggle="tooltip" data-placement="top"
                              title="添加"></span>
                    </a>

                    <a href="#" class="treeview-head-icon-right">
                         <span id="editcategory" class=" octicon octicon-pencil" data-toggle="tooltip"
                               data-placement="top" title="编辑"></span>
                    </a>
                </div>
            </div>
            <div class="sidebar-module">

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
                    </div>
                    <div class="form-group">
                        <select id="categoryType" class="selectpicker  show-tick" data-size="8" data-live-search="true">
                        </select>
                    </div>
                    <div class="categorytoolbar">
                        <button type="button" class="btn btn-success btn-sm" onclick="saveCategory()">确定</button>
                        <button type="button" class="btn btn-danger btn-sm" onclick="canceleditNav()">取消</button>
                    </div>
                </div>

            </div>
        </div>
        <div class="col-lg-9 rowcol-right">
            <div class="treeview-head">网址列表
                <div class="treeview-head-right">
                    <a href="#" class="treeview-head-icon-right">
                        <span id="addsite" class="octicon octicon-plus" data-toggle="tooltip" data-placement="top" title="" data-original-title="添加"></span>
                    </a>

                    <a href="#" class="treeview-head-icon-right">
                        <span id="editsite" class=" octicon octicon-pencil" data-toggle="tooltip" data-placement="top" title="" data-original-title="编辑"></span>
                    </a>
                </div>
            </div>
            <div id="showlink">
                <div class="btn-search">
                    <form action="http://www.baidu.com/baidu" target="_blank">
                        <div class="input-group input-group-sm">
                            <input name="tn" type="hidden" value="baidu">
                            <input type="text" class="form-control" placeholder="请输入查询内容" name="word" size="30"
                                   baiduSug="1">

                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-default">搜索</button>
                            </div>
                        </div>
                        <!-- /btn-group -->

                    </form>
                </div>
                <ul>
                    <li>
                        <div class="showlinkicon"><a target="_blank" href="http://www.solidot.org/?theme=clightgreen">
                            <img width="16px" height="16px" style="margin-right:5px;"
                                 src="http://www.solidot.org/favicon.ico">奇客
                            </img>
                        </a>
                            <span class="linkedit linkediticon octicon octicon-pencil"></span>
                            <span class="linkedit linkdelicon octicon octicon-x"></span></div>
                    </li>
                    <li>
                        <div class="showlinkicon"><a target="_blank" href="http://www.baidu.com">
                            <img width="16px" height="16px" style="margin-right:5px;"
                                 src="http://www.solidot.org/favicon.ico">百度
                            </img>
                        </a>
                            <span class="linkedit linkediticon octicon octicon-pencil"></span>
                            <span class="linkedit linkdelicon octicon octicon-x"></span></div>
                    </li>
                    <li>
                        <div class="showlinkicon"><a target="_blank" href="http://www.solidot.org/?theme=clightgreen">
                            <img width="16px" height="16px" style="margin-right:5px;"
                                 src="http://www.solidot.org/favicon.ico">奇客
                            </img>
                        </a>
                            <span class="linkedit linkediticon octicon octicon-pencil"></span>
                            <span class="linkedit linkdelicon octicon octicon-x"></span></div>
                    </li>
                    <li>
                        <div class="showlinkicon"><a target="_blank" href="http://www.solidot.org/?theme=clightgreen">
                            <img width="16px" height="16px" style="margin-right:5px;"
                                 src="http://www.solidot.org/favicon.ico">奇客
                            </img>
                        </a>
                            <span class="linkedit linkediticon octicon octicon-pencil"></span>
                            <span class="linkedit linkdelicon octicon octicon-x"></span></div>
                    </li>
                    <li>
                        <div class="showlinkicon"><a target="_blank" href="http://www.solidot.org/?theme=clightgreen">
                            <img width="16px" height="16px" style="margin-right:5px;"
                                 src="http://www.solidot.org/favicon.ico">奇客
                            </img>
                        </a>
                            <span class="linkedit linkediticon octicon octicon-pencil"></span>
                            <span class="linkedit linkdelicon octicon octicon-x"></span></div>
                    </li>
                    <li>
                        <div class="showlinkicon"><a target="_blank" href="http://www.solidot.org/?theme=clightgreen">
                            <img width="16px" height="16px" style="margin-right:5px;"
                                 src="http://www.solidot.org/favicon.ico">奇客
                            </img>
                        </a>
                            <span class="linkedit linkediticon octicon octicon-pencil"></span>
                            <span class="linkedit linkdelicon octicon octicon-x"></span></div>
                    </li>
                    <li>
                        <div class="showlinkicon"><a target="_blank" href="http://www.solidot.org/?theme=clightgreen">
                            <img width="16px" height="16px" style="margin-right:5px;"
                                 src="http://www.solidot.org/favicon.ico">奇客
                            </img>
                        </a>
                            <span class="linkedit linkediticon octicon octicon-pencil"></span>
                            <span class="linkedit linkdelicon octicon octicon-x"></span></div>
                    </li>
                    <li>
                        <div class="showlinkicon"><a target="_blank" href="http://www.solidot.org/?theme=clightgreen">
                            <img width="16px" height="16px" style="margin-right:5px;"
                                 src="http://www.solidot.org/favicon.ico">奇客
                            </img>
                        </a>
                            <span class="linkedit linkediticon octicon octicon-pencil"></span>
                            <span class="linkedit linkdelicon octicon octicon-x"></span></div>
                    </li>

                </ul>
                <div class="editlinkdlg">
                    <div class="dlgHeader">
                        <button type="button"id="siteClose" class="close" aria-hidden="true">×</button>
                        <span class="header-title">编辑</span>
                    </div>
                    <div class="dlgBody">
                        <div class="form-group">
                            <input type="text" class="form-control" id="linkeditname">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="linkediturl">
                        </div>
                        <div class="form-group">
                            <select id="siteType" class="selectpicker  show-tick">
                                <option value="1">导航</option>
                                <option value="2">网址</option>
                            </select>
                        </div>
                        <div class="sitetoolbar  form-group-sm">
                            <button type="button" class="btn btn-success btn-sm">确定</button>
                            <button type="button" class="btn btn-danger btn-sm" onclick="canceleditLink()">取消</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</body>
<script charset="gbk" src="http://www.baidu.com/js/opensug.js"></script>
</html>