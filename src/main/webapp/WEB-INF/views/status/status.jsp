<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新状态</title>
    <link href="/js/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/bootstrap-select/js/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="/js/views/status.js"></script>
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
        <div class="bug-control">
            <form>
                <div class="form-group">
                    <div>
                        <label>类型</label>
                    </div>

                    <select id="type" class="selectpicker" data-size="10">
                        <option value="1">新增</option>
                        <option value="2">已修复</option>
                        <option value="3">建议</option>
                        <option value="4">正在修复</option>
                        <option value="5">未修复</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>内容</label>
                    <textarea id="content" class="form-control" rows="3"></textarea>
                </div>
                <p class="text-right" style="margin-top: 10px">
                    <a href="javascript:void(0)" class="btn btn-success" onclick="editStatus()">编辑</a>
                    <a href="javascript:void(0)" class="btn btn-success" onclick="saveStatus()">添加</a>
                    <a href="javascript:void(0)" class="btn btn-danger" onclick="deleteStatus()">删除</a>
                </p>
            </form>
        </div>
        <%--<span class="label label-primary">新功能</span>--%>
        <%--<span class="label label-success">已修复</span>--%>
        <%--<span class="label label-info">建议</span>--%>
        <%--<span class="label label-warning">正在修复</span>--%>
        <%--<span class="label label-danger">未修复</span>--%>
        <div id="bug-repeat">

        </div>

    </div>
</div>
</body>
</html>
