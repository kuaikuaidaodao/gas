"use strict";
$(function () {
    $("#login").click(function () {
        var user = $("#userName").val();
        var password = $("#password").val();
        $.getJSON("http://47.96.154.234/userinfo/login",{"userName":user,"password":password},function (data) {
            if(data.status==="success"){
                $.cookie("userId",data.msg.index);
                window.location.href="/index.html";
            }else {
                $("#login-tip").html(data)
            }
        })
    })
});