<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新感觉,新体验</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/index.css">
    <script type="application/javascript" src="/js/views/login.js"></script>
</head>
<body>

<div class="mycontent">
    <div id="content-c" class="container">
        <form action="/quickRegion" id="myform">
            <div class="form-group"><input type="text" id="name" name="name" class="form-control" placeholder="用户名" required></div>
            <div class="form-group"><input type="email" id="email" name="email" class="form-control" placeholder="邮箱" required></div>
            <div class="form-group"><input type="password" id="password" name="password" class="form-control" placeholder="密码" required></div>
            <p class="text-muted">至少使用一个小写字母，一个数字和七个字符。</p>

            <div class="form-group">
                <a href="javascript:void(0)" class="btn btn-success btn-block" id="region" onclick="quickRegion()"><b>快速注册为Awbeci</b></a>
            </div>
            <p class="text-muted">当你点击注册为awbeci，默认你同意我们的<a href="#">服务条款</a>和隐私保护的条款.我们将发送一封电子邮件到你注册的邮箱.</p>
        </form>
        <h1>新感觉 新体验<br>分享你的缤纷世界</h1>
        <p class="subheading">强大的网络资源分享社区，优质的资源共享，友好的社交平台，在这里不管是工作 学习还是
            娱乐，你都可以享受到资源分享带来的快乐与便利，快快加入我们吧<a href="#">QQ群(代码世界):464696550.</a> </p>
    </div>
</div>

</body>
</html>