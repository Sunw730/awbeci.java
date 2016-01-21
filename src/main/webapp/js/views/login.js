$(function () {
    $('#submit').on('click', function (e) {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username.length <= 0) {
            Lobibox.notify('info', {
                size:'mini',
                title: 'awbeci提示',
                msg: '请输入用户名.'
            });
            return;
        }
        if (password.length < 7) {
            Lobibox.notify('info', {
                size:'mini',
                title: 'awbeci提示',
                msg: '请输入密码.'
            });
            return;
        }
        $.post('/json/loginIn.json', {
            name: username,
            password: password
        }, function (val) {
            if (val.success) {
                location.href = "/";
            }
            else {
                Lobibox.notify('info', {
                    size:'mini',
                    title: 'awbeci提示',
                    msg: '登录失败.'
                });
            }
        })
    });

    $("#username").on('change', function (e) {
        var useorname = $("#username").val();
        if (username.length > 0) {
            $("#username").tooltip('destroy');
        }
        else {
            return $("#username").tooltip('show');
        }
    })

    $("#password").on('change', function (e) {
        var password = $("#password").val();
        if (password.length > 0) {
            $("#password").tooltip('destroy');
        }
        else {
            return $("#password").tooltip('show');
        }
    });

});
//注册
function quickRegion() {
    var username = $("#name").val();
    var email = $("#email").val();
    var password = $("#password").val();
    if (username.length <= 0) {
        Lobibox.notify('info', {
            size:'mini',
            title: 'awbeci提示',
            msg: '请输入用户名.'
        });
        return;
    }
    if (email.length <= 0) {
        Lobibox.notify('info', {
            size:'mini',
            title: 'awbeci提示',
            msg: '请输入邮箱.'
        });
        return;
    }
    if (password.length < 7) {
        Lobibox.notify('info', {
            size:'mini',
            title: 'awbeci提示',
            msg: '请输入密码，至少使用一个小写字母，一个数字和七个字符。.'
        });
        return;
    }
    $.post("/json/quickRegion.json", {
        name: username,
        password: password,
        email: email
    }, function (data) {
        if (data == "注册成功") {
            location.href = "/";
        }
        else {
            Lobibox.notify('info', {
                size:'mini',
                title: 'awbeci提示',
                msg: data
            });
        }
    });
}