var categoryflag = '';
var siteflag = '';
//点击分类的时候(注意是子分类)
var clickCategoryId = '';

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)return unescape(r[2]);
    return null;
}

$(function () {
    $("[data-toggle='tooltip']").tooltip({html: true});
    //$("#showlink ul").dragsort({});
    //todo:initCategory();
    editCategorySite();
    addcategory();
    addSite();
    $('#categoryClose').on('click', function () {
        canceleditNav();
    });
    $('#siteClose').on('click', function () {
        canceleditLink();
    });
    querySite();
    var name = GetQueryString('domainName');
    initQuerySite(name);
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
        html += data[i].name;
        html += ' </img>';
        html += '</a>'
        html += '<span class="linkedit linkediticon octicon octicon-pencil"></span>';
        html += '<span class="linkedit linkdelicon octicon octicon-x"></span></div>';
        html += ' </li>';
    }
    $('#showmysite').append(html);
    $('.linkedit').addClass('hide');
    editDelSite();
}

//初始化分类
function initCategory() {
    $('.sidebar-module').empty();
    $.post('/json/getCategoryByUid.json', {
        depth: 1
    }, function (data) {
        var html = '<ul>';
        for (var i = 0; i < data.length; i++) {
            if (data[i].pid == null || data[i].pid == '') {
                html += '<li class="js-expand-btn">';
                html += '<h3>';
                html += '<a id="' + data[i].id + '" href="#" class="categoryParent">';
                html += '<span class="octicon octicon-chevron-right arrow-btn" aria-hidden="true"></span>';
                html += data[i].name;
                html += '</a>';
                html += '<span class="navedit navediticon octicon octicon-pencil" ></span>';
                html += '<span class="navedit navdelicon octicon octicon-x"></span>';
                html += '</h3>';
                html += '<ul class="js-guides">';

                for (var j = 0; j < data.length; j++) {
                    if (data[j].pid == data[i].id) {
                        html += '<li class="list-item">';
                        html += '<a class="categoryChild" href="javascript:void(0)" id="' + data[j].id + '" pid="' + data[j].pid + '"><span class="octicon octicon-repo typeoction"></span>' + data[j].name + '</a>';
                        html += '<span class="navedit navediticon octicon octicon-pencil"></span>';
                        html += '<span class="navedit navdelicon octicon octicon-x"></span>';
                        html += '</li>';
                    }
                }

                html += '</ul>';
                html += '</li>';
            }
        }
        html += '</ul>';
        $('.sidebar-module').append($(html));
        $('.navedit').addClass('hide');

        //this funciton in github.js
        setSiteBar();
        editDelCategory();
        categoryChildClick();
    }, 'json');
}

//分类子节点单击事件
function categoryChildClick() {
    $('.categoryChild').on('click', function () {
        clickCategoryId = $(this).attr('id');
        clickCategoryShowSite();
    });
}

//点击分类的时候显示网址列表
function clickCategoryShowSite() {
    $.post('/json/getSiteByCategoryId.json', {
        categoryId: clickCategoryId
    }, function (data) {
        showSite(data);
    }, 'json');
}

//绑定类型
function bindCategories(id) {
    $('#categoryType').empty();
    //$.ajaxSettings.async = false;
    $.post('/json/getCategoryParent.json', function (data) {
        var html = '<option value="">主分类</option>';
        for (var i = 0; i < data.length; i++) {
            if (data[i].id != id) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
            }
        }
        $('#categoryType').append(html);
        $('#categoryType').selectpicker('refresh');
        $('.editnavdlg').addClass('show')
    }, 'json');
}

//绑定网址
function bindSite(bindid) {
    $('#siteType').empty();
    //$.ajaxSettings.async = false;
    $.post('/json/getCategoryChild.json', function (data) {
        var html = '';
        var flag = false;
        for (var i = 0; i < data.parents.length; i++) {
            html += '<optgroup label="' + data.parents[i].name + '">'
            for (var j = 0; j < data.childs.length; j++) {
                if (data.childs[j].pid == data.parents[i].id) {
                    flag = true;
                    if (data.childs[j].id == bindid) {
                        html += '<option value="' + data.childs[j].id + '" selected="selected">' + data.childs[j].name + '</option>';
                    }
                    else {
                        html += '<option value="' + data.childs[j].id + '">' + data.childs[j].name + '</option>';
                    }
                }
            }
            html += '</optgroup>';
        }
        if (!flag) {
            Lobibox.notify('info', {
                size: 'mini',
                title: 'awbeci提示',
                msg: '请先在主分类下面添加子分类再添加网址.'
            });
            return;
        }
        else {
            $('#siteType').append(html);
            $('#siteType').selectpicker('refresh');
            $('.editlinkdlg').addClass('show');
        }
    }, 'json');
}

//添加分类
function addcategory() {
    $('#addcategory').on('click', function () {
        categoryflag = 'add';
        $('.header-title').text('添加');
        var positon = $(this).parent().parent().position();
        $("#categoryName").val('');
        $('#categoryId').val('');
        $('.editnavdlg').css({
            left: '1px',
            top: positon.top + 29
        });
        bindCategories();
    });
}

//保存分类
function saveCategory() {
    var categoryname = $('#categoryName').val();
    var category = $('#categoryType').val();
    if ($.trim(categoryname).length == 0) {
        Lobibox.notify('info', {
            size: 'mini',
            title: 'awbeci提示',
            msg: '请输入完整.'
        });
        return;
    }
    //todo:注意：添加的时候不能允许名称重复
    $.post('/json/saveCategory.json', {
        id: $('#categoryId').val(),
        name: categoryname,
        pid: category,
        flag: categoryflag
    }, function (data) {
        if (data != 0) {
            canceleditNav();
            initCategory();
        }
        else {
            Lobibox.notify('info', {
                size: 'mini',
                title: 'awbeci提示',
                msg: '添加失败.'
            });
            return;
        }
    }, 'json');
}

//添加网址
function addSite() {
    $('#addsite').on('click', function (event, data) {
        siteflag = 'add';
        $('.header-title').text('添加');
        var $positon = $(this).position();
        $('#siteid').val('');
        $('#siteid').attr('icon', '');
        $("#sitename").val('');
        $("#siteurl").val('');
        $('.editlinkdlg').css({
            left: $positon.left - 221,
            top: $positon.top + 25
        });
        bindSite('');
    })
}

//保存网址
function saveSite() {
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
            canceleditLink();
            if (clickCategoryId == null || clickCategoryId == '') {
                initSite();
            }
            else {
                clickCategoryShowSite();
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
    }, 'json');
}

//编辑导航
function editCategorySite() {
    $("#editcategory").on('click', function (e) {
        $('.header-title').text('编辑');
        if ($('.navedit').hasClass('hide')) {
            $('.navedit').removeClass('hide').addClass('show');
        }
        else {
            $('.navedit').removeClass('show').addClass('hide');
        }
        canceleditNav();
    });

    $("#editsite").on('click', function (e) {
        if ($('.linkedit').hasClass('hide')) {
            $('.linkedit').removeClass('hide').addClass('show');
        }
        else {
            $('.linkedit').removeClass('show').addClass('hide');
        }
        canceleditLink();
    });
}

//编辑删除分类放到一起
function editDelCategory() {
    $('.navediticon').on('click', function (event) {
        categoryflag = 'update';
        bindCategories($(this).parent().children('a').attr('id'));
        var positon = $(this).parent().position();
        $('#categoryId').val($(this).parent().children('a').attr('id'));
        $("#categoryName").val($.trim($(this).parent().children('a').text()));
        $('#categoryType').selectpicker('val', $(this).parent().children('a').attr('pid'));
        $('.editnavdlg').css({
            left: positon.left,
            top: positon.top + 35
        });
        event.stopPropagation();
    });

    $('.navdelicon').on('click', function (event) {
        var $that = $(this);
        event.stopPropagation();
        var parents = $(this).parent().parent().find('.list-item');
        var parent = $(this).parent();
        if (parents.length > 0 && !parent.hasClass('list-item')) {
            Lobibox.notify('info', {
                size: 'mini',
                title: 'awbeci提示',
                msg: '该分类下存在子分类，请先删除子分类，再删除此分类.'
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
                    var id = $that.parent().children('a').attr('id');
                    $.post('/json/deleteCategory.json', {
                        id: id
                    }, function (data) {
                        if (data.success) {
                            initCategory();
                            Lobibox.notify('info', {
                                size: 'mini',
                                title: 'awbeci提示',
                                msg: data.msg
                            });
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
    });
}

/*编辑删除网址放到一起*/
function editDelSite() {
    $('.linkediticon').on('click', function (event, data) {
        siteflag = 'update';
        $('.header-title').text('编辑');
        var $positon = $(this).parent().position();
        $("#sitename").val($.trim($(this).parent().children('a').text()));
        $("#siteurl").val($.trim($(this).parent().children('a').attr('href')));
        $('.editlinkdlg').css({
            left: $positon.left + 6,
            top: $positon.top + 39
        });
        var bindid = $(this).parent().children('a').attr('categoryid');
        bindSite(bindid);
        $('#siteid').val($(this).parent().children('a').attr('id'));
        $('#siteid').attr('icon', $(this).parent().children('a').children('img').attr('src'));
        $('#siteType').selectpicker('val', bindid);
    });

    $('.linkdelicon').on('click', function (event, data) {
        var $that = $(this);
        var iconurl = $(this).parent().children('a').children('img').attr('src');
        if (iconurl.search('6000.png') != -1) {
            iconurl = '';
        }

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
                        id: $that.parent().children('a').attr('id'),
                        iconurl: iconurl
                    }, function (data) {
                        if (data != 0) {
                            if (clickCategoryId == null || clickCategoryId == '') {
                                initSite();
                            }
                            else {
                                clickCategoryShowSite();
                            }
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
    });
}

//取消编辑
function canceleditNav() {
    $('.editnavdlg').removeClass('show');
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
