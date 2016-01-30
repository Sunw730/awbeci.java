<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新状态</title>
    <link href="/js/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/bootstrap-select/js/bootstrap-select.min.js"></script>
    <style>
        .pagehead {
            border-bottom: 1px solid #eee;
            margin-bottom: 20px;
        }

        .status-content {
            width: 650px;
            margin: 0 auto;
        }

        .bug-content {
            margin: 10px 0;
        }

        .bug-info {
            margin-left: 10px;
        }

        .bug-control {
            margin: 50px 0;
        }
    </style>
</head>
<body>
<div class="pagehead">
    <div class="status-content">
        <h3><strong>网站更新情况</strong></h3>
    </div>
</div>
<div class="status-content">
    <div>
        <p class="bg-info" style="padding: 15px;border-radius: 4px;font-size: 14px">
            <span class="octicon octicon-light-bulb"></span>提示：为了更好的使用awbeci网站，请定期查看该页面以便了解网站bug以及修复情况
        </p>
        <%--<span class="label label-primary">新功能</span>--%>
        <%--<span class="label label-success">已修复</span>--%>
        <%--<span class="label label-info">建议</span>--%>
        <%--<span class="label label-warning">正在修复</span>--%>
        <%--<span class="label label-danger">未修复</span>--%>
        <div class="bug-content">
             <span class="label label-success">
            已修复
        </span>
            <small class="bug-info">没有关注人员的div高度有问题</small>
        </div>

        <div class="bug-content">
            <span class="label label-success">
            已修复
        </span>
            <small class="bug-info">没有关注人员的div高度有问题</small>
        </div>
        <div class="bug-control">
            <form>
                <div class="form-group">
                    <div>
                        <label>类型</label>
                    </div>

                    <select id="type" class="selectpicker" data-size="10">
                        <option>新增</option>
                        <option>已修复</option>
                        <option>建议</option>
                        <option>正在修复</option>
                        <option>新问题</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>内容</label>
                    <textarea class="form-control" rows="3"></textarea>
                </div>
                <p class="text-right" style="margin-top: 10px">
                    <button type="submit" class="btn btn-success">添加</button>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
