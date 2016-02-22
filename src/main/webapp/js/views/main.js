/**
 * Created by zhangwei on 2015/9/28.
 */
$(function () {
    initDlg();
});

function initDlg() {
    $('.container-center').on('mouseenter mouseleave',
        function (event) {
            var $findhovera = $(this).children().find('a.hoverUserName');
            if (event.type == 'mouseenter') {
                var id = $findhovera.attr('id');
                var positon = $findhovera.position();
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
                        $('.userInfoDlg').fadeTo('fast', 1);
                    }, 'json');
            } else {
                var $target = event.relatedTarget
                $('.userInfoDlg').hide();
            }
        });

    //$('.userInfoDlg').on('mouseleave', function (e) {
    //    $('.userInfoDlg').hide();
    //});
}