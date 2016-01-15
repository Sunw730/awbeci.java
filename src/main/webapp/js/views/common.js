$(function () {
    $("[data-toggle='tooltip']").tooltip();
    $("#container-main").css({'margin-top': '60px'});
});
function region() {
    location.href = '/region';
}

function login() {
    location.href = "/login";
}

function quit() {
    $.post('/json/loginOut.json', function (data) {
        if (data.success) {
            location.href = '/';
        }
    })
}