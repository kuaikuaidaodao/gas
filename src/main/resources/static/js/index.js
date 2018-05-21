"use strict";

function setPage(opt, role) {
    var pageHTML = "";
    pageHTML += "<span class='total'>共" + opt.total + "条" + opt.pages + "页</span>";
    if (role === "user") {
        pageHTML += "<span>第<b id='user-cur-page'>" + opt.pageNum + "</b>页</span>";
        pageHTML += "<span>跳到<input type='text' class='input'>页<button class='btn btn-sm btn-primary'>Go</button></span>";
    } else {
        pageHTML += "<ul class='pagination'><li><a  itemid='" + opt.firstPage + "'>首页</a></li>";
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
    }
    return pageHTML;
}

function insertUserList(data) {
    var list = data.list;
    var userHTml = "<li class='active'><a href='javascript:void(0)'>所有用户</a></li>";
    for (var i = 0; i < list.length; i++) {
        userHTml += "<li><a title='" + list[i].unitName + "' href='javascript:void(0)' data-item='" + list[i].unitName + "'>" + list[i].unitName + "</a></li>"
    }
    $("#user-list").html(userHTml);
    // $("#user-page").html(setPage(data, "user"))
}
function getUsers(pageNo) {
    $.getJSON("/userinfo/getList", {"pageNo":pageNo}, function (data) {
        insertUserList(data)
    });
}
function emptyData() {
    return "<div><b></b></div>"
}