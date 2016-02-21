/**
 * Created by zhangwei on 2015/9/28.
 */
$(function () {
    initDlg();
});

function initDlg() {
    $('.hoverUserName').hover(function (e) {
        var id = $(this).attr('id');
        var positon = $(this).position();
        $('.userInfoDlg').css({
            left: positon.left,
            top: positon.top + 29
        });

        $.post('/json/getUserInfoDlg.json',
            {uid: id},
            function (data) {
                $("#content-info-img img").attr('src', data.user.avatarUrl);
                $('#dynamic-time').text(data.user.createDt);
                $("#content-info-username a").attr({
                    href: data.user.name
                }).text(data.user.name);
                $('#followingCount').text(data.followingsCount);
                $('#followerCount').text(data.followersCount);
                $('#sitesCount').text(data.sitesCount);
                $('#sitesCountNavigation').attr('href', data.user.name + '/navigation');
                $('.userInfoDlg').fadeIn();
            }, 'json');
    }, function (e) {
        $('.userInfoDlg').fadeOut('slow');
    });
//todo:事件代理
    $('.userInfoDlg').hover(function (e) {
        $('.userInfoDlg').show();
    },function(e){
        $('.userInfoDlg').hide();
    });
}