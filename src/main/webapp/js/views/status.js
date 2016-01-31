/**
 * Created by zhangwei on 2016/1/31.
 */

$(function () {
    initInfo();
});

function initInfo() {
    $("#bug-repeat").empty();
    $.post('/json/getAllStatus.json', function (data) {
        var html = '';
        if (data) {
            for (var i = 0; i < data.length; i++) {
                var type = data[i].type;
                var typeTxt = '';
                var typeClass = '';
                switch (type) {
                    case 1:
                        typeTxt = '新功能';
                        typeClass = 'primary';
                        break;
                    case 2:
                        typeTxt = '已修复';
                        typeClass = 'success';
                        break;
                    case 3:
                        typeTxt = '建议';
                        typeClass = 'info';
                        break;
                    case 4:
                        typeTxt = '正在修复';
                        typeClass = 'warning';
                        break;
                    case 5:
                        typeTxt = '未修复';
                        typeClass = 'danger';
                        break;
                    default:
                        typeTxt = '';
                        typeClass = '';
                        break;
                }
                html += '<div class="bug-content">';
                html += '<span class="label label-' + typeClass + '">';
                html += typeTxt;
                html += ' </span>';
                html += '<a href="javascript:void(0)" bugid="' + data[i].id + '" type="' + data[i].type + '" content="' + data[i].content + '" onclick="selectBugInfo(this)">' +
                    '<small class="bug-info">' + data[i].content + '</small></a>' +
                    '<small style="margin-left: 10px;color: #767676;font-size: 12px;">' + data[i].updateDt + '</small>';
                html += '</div>';
            }
            $("#bug-repeat").append(html);
        }
    });
}

function saveStatus() {
    var type = $('#type').val();
    var content = $('#content').val();
    if ($.trim(content).length == 0) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请输入内容.'
        });
        return;
    }
    var postData = {type: type, content: content};
    $.post('/json/saveStatus.json',
        postData, function (data) {
            if (data == "success") {
                Lobibox.notify('info', {
                    size: 'mini',
                    title: 'awbeci提示',
                    msg: '添加成功.'
                });
                initInfo();
            }
            else {
                Lobibox.notify('error', {
                    size: 'mini',
                    title: 'awbeci提示',
                    msg: '添加失败.'
                });
            }
        }, 'json');
}
var bugid = '';
function selectBugInfo(that) {
    bugid = $(that).attr('bugid');
    var type = $(that).attr('type');
    var content = $(that).attr('content');
    $('#type').selectpicker('val', type);
    $('#content').val(content);
}

function editStatus() {
    if (!bugid) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请先选择一条数据.'
        });
        return;
    }
    var type = $('#type').val();
    var content = $('#content').val();
    if ($.trim(content).length == 0) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请输入内容.'
        });
        return;
    }
    var postData = {id: bugid, type: type, content: content};
    $.post('/json/updateStatus.json',
        postData, function (data) {
            if (data == "success") {
                Lobibox.notify('info', {
                    size: 'mini',
                    title: 'awbeci提示',
                    msg: '修改成功.'
                });
                initInfo();
            }
            else {
                Lobibox.notify('error', {
                    size: 'mini',
                    title: 'awbeci提示',
                    msg: '修改失败.'
                });
            }
        }, 'json');
}

function deleteStatus() {
    if (!bugid) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请先选择一条数据.'
        });
        return;
    }
    $.post('/json/deleteStatus.json', {
        id: bugid
    }, 'json')
}
