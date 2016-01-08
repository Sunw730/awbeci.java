<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>账户设置</title>
    <link href="/js/cropper/cropper.min.css" rel="stylesheet">
    <link href="/css/setting.css" rel="stylesheet">
    <script src="/js/cropper/cropper.min.js"></script>
    <script src="/js/cropper/canvas-toBlob.js"></script>
    <script src="/js/views/setting.js"></script>
</head>
<body>
<div class="container" id="container-main">
    <div class="row">
        <div class="row-left">
            <div class="person-info-content">
                <div class="person-info-header">
                    用户设置
                </div>
            </div>
            <div class="person-info-setlist">
                <div class="list-group">
                    <a href="#" class="list-group-item" onclick="settingProfile(1)">
                        个人资料
                        <span class="octicon octicon-chevron-right"></span>
                    </a>
                    <a href="#" class="list-group-item" onclick="settingProfile(2)">修改密码
                        <span class="octicon octicon-chevron-right"></span>
                    </a>
                </div>
            </div>
        </div>
        <div class="row-right">
            <div class="setting-content">
                <div class="person-info-content">
                    <div class="person-info-header2">

                    </div>
                </div>
                <div class="setting-detail">
                    <div class="setting-detail-tab tab1">
                        <dl>
                            <dt><label>头像图片</label></dt>
                            <dd>
                                <img id="userAvatar" class="left" src="" alt="" width="70" height="70">
                                <div class="user-avatar-info">
                                    <label class="btn btn-default">

                                        <input type="file" class="sr-only" id="inputImage" name="file" accept="image/*">

                                        上传新头像
                                    </label>
                                    <p>上传后你可以通过拖动新头像来设置</p>
                                </div>
                            </dd>
                        </dl>
                        <dl class="form-group clearboth margintop">
                            <dt><label>用户名</label></dt>
                            <dd>
                                <input id="name" name="name" type="text" class="form-control form-control-width">
                            </dd>
                        </dl>
                        <dl class="form-group clearboth">
                            <dt><label>昵称</label></dt>
                            <dd>
                                <input id="niceName" name="niceName" type="text"
                                       class="form-control form-control-width">
                            </dd>
                        </dl>
                        <dl class="form-group clearboth">
                            <dt><label>邮箱</label></dt>
                            <dd>
                                <input id="email" name="email" type="text" class="form-control form-control-width">
                            </dd>
                        </dl>

                        <p>
                            <button class="btn btn-success" onclick="updateProfile()">更新资料</button>
                        </p>
                    </div>
                    <div class="setting-detail-tab tab2">
                        <dl class="form-group clearboth">
                            <dt><label>原密码</label></dt>
                            <dd>
                                <input id="oldPwd" name="oldPwd" type="password"
                                       class="form-control form-control-width">
                            </dd>
                        </dl>
                        <dl class="form-group clearboth">
                            <dt><label>新密码</label></dt>
                            <dd>
                                <input id="newPwd" name="newPwd" type="password"
                                       class="form-control form-control-width">
                            </dd>
                        </dl>
                        <dl class="form-group clearboth">
                            <dt><label>确认新密码</label></dt>
                            <dd>
                                <input id="newPwd2" name="newPwd2" type="password"
                                       class="form-control form-control-width">
                            </dd>
                        </dl>
                        <p>
                            <button class="btn btn-success" onclick="updatePwd()">更新密码</button>
                        </p>
                    </div>
                    <div class="setting-detail-tab tab3">333</div>

                </div>
            </div>
        </div>

    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    设置新头像
                </h4>
            </div>
            <div class="modal-body">

                <div class="avatar-wrapper">
                    <img id="avatorImg" src="" alt="Picture">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <a class="btn btn-primary" id="download" href="javascript:void(0);" onclick="uploadAvator()">确定</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>