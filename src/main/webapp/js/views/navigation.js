var categoryflag = '';
var siteflag = '';
//点击分类的时候(注意是子分类)
var categoryObj = [];//导航分类层次数组
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}
$(function () {

    $("[data-toggle='tooltip']").tooltip({html: true});
    //$("#showlink ul").dragsort({});
    initCategory('', 1);
    $('#categoryClose').on('click', function () {
        canceleditNav();
    });
    $('#siteClose').on('click', function () {
        canceleditLink();
    });
    querySite();
    queryCategory();
    var name = GetQueryString('domainName');
    initQuerySite(name);
    //todo:setTimeout("$('body').showLoading();",2000);

});

function initQuerySite(name) {
    if ($.trim(name).length > 0) {
        $.post('/json/querySiteByDomainName.json', {
            param: name
        }, function (data) {
            showSite(data);
        })
    }
    else {
        initSite();
    }
}

//按回车查询网址
function querySite() {
    $('#txtQuerySite').keydown(function (e) {
        if (e.keyCode == 13) {
            var param = $('#txtQuerySite').val();
            $.post('/json/querySiteByParam.json', {
                param: param
            }, function (data) {
                showSite(data);
            })
        }
    });
}

//按回车查询分类
function queryCategory() {
    $('#txtQueryCategory').keydown(function (e) {
        if (e.keyCode == 13) {
            var depth = $('#category-list').attr('depth');
            var pid = $('#category-list').attr('pid');
            var param = $('#txtQueryCategory').val();
            initCategory(pid, depth, $.trim(param));
        }
    });
}

function initSite() {
    $.post('/json/getSiteByMostClick.json', function (data) {
        showSite(data);
    });
}

//根据所传数据显示网址
function showSite(data) {
    var html = '';
    $('#showmysite').empty();
    for (var i = 0; i < data.length; i++) {
        html += '<li>';
        html += '<div class="showlinkicon">';
        html += '<a id="' + data[i].id + '" target="_blank" href="' + data[i].url + '" categoryid="' + data[i].categoryId + '">';
        html += '<img width="16px" height="16px" style="margin-right:5px;" src="' + data[i].icon + '">';
        html += '<span class="siteName">' + data[i].name + '</span>';
        html += ' </img>';
        html += '</a>';
        html += '<a href="javascript:void(0)" onclick="showEditSiteDlg(this)"><span class="linkedit linkediticon octicon octicon-pencil"></span></a>';
        html += '<a href="javascript:void(0)" onclick="delSite(this)"><span class="linkedit linkdelicon octicon octicon-x"></span></a></div>';
        html += ' </li>';
    }
    $('#showmysite').append(html);
    $('.linkedit').addClass('hide');
}

//返回上一级分类
function backCategory() {
    categoryObj.pop();
    var category = categoryObj.pop();
    initCategory(category.pid, category.depth);
}

//添加到分类层级数组中
function pushCategory(pid, depth) {
    var iscategoryObj = false;
    for (var i = 0; i < categoryObj.length; i++) {
        if (categoryObj[i].pid == pid && categoryObj[i].depth == depth) {
            iscategoryObj = true;
        }
    }
    if (!iscategoryObj) {
        categoryObj.push({pid: pid, depth: depth});
    }
}

//初始化分类
function initCategory(pid, depth, param) {

    pushCategory(pid, depth);
    $('#category-list').attr('depth', depth);
    $('#category-list').attr('pid', pid);
    $('#category-list').empty();
    if (depth > 1) {
        $('#backCategory').css({'display': 'inline-block'});
    }
    else {
        $('#backCategory').css({'display': 'none'});
    }

    $.post('/json/getCategoryByUid.json', {
        pid: pid,
        name: param
    }, function (data) {
        var html = '';
        for (var i = 0; i < data.length; i++) {
            html += '  <li class="list-group-item">' +
                ' <a href="javascript:void(0)" categoryid="' + data[i].id + '" onclick="clickCategoryShowSite(this,\'' + data[i].id + '\')">' +
                ' <span class="categoryname">' + data[i].name + '</span>' +
                '  </a>' +
                '  <a href="javascript:void(0)" onclick="editCategory(this)">' +
                ' <span class="octicon octicon-pencil oction-category depth-right-edit" ></span>' +
                ' </a>' +
                ' <a href="javascript:void(0)" onclick="delCategory(this,\'' + data[i].id + '\',' + data[i].sitescount + ',' + data[i].categorycount + ')">' +
                '  <span class="octicon octicon-x oction-category depth-right-del"></span>' +
                '  </a>' +
                '  <a href="javascript:void(0)" onclick="initCategory(\'' + data[i].id + '\',' + (depth + 1) + ')">' +
                '   <span class="octicon octicon-chevron-right depth-right-in"></span>' +
                '   </a>' +
                '   </li>';
        }
        $('#category-list').append($(html));
        $('.oction-category').addClass('hide');
    }, 'json');
}

//点击分类的时候显示网址列表
function clickCategoryShowSite(that, id) {
    $('#category-list li').removeClass('list-group-item-active');
    $(that).parent().addClass('list-group-item-active');
    $.post('/json/getSiteByCategoryId.json', {
        categoryId: id
    }, function (data) {
        showSite(data);
    }, 'json');
}

function addSiteShowSite(id) {
    $.post('/json/getSiteByCategoryId.json', {
        categoryId: id
    }, function (data) {
        showSite(data);
    }, 'json');
}

//绑定网址
function bindSiteForCategory(pid, bindid) {
    $('#siteType').empty();
    $.post('/json/getCategoryByUid.json', {
        pid: pid
    }, function (data) {
        if (data.length == 0) {
            Lobibox.notify('info', {
                size: 'mini',
                title: 'awbeci提示',
                msg: '请先添加分类'
            });
            return;
        }
        var html = '';
        for (var i = 0; i < data.length; i++) {
            if (bindid == data[i].id) {
                html += '<option value="' + data[i].id + '" selected="selected">' + data[i].name + '</option>';
            }
            else {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            }
        }
        $('#siteType').append(html);
        $('#siteType').selectpicker('refresh');
        $('.editlinkdlg').addClass('show');
    }, 'json');
}

//添加分类
function addcategory(that) {
    categoryflag = 'add';
    $('.header-title').text('添加');
    var positon = $(that).position();
    $("#categoryName").val('');
    $('#categoryId').val('');
    $('.editnavdlg').css({
        left: '1px',
        top: positon.top + 31
    });
    $('.editnavdlg').show()
}

//保存分类
function saveCategory(that) {
    var l = Ladda.create(that);
    l.start();
    var depth = $('#category-list').attr('depth');
    var pid = $('#category-list').attr('pid');
    var categoryname = $('#categoryName').val();
    if ($.trim(categoryname).length == 0) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请输入完整.'
        });
        return;
    }
    $.post('/json/saveCategory.json', {
            id: $('#categoryId').val(),
            name: categoryname,
            pid: pid,
            depth: depth,
            flag: categoryflag
        }, function (data) {
            if (data != 0) {
                canceleditNav();
                if (categoryflag == 'update') {
                    $('.editCategoryName').prev().find('.categoryname').text(categoryname);
                }
                else {
                    initCategory(pid, depth);
                }
            }
            else {
                Lobibox.notify('info', {
                    size: 'mini',
                    title: 'awbeci提示',
                    msg: '添加失败.'
                });
                return;
            }
        }, 'json')
        .always(function () {
            l.stop();
        });
}

//添加网址
function showAddSiteDlg(that) {
    siteflag = 'add';
    var pid = $('#category-list').attr('pid');
    $('.header-title').text('添加');
    var $positon = $(that).position();
    $('#siteid').val('');
    $('#siteid').attr('icon', '');
    $("#sitename").val('');
    $("#siteurl").val('');
    $('.editlinkdlg').css({
        left: $positon.left - 170,
        top: $positon.top + 30
    });
    bindSiteForCategory(pid, '');
}

//保存网址
function saveSite(that) {
    var l = Ladda.create(that);
    l.start();
    var clickcategoryid = $('.list-group-item-active>a').attr('categoryid');//当前被选中的分类
    var sitename = $('#sitename').val();
    var siteurl = $('#siteurl').val();
    var siteicon = $('#siteid').attr('icon');
    if ($.trim(sitename).length == 0 ||
        $.trim(siteurl).length == 0) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请输入完整.'
        });
        return;
    }
    if (!isURL(siteurl)) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '您输入的URL不合法，请重新输入.'
        });
        return;
    }
    if (siteurl.search("http") == -1) {
        siteurl = "http://" + siteurl;
    }

    var categoryid = $('#siteType').val();
    $.post('/json/saveSite.json', {
        id: $('#siteid').val(),
        name: sitename,
        url: siteurl,
        icon: siteicon,
        categoryId: categoryid,
        flag: siteflag
    }, function (data) {
        if (data != 0) {
            //如果当前被选中的分类等于已经添加网址的分类，就要重新显示
            if (clickcategoryid == categoryid) {
                //todo:再显示一下编辑按钮
                addSiteShowSite(categoryid);
            }
        }
        else {
            Lobibox.notify('info', {
                size: 'mini',
                title: 'awbeci提示',
                msg: '添加失败.'
            });
            return;
        }
    }, 'json')
    .always(function(){
        l.stop();
        canceleditLink();
    });
}

//编辑导航
function showEditCategoryBtn() {
    $('.header-title').text('编辑');
    if ($('.oction-category').hasClass('hide')) {
        $('.oction-category').removeClass('hide').addClass('show');
    }
    else {
        $('.oction-category').removeClass('show').addClass('hide');
    }
    canceleditNav();
}

function showEditSiteOction() {
    if ($('.linkedit').hasClass('hide')) {
        $('.linkedit').removeClass('hide').addClass('show');
    }
    else {
        $('.linkedit').removeClass('show').addClass('hide');
    }
    canceleditLink();
}

function editCategory(that) {
    categoryflag = 'update';
    $('.list-group-item a').removeClass('editCategoryName');
    $(that).addClass('editCategoryName');
    var $parent = $(that).parent();
    var $children = $parent.children('a');
    var positon = $parent.position();
    $('#categoryId').val($children.attr('categoryid'));
    $("#categoryName").val($.trim($children.text()));
    $('.editnavdlg').css({
        left: positon.left,
        top: positon.top + 30
    });
    $('.editnavdlg').show()
}

//删除分类
function delCategory(that, id, sitescount, categorycount) {
    if (sitescount > 0 || categorycount > 0) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请先删除子分类和网址，再删除此分类.'
        });
        return;
    }
    Lobibox.confirm({
        title: 'awbeci提示',
        msg: "您确定删除此分类？",
        buttons: {
            yes: {
                text: '确定'
            },
            no: {
                text: '取消'
            }
        },
        callback: function ($this, type, ev) {
            if (type === 'yes') {
                $.post('/json/deleteCategory.json', {
                    id: id
                }, function (data) {
                    if (data.success) {
                        $(that).parent().remove();
                    }
                    else {
                        Lobibox.notify('info', {
                            size: 'mini',
                            title: 'awbeci提示',
                            msg: data.msg
                        });
                    }
                }, 'json');
            }
        }
    });
}

//显示编辑网址对话框
function showEditSiteDlg(that) {
    var $findcategory = $('#category-list').find('.list-group-item-active');
    if ($findcategory.length == 0) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请先选择分类，再点击编辑.'
        });
        return;
    }
    siteflag = 'update';
    $('.header-title').text('编辑');
    var $positon = $(that).parent().position();
    $("#sitename").val($.trim($(that).prev().find('.siteName').text()));
    $("#siteurl").val($.trim($(that).prev().attr('href')));
    $('.editlinkdlg').css({
        left: $positon.left + 6,
        top: $positon.top + 39
    });
    var pid = $('#category-list').attr('pid');
    var categoryid = $(that).prev().attr('categoryid');
    bindSiteForCategory(pid, categoryid);
    $('#siteid').val($(that).prev().attr('id'));
    $('#siteid').attr('icon', $(that).prev().find('img').attr('src'));
}

//删除网址
function delSite(that) {
    var iconurl = $(that).prev().prev().find('img').attr('src');
    Lobibox.confirm({
        title: 'awbeci提示',
        msg: "您确定删除此网址？",
        buttons: {
            yes: {
                text: '确定'
            },
            no: {
                text: '取消'
            }
        },
        callback: function ($this, type, ev) {
            if (type === 'yes') {
                $.post('/json/deleteSite.json', {
                    id: $(that).prev().prev().attr('id'),
                    iconurl: iconurl
                }, function (data) {
                    if (data != 0) {
                        $(that).parent().remove();
                    }
                    else {
                        Lobibox.notify('info', {
                            size: 'mini',
                            title: 'awbeci提示',
                            msg: '删除失败.'
                        });
                        return;
                    }
                }, 'json');
            }
        }
    });
}

//取消编辑
function canceleditNav() {
    $('.editnavdlg').hide();
}

//取消编辑网址
function canceleditLink() {
    $('.editlinkdlg').removeClass('show');
}

function isURL(str_url) {
    var strRegex = /^((http|https|ftp):\/\/)?(\w(\:\w)?@)?([0-9a-z_-]+\.)*?([a-z0-9-]+\.[a-z]{2,6}(\.[a-z]{2})?(\:[0-9]{2,6})?)((\/[^?#<>\/\\*":]*)+(\?[^#]*)?(#.*)?)?$/i;
    if (strRegex.test(str_url)) {
        return true;
    } else {
        return false;
    }
}


//-------------------------------添加关注-----------------------------------
function follow(that, id, name) {
    var $that = $(that);
    if ($.trim($that.text()) == '关注') {
        $.post('/json/addFollow.json', {
            followId: id,
            followName: name
        }, function (data) {
            if (data != 0) {
                $that.empty().append('<span aria-hidden="true" class="octicon octicon-person commonOction"></span>取消关注')
            } else {
                location.href = "/login";
            }
        });

    }
    if ($.trim($that.text()) == '取消关注') {
        $.post('/json/removeFollow.json', {
            followid: id,
        }, function (data) {
            if (data != 0) {
                $that.empty().append('<span aria-hidden="true" class="octicon octicon-person commonOction"></span>关注')
            }
        });
    }
}
