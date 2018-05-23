"use strict";
const rootOrigin = "http://localhost:8080";
(function ($) {
    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog modal-sm">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<p>[Message]</p>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';


        var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        }
        var init = function (options) {
            options = $.extend({}, {
                title: "操作提示",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).modal({
                width: options.width,
                backdrop: 'static'
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        }

        return {
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();

                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {
                                callback(true);
                            });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () {
                                callback(true);
                            });
                            modal.find('.cancel').click(function () {
                                callback(false);
                            });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            dialog: function (options) {
                options = $.extend({}, {
                    title: 'title',
                    url: '',
                    width: 800,
                    height: 550,
                    onReady: function () {
                    },
                    onShown: function (e) {
                    }
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                target.find('.modal-body').load(options.url);
                if (options.onReady())
                    options.onReady.call(target);
                target.modal();
                target.on('shown.bs.modal', function (e) {
                    if (options.onReady(e))
                        options.onReady.call(target, e);
                });
                target.on('hide.bs.modal', function (e) {
                    $('body').find(target).remove();
                });
            }
        }
    }();
    (function () {
        if (typeof $.cookie("userId") == 'undefined') {
            alert("您尚未登录,请登录");
            window.location.href = "/login.html";
        }
    })()
})(jQuery);

function dus(s) {
    let arr = [];
    var str = '';
    str = s.slice(0, s.lastIndexOf());
    arr = str.split('');
    arr.reverse();
    arr.splice(0, 0, '\"');
    arr.splice(3, 0, '.');
    arr.splice(6, 1, '\'');
    arr.splice(9, 0, '°');
    arr.reverse();
//-----------------------------------------------
    str = arr.join('');
//------------------------------------------------
    return str;
}

function arrays(s) {
    let arr = [];
    let newstr = '';
    var str = '';
    str = s.slice(0, s.lastIndexOf());
    arr = str.split('');
    arr.reverse();
    arr.splice(0, 0, '\"');
    arr.splice(3, 0, '.');
    arr.splice(6, 1, '\'');
    arr.splice(9, 0, '°');
    arr.reverse();
    str = arr.join('');
//------------------------------------------------
    arr = str.split(/[°\'\"]/);
    newstr = parseInt(arr[0]) + parseInt(arr[1]) / 60 + parseFloat(arr[2]) / 3600;
    return newstr;
}


function setPage(opt) {
    var pageHTML = "<span class='total'>共" + opt.total + "条" + opt.pages + "页</span>" +
        "<ul class='pagination'><li><a  itemid='" + opt.firstPage + "'>首页</a></li>";
    if (opt.hasPreviousPage) {
        pageHTML += "<li><a itemid='" + (parseInt(opt.pageNum) - 1) + "'>&laquo;</a></li>"
    } else {
        pageHTML += "<li class='disabled'><a>&laquo;</a></li>"
    }
    pageHTML += "<li class='active'><a id='table-cur-page'>" + opt.pageNum + "</a></li>";
    if (opt.hasNextPage) {
        pageHTML += "<li><a itemid='" + (parseInt(opt.pageNum) + 1) + "'>&raquo;</a></li>"
    } else {
        pageHTML += "<li class='disabled'><a>&raquo;</a></li>"
    }
    pageHTML += "<li><a itemid='" + opt.pages + "'>尾页</a></li></ul>";
    pageHTML += "<span>跳转到 <input type='text' class='input'> 页 <button class='btn bg-primary'>Go</button></span>";
    return pageHTML;
}

function insertUserList(data) {
    var userHTml = "<li class='active'><a href='javascript:void(0)' item='all'>所有用户</a></li>";
    for (var i = 0; i < data.length; i++) {
        userHTml += "<li><a title='" + data[i].unit_name + "' href='javascript:void(0)' item='one' data-item='" + data[i].index + "'>" + data[i].unit_name + "</a></li>"
    }
    $("#user-list").html(userHTml);
}

function getUsers() {
    $.getJSON(rootOrigin + "/userinfo/getListNoPage", function (data) {
        insertUserList(data)
    });
}

function emptyData() {
    return "<div><b></b></div>"
}