"use strict";

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
        userHTml += "<li><a title='" + data[i].unitName + "' href='javascript:void(0)' item='one' data-item='" + data[i].unitName + "'>" + data[i].unitName + "</a></li>"
    }
    $("#user-list").html(userHTml);
}

function getUsers() {
    $.getJSON("/userinfo/getListNoPage", function (data) {
        insertUserList(data)
    });
}

function emptyData() {
    return "<div><b></b></div>"
}