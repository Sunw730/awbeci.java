<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索</title>
    <link href="/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="/js/views/main.js"></script>
</head>
<body>
<div class="container" id="container-main">
    <div class="melogo">
        <p>Awbeci</p>
    </div>
    <div class="mesearch">

        <form  onsubmit="return baiduWithHttps(this)" action="http://www.baidu.com/baidu" target="_blank">
            <div class="input-group">
                <input name="tn" type="hidden" value="SE_zzsearchcode_shhzc78w">
                <input type="text" class="form-control" onfocus="checkHttps" name="word" baiduSug="1"  size="30">

                <div class="input-group-btn">
                    <button type="submit" class="btn btn-primary">搜索Web</button>
                </div>
            </div>
        </form>
    </div>
    <div class="metip">
        <p>欢迎您加入awbeci，QQ群(代码世界):464696550.</p>
    </div>
</div>
<script src="http://s1.bdstatic.com/r/www/cache/global/js/BaiduHttps_20150714_zhanzhang.js"></script>
<script charset="gbk" src="http://www.baidu.com/js/opensug.js"></script>

<script>
    function checkHttps() {
        BaiduHttps.useHttps();
    }
    function baiduWithHttps(formname) {
        var data = BaiduHttps.useHttps();
        if (data.s === 0) {
            return true;
        }
        else {
            formname.action = 'https://www.baidu.com/baidu' + '?ssl_s=1&ssl_c' + data.ssl_code;
            return true;
        }
    }
</script>

</body>
</html>
