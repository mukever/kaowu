/**
 * Created by format on 2016/1/23.
 */


var username = $.trim($(".header-right span").eq(0).text());    //取出用户名并做去空格处理
var returnval = 0;
$.ajax({
    type: "POST",
    url: "UserInfo.action",
    async: false,
    data:{"ID":username,"Type":1},
    dataType: "json",
    success: function(data){
        data = JSON.parse(data);
        returnval = data;
        if(data.count == 0){
            $(".audit span").addClass('.hidden');
            $(".audit").css('padding-left','0');
        }
        else{
            $(".audit span").text(data.count);
        }
    },
    error: function(jqXHR,textstatus,errorThrown){
        alert("服务器请求出错: " + textstatus);
    }
});

/*返回用户信息接口*/
function ReturnUserInfo(){
    return returnval;
}

$(document).ready(function(){
    $(".audit").click(function(){
       window.location.href = "UserAudit.jsp";
    });
    $(".class").click(function Class(){
        $(".class-flip").slideToggle(300);
        $("#btn-class").toggleClass("fa-rotate-180");
        if($(".test-flip").css('display') == 'block'){
            $(".test-flip").slideUp(300);
            $("#btn-test").toggleClass("fa-rotate-180");
        }
        if($(".room-flip").css('display') == 'block'){
            $(".room-flip").slideUp(300);
            $("#btn-room").toggleClass("fa-rotate-180");
        }
    });
    $(".test").click(function Test(){
        $(".test-flip").slideToggle(300);
        $("#btn-test").toggleClass("fa-rotate-180");
        if($(".class-flip").css('display') == 'block'){
            $(".class-flip").slideUp(300);
            $("#btn-class").toggleClass("fa-rotate-180");
        }
        if($(".room-flip").css('display') == 'block'){
            $(".room-flip").slideUp(300);
            $("#btn-room").toggleClass("fa-rotate-180");
        }
    });
    $(".room").click(function Room(){
        $(".room-flip").slideToggle(300);
        $("#btn-room").toggleClass("fa-rotate-180");
        if($(".class-flip").css('display') == 'block'){
            $(".class-flip").slideUp(300);
            $("#btn-class").toggleClass("fa-rotate-180");
        }
        if($(".test-flip").css('display') == 'block'){
            $(".test-flip").slideUp(300);
            $("#btn-test").toggleClass("fa-rotate-180");
        }
    });
    var width = screen.availWidth;
    $(".page-wrapper").width(width-220);
    if($(".audit span").text() == 0){
        $(".audit span").addClass('hidden');
        $(".audit").css('padding-left','0');
    }
});
