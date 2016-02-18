/**
 * Created by format on 2016/2/8.
 */



/**
 * Register.action数据格式
 * POST 方式提交数据
 * 数据格式：JSON
 * data{"Username":username,"Password":password,"ID":id,"Age":age,"Wechat":wechat,"College":number};
 * ******* Wechat(微信号)没有的时候传递false ******* College(学院编号)[1：计算机，2：电子信息工程学院......]  >>按考务的那个排号
 * 返回值：两种状态[成功 || 错误（用户名存在......）]
 * **/

var username;
var password;
var id;
var age;
var wechat;
var college;
var returnval;
var serverstatus = true;
 /*修改按钮*/
function ChangeBtn(){
   if($(".bar li").eq(2).hasClass("active-last")){
      $("#next").addClass("hidden");
      $("#close").removeClass("hidden").click(function(){
         window.close();
      });
   }
   else{
      $("#next").removeClass("hidden");
      $("#close").addClass("hidden");
   }
}
/*发送数据*/
function SendData(){
   $.ajax({
      type: "POST",
      url: "Register.action",
      async: false,
      data: {"Username": username,"Password": password,"ID": id,"Age": age,"Wechat": wechat,"College": college},
      dataType: "json",
      success: function(data){
         returnval = JSON.parse(data);
      },
      error: function(jqXHR,textStatus,errorThrown) {
         serverstatus = false;
         alert("服务器请求出错: " + textStatus);
      }
   });
}
/*点击下一步触发的事件*/
$("#next").click(function(){
   for(var i = 0;i < 2;i++){
      if($(".bar li").eq(i).hasClass("active")){
         continue;
      }
      else{
         if(CheckForm(i) == false){
            return;
         }
         if(i == 1){
            SendData();
            if(serverstatus == false){
               return;
            }
            if(returnval.type == 0){
               alert("用户已经存在");
               return;
            }
            $(".bar li").eq(2).addClass("active-last");
         }
         $("#prior").removeClass("btn-default").addClass("btn-inverse");
         $(".bar li").eq(i).addClass("active");
         $("form fieldset").eq(i).addClass("hidden");
         $("form fieldset").eq(i+1).removeAttr("class").addClass("show");
         ChangeBtn();
         return;
      }
   }
});
/*点击上一步触发的事件*/
$("#prior").click(function(){
   for(var i = 1;i >= 0;i--){
      if($(".bar li").eq(i).hasClass("active")){
         $(".bar li").eq(i+1).removeAttr("class");
         $(".bar li").eq(i).removeClass("active").addClass("actived");
         $("form fieldset").eq(i+1).addClass("hidden");
         $("form fieldset").eq(i).removeAttr("class").addClass("show");
         if(i == 0){
            $("#prior").removeClass("btn-inverse").addClass("btn-default");
         }
         ChangeBtn();
         return;
      }
   }
});
/*获得焦点后移除error样式*/
$("#login-name").focus(function(){
   $("#one div").eq(0).removeClass("has-error");
});
$("#login-pass").focus(function(){
   $("#one div").eq(1).removeClass("has-error");
});
$("#login-id").focus(function(){
   $("#one div").eq(2).removeClass("has-error");
});
$("#login-age").focus(function(){
   $("#one div").eq(3).removeClass("has-error");
});
$("#login-wechat").focus(function(){
   $("#two div").eq(0).removeClass("has-error");
});
/*表单验证*/
function CheckForm(i){
   var success = true;
   if(i == 0){
      username = $("#login-name").val();
      password = $("#login-pass").val();
      id = $("#login-id").val();
      age = $("#login-age").val();
      if(username == "" || username == " "){
         $("#one div").eq(0).addClass("has-error");
         success = false;
      }
      if(password == "" || password.length < 6){
         $("#one div").eq(1).addClass("has-error");
         success = false;
      }
      if(id == "" || id == " "){
         $("#one div").eq(2).addClass("has-error");
         success = false;
      }
      if(age == "" || id == " " || age < 18 || age > 70){
         $("#one div").eq(3).addClass("has-error");
         success = false;
      }
      return success;
   }
   if(i == 1){
      wechat =$("#login-wechat").val();
      if(wechat == " "){
         $("#two div").eq(0).addClass("has-error");
         wechat = false;
         success = false;
      }
      if(wechat == ""){
         wechat = false;
      }
      college = GetCollege();
      if(college == ""){
         $(".option").addClass("error");
         success = false;
      }
      return success;
   }
}
/*单击设置学院class*/
$(".all-college li a").click(function(){
   for(var i = 0;i < 14;i++){
      if($(".all-college li").eq(i).val() != $(this).parent().val() && $(".all-college li").eq(i).hasClass("optioned")){
         $(".all-college li").eq(i).removeAttr('class');
      }
   }
   $(this).parent().addClass("optioned");
   $(".option").text($(this).text());
});
/*获取学院信息*/
function GetCollege(){
   for(var i = 0;i < 14;i++){
      if($(".all-college li").eq(i).hasClass("optioned")){
         return $(".all-college li").eq(i).val();
      }
   }
   return false;
}
/*控制下拉菜单样式函数组*/
//$(".college-chosen").hover(function(){
//   $(".fa-caret-down").addClass("color-normal");
//},function(){
//   $(".fa-caret-down").removeClass("color-normal");
//});

$(".college-chosen").mousedown(function(){
   $(".college-chosen").addClass("color-dark");
   $(".fa-caret-down").addClass("color-normal");
});
$(".college-chosen").mouseup(function(){
   $(".college-chosen").removeClass("color-dark");
   $(".fa-caret-down").removeClass("color-normal");
   $(".fa-caret-down").removeClass("color-dark");
});
$(".college-chosen").click(function(){
   $(".option").removeClass("error");
   $(".college-chosen").addClass("color-normal");
   $(".fa-caret-down").addClass("color-dark");
   if($(".college-group").hasClass("hidden")){
      $(".college-group").removeClass("hidden").addClass("college-group-show");
   }
   else if($(".college-group").hasClass("college-group-hidden")){
      $(".college-group").removeClass("college-group-hidden").addClass("college-group-show");
   }
   else{
      $(".college-group").removeClass("college-group-show").addClass("college-group-hidden");
      $(".college-chosen").removeClass("color-normal");
      $(".fa-caret-down").removeClass("color-dark");
      setTimeout(function(){
         $(".college-group").removeClass("college-group-hidden").addClass("hidden");
      },500);
   }
});
/*判断点击区域，隐藏college-group div*/
$(document).click(function(e){
   e = window.event || e;
   obj = $(e.srcElement || e.target);
   if(obj.attr('id') == "college-chosen" || obj.attr('id') == "icon-down" || obj.attr('id') == "option"){
      return;
   }
   else{
      $(".college-chosen").removeClass("color-normal");
      $(".fa-caret-down").removeClass("color-dark");
      if($(".college-group").hasClass("college-group-show")){
         $(".college-group").removeClass("college-group-show").addClass("college-group-hidden");
         setTimeout(function(){
            $(".college-group").removeClass("college-group-hidden").addClass("hidden");
         },500);
      }
   }
});