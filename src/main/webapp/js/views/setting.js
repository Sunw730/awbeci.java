$(function () {
    settingProfile(1);
    var $image = $('#avatorImg');
    var $modal = $('#myModal');
    var cropBoxData;
    var canvasData;

    // Bind events first
    $modal.on('shown.bs.modal', function () {
        $image.cropper({
            movable: true,
            aspectRatio: 1,
            strict: false
        });
    }).on('hidden.bs.modal', function () {
        $image.cropper('destroy');
    });

    $('#inputImage').change(function () {
        var files = this.files;
        var file;

        if (files && files.length) {
            file = files[0];

            if (/^image\/\w+$/.test(file.type)) {
                blobURL = URL.createObjectURL(file);

                $image.one('built.cropper', function () {
                    // Revoke when load complete
                    URL.revokeObjectURL(blobURL);
                });

                $image.attr('src', blobURL);
            } else {
                window.alert('Please choose an image file.');
            }
        }

        // Don't bind events in another event

        $modal.modal('show');
    });
});

function activeProfile() {
    $.post('/json/getUserInfo.json', function (data) {
        if (data) {
            $("#id").val(data.id);
            $("#userAvatar").attr('src', data.avatarUrl);
            $("#name").val(data.name);
            $("#niceName").val(data.niceName);
            $("#email").val(data.email);
        }
    }, 'json');
}

//更新个人资料
function updateProfile() {
    var name = $("#name").val();
    var niceName = $("#niceName").val();
    var email = $("#email").val();

    if ($.trim(name).length == 0) {
        return alert('请输入用户名');
    }
    if ($.trim(niceName).length == 0) {
        return alert('请输入昵称');
    }
    if ($.trim(email).length == 0) {
        return alert('请输入邮箱');
    }

    $.post("/json/updateProfile.json", {
        name: name,
        niceName: niceName,
        email: email
    }, function (data) {
        if (data > 0) {
            alert('更新成功');
        }
        else {
            alert('更新失败');
        }
    })
}

//点击类型
function settingProfile(type) {
    $('.setting-detail-tab').removeClass('tabActive');
    switch (type) {
        case 1:
            $(".tab1").addClass('tabActive');
            $(".person-info-header2").text('个人资料');
            activeProfile();
            break;
        case 2:
            $(".tab2").addClass('tabActive');
            $(".person-info-header2").text('修改密码');
            $("#oldPwd").val('');
            $("#newPwd").val('');
            $("#newPwd2").val('');
            break;
        case 3:
            $(".tab3").addClass('tabActive');
            $(".person-info-header2").text('其它');
            break;
    }
}

//更新密码
function updatePwd() {
    var oldPwd = $("#oldPwd").val();
    var newPwd = $("#newPwd").val();
    var newPwd2 = $("#newPwd2").val();

    if ($.trim(oldPwd).length == 0 || $.trim(oldPwd).length < 7) {
        return alert('请输入旧密码，并且密码为至少使用一个小写字母，一个数字和七个字符。');
    }
    if ($.trim(newPwd).length == 0 || $.trim(newPwd).length < 7) {
        return alert('请输入新密码，并且密码为至少使用一个小写字母，一个数字和七个字符。');
    }
    if ($.trim(newPwd2).length == 0 || $.trim(newPwd2).length < 7) {
        return alert('请确认新密码，并且密码为至少使用一个小写字母，一个数字和七个字符。');
    }

    if ($.trim(newPwd).length != $.trim(newPwd2).length || newPwd != newPwd2) {
        return alert('两次新密码输入不一致');
    }
    $.post("/json/updatePassword.json", {
        oldPwd: oldPwd,
        newPwd: newPwd,
        newPwd2: newPwd2
    }, function (data) {
        if (data > 0) {
            alert('更新成功');
        }
        else {
            alert('更新失败');
        }
    })
}

//上传头像
function uploadAvator() {
    var $avatar = $("#userAvatar");
    var $image = $('#avatorImg');
    var $modal = $('#myModal');
    $image.cropper('getCroppedCanvas').toBlob(function (blob) {
        var formData = new FormData();
        formData.append('croppedImage', blob);
        formData.append('avatarUrl', $avatar.attr('src'));
        $.ajax('/json/uploadAvatar.json', {
            method: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data) {
                    $modal.modal('hide');
                    $avatar.attr('src', data);
                }
            },
            error: function () {
                console.log('Upload error');
            }
        });
    });
    //$avatarForm = $("#avatarForm");
    //var data = new FormData($avatarForm[0]);
    //var _this = this;
    //$.ajax({
    //    url: '/json/uploadAvatar.json',
    //    type: 'post',
    //    data: data,
    //    dataType: 'json',
    //    processData: false,
    //    contentType: false,
    //    success: function (data) {
    //
    //    },
    //
    //});
}

