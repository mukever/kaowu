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
var position;
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
      data: {"Username": username,"Password": password,"ID": id,"Age": age,"Wechat": wechat,"College": college,"Position":position},
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
   $("#one div").eq(1).removeClass("has-error").removeClass('can-not-pass');
});
$("#login-pass").focus(function(){
   $("#one div").eq(2).removeClass("has-error").removeClass('pass-wrong');
});
$("#login-pass2").focus(function(){
   $("#one div").eq(3).removeClass("has-error").removeClass('pass-diff');
});
$("#login-id").focus(function(){
   $("#one div").eq(0).removeClass("has-error").removeClass('can-not-pass');
});
$("#login-age").focus(function(){
   $("#one div").eq(4).removeClass("has-error").removeClass('age-wrong');
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
      var password2 = $("#login-pass2").val();
      id = $("#login-id").val();
      age = $("#login-age").val();
      if(id == "" || id == " "){
         $("#one div").eq(0).addClass("has-error").addClass('can-not-pass');
         success = false;
      }
      if(username == "" || username == " "){
         $("#one div").eq(1).addClass("has-error").addClass('can-not-pass');
         success = false;
      }
      if(password == "" || password.length < 6){
         $("#one div").eq(2).addClass("has-error").addClass('pass-wrong');
         success = false;
      }
      if(password2 != password || password2 == ""){
         $("#one div").eq(3).addClass("has-error").addClass('pass-diff');
         success = false;
      }
      if(age == "" || id == " " || age < 18 || age > 70){
         $("#one div").eq(4).addClass("has-error").addClass('age-wrong');
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
      position = GetPosition();
      if(position == ""){
         $(".option-position").addClass('error');
         return false;
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

/*单击设置position class*/
$(".all-position li a").click(function(){
   for(var i = 0;i < 14;i++){
      if($(".all-position li").eq(i).val() != $(this).parent().val() && $(".all-position li").eq(i).hasClass("optioned")){
         $(".all-position li").eq(i).removeAttr('class');
      }
   }
   $(this).parent().addClass("optioned");
   $(".option-position").text($(this).text());
});
/*获取position信息*/
function GetPosition(){
   for(var i = 0;i < 14;i++){
      if($(".all-position li").eq(i).hasClass("optioned")){
         return $(".all-position li").eq(i).val();
      }
   }
   return false;
}
/*控制下拉菜单样式函数组*/
/*---------------------------------------学院div------------------------------------*/
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
/*----------------------------------------position div------------------------------------*/
$(".position-chosen").mousedown(function(){
   $(".position-chosen").addClass("color-dark");
   $(".fa-caret-down").addClass("color-normal");
});
$(".position-chosen").mouseup(function(){
   $(".position-chosen").removeClass("color-dark");
   $(".fa-caret-down").removeClass("color-normal");
   $(".fa-caret-down").removeClass("color-dark");
});
$(".position-chosen").click(function(){
   $(".option-position").removeClass("error");
   $(".position-chosen").addClass("color-normal");
   $(".fa-caret-down").addClass("color-dark");
   if($(".position-group").hasClass("hidden")){
      $(".position-group").removeClass("hidden").addClass("position-group-show");
   }
   else if($(".position-group").hasClass("position-group-hidden")){
      $(".position-group").removeClass("position-group-hidden").addClass("position-group-show");
   }
   else{
      $(".position-group").removeClass("position-group-show").addClass("position-group-hidden");
      $(".position-chosen").removeClass("color-normal");
      $(".fa-caret-down").removeClass("color-dark");
      setTimeout(function(){
         $(".position-group").removeClass("position-group-hidden").addClass("hidden");
      },500);
   }
});
/*---------------------------------------------隐藏position-group div------------------------------------*/
$(document).click(function(e){
   e = window.event || e;
   obj = $(e.srcElement || e.target);
   if(obj.attr('id') == "position-chosen" || obj.attr('id') == "position-icon-down" || obj.attr('id') == "option-position"){
      return;
   }
   else{
      $(".position-chosen").removeClass("color-normal");
      $(".fa-caret-down").removeClass("color-dark");
      if($(".position-group").hasClass("position-group-show")){
         $(".position-group").removeClass("position-group-show").addClass("position-group-hidden");
         setTimeout(function(){
            $(".position-group").removeClass("position-group-hidden").addClass("hidden");
         },500);
      }
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