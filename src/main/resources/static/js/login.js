"use strict";
$(function () {
    $("#login").click(function () {
        var user = $("#userName").val();
        var password = $("#password").val();
        $.getJSON("/userinfo/login",{"userName":user,"password":password},function (data) {
            if(data.status==="success"){
                $.cookie("userinfo",data.msg);
                window.location.href="/index.html";
            }else {
                $("#login-tip").html(data)
            }
        })
    })
});