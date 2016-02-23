$(function () {
    initMyFollowUser()
    initDlg();
});
var myFollowUser;
function initMyFollowUser() {
    $.post('/json/getUserInfoDlg.json',
        function (data) {
            if (data){
                myFollowUser = data;
            }
        }, 'json');
}

var showtimeoutid;
function initDlg() {
    $('.hoverUserName').hover(function (e) {
        var id = $(this).attr('id');
        var positon = $(this).position();

        $('.userInfoDlg').css({
            left: positon.left,
            top: positon.top + 22
        });

        //$("#content-info-img img").attr('src', data.user.avatarUrl);
        //$('#dynamic-time').text(data.user.createDt);
        //$("#content-info-username a").attr({
        //    href: data.user.name
        //}).text(data.user.name);
        //$('#followingCount').text(data.followingsCount);
        //$('#followerCount').text(data.followersCount);
        //$('#sitesCount').text(data.sitesCount);
        //$('#sitesCountNavigation').attr('href', data.user.name + '/navigation');
        //$('.userInfoDlg').fadeIn();
    }, function (e) {
        showtimeoutid = setTimeout(function () {
            $('.userInfoDlg').hide()
        }, 100)
    });
    $('.userInfoDlg').hover(function (e) {
        clearTimeout(showtimeoutid)
    }, function (e) {
        $('.userInfoDlg').hide();
    });
}