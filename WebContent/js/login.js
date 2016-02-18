/**
 * Created by format on 2015/11/17.
 * login流程js
 */
/*定义error样式函数*/
function SetError(string){
    if(string == "name" || string == "both"){
        $("#control-username").addClass("has-error");
        $("#login-name").addClass("border-error");
        $(".fui-user").addClass("content-error");
    }
    if(string == "key" || string == "both"){
        $("#control-password").addClass("has-error");
        $("#login-pass").addClass("border-error");
        $(".fui-lock").addClass("content-error");
    }
}
/*判断用户名密码是否为空，添加error样式*/
function UserName(){
    if($("#login-name").val() === ""){
        SetError("name");
        return false;
    }
    if($("#login-pass").val() === ""){
        SetError("key");
        return false;
    }
    return true;
}
/*输入框获得焦点后，移除error样式*/
$("#login-name").focus(function(){
    $("#control-username").removeClass("has-error");
    $("#login-name").removeClass("border-error");
    $(".fui-user").removeClass("content-error");
});
$("#login-pass").focus(function(){
    $("#control-password").removeClass("has-error");
    $("#login-pass").removeClass("border-error");
    $(".fui-lock").removeClass("content-error");
});
/*双击自动填充用户名input*/
$(function(){
   $("#login-name").dblclick(function(){
      $("#login-name").val($.cookie('Username'));
   });
});
/*点击login按钮后触发的事件*/
$("#sub-btn").click(function(){
    if(UserName() === true){    //如果用户名密码不为空
        //step1.发送数据
        var username = $("#login-name").val();
        var password = $("#login-pass").val();
        var sessionid = $.cookie('Sessionid');  //获取sessionid
        if(typeof (sessionid) == "undefined"){  //如果没有，则设false
            sessionid = false;
        }
        var serverstatus = true;
        var returnval = 0;
        $.ajax({
            type: "POST",
            url: "Login.action",
            async: false,   //同步请求
            data: {"Username": username, "Password": password, "Sessionid": sessionid},
            dataType: "json",
            success: function (data) {
                returnval = JSON.parse(data);   //字符创转换成json对象
            },
            //请求出错的处理
            error: function(jqXHR,textStatus,errorThrown){
                serverstatus = false;
                alert("服务器请求出错: " + textStatus);
            }
        });
        if(serverstatus == false){
            return;
        }
        //step2.判断用户是否合法
        if(returnval.type == 0 || returnval == 0){
            SetError("both");
            return;
        }
        //step3.判断用户是否选择记住我
        if($('div').hasClass("bootstrap-switch-on")){
            //step4.选择记住我，设置cookies,7天
            $.cookie('Sessionid',returnval.sessionid,{expires:7});
            $.cookie('Username',username,{expires:7});
        }
        else{
            $.removeCookie('Sessionid');
            $.removeCookie('Username');
        }
        //step5.跳转页面
        if(returnval.type == 1){
            window.location.href = "index.html";
        }
        else if(returnval.type == -1){
            window.location.href = "error.html";
        }
        else{
            window.location.href = "";
        }
    }
});
