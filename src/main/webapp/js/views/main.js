$(function () {
    initMyFollowUser()
    initDlg();
});
var myFollowUser;//todo:后面要用redis做缓存服务
function initMyFollowUser() {
    $.post('/json/getUserInfoDlg.json',
        function (data) {
            if (data) {
                myFollowUser = data;
            }
        }, 'json');
}
var showtimeoutid;
function initDlg() {
    $('.hoverUserName').hover(function (e) {
        var id = $(this).attr('id');
        var positon = $(this).position();
        var rect = this.getBoundingClientRect();//获取div相对浏览器窗口位置
        if (rect.top < 200) {
            $('.userInfoDlg').css({
                left: positon.left,
                top: positon.top + 26
            });
            $('.hover-offset').removeClass('hover-offset-top').addClass('hover-offset-bottom')
        }
        else {
            $('.userInfoDlg').css({
                left: positon.left,
                top: positon.top - 190
            });
            $('.hover-offset').removeClass('hover-offset-bottom').addClass('hover-offset-top')
        }
        var showFlag = false;
        if (myFollowUser) {
            for (var i = 0; i < myFollowUser.length; i++) {
                if (myFollowUser[i].ID == id) {
                    $("#content-info-img img").attr('src', myFollowUser[i].AVATARURL);
                    $('#dynamic-time').text('加入于 ' + myFollowUser[i].mycreatedt);
                    $("#content-info-username a").attr({
                        href: myFollowUser[i].NAME
                    }).text(myFollowUser[i].NAME);
                    $('#followingCount').text(myFollowUser[i].followingCount);
                    $('#followerCount').text(myFollowUser[i].followerCount);
                    $('#sitesCount').text(myFollowUser[i].siteCount);
                    $('#sitesCountNavigation').attr('href', myFollowUser[i].NAME + '/navigation');
                }
                if (!showFlag && myFollowUser[i].uid == id) {
                    showFlag = true;
                }
            }
            if (!showFlag) {
                $('.userInfoDlg').fadeIn();
            }
        }
    }, function (e) {
        showtimeoutid = setTimeout(function () {
            $('.userInfoDlg').hide();
        }, 100)
    });
    $('.userInfoDlg').hover(function (e) {
        clearTimeout(showtimeoutid)
    }, function (e) {
        $('.userInfoDlg').hide();
    });
}