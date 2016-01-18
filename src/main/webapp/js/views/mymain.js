$(function () {
})

function follow(that, id, name) {
    var $that = $(that);
    if ($.trim($that.text()) == '关注') {
        $.post('/json/addFollow.json', {
            followId: id,
            followName: name
        }, function (data) {
            if (data != 0) {
                $that.empty().append('<span aria-hidden="true" class="octicon octicon-person"></span>取消关注')
            }
        });

    }
    if ($.trim($that.text()) == '取消关注') {
        $.post('/json/removeFollow.json', {
            followid: id,
        }, function (data) {
            if (data != 0) {
                $that.empty().append('<span aria-hidden="true" class="octicon octicon-person"></span>关注')
            }
        });

    }
}