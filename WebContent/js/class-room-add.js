/**
 * Created by format on 2016/2/28.
 */


var CLASSROOMID;
var CLASSROOMNUM;
var CLASSROOMFATHER;
/*发送教室数据，添加教室*/
function PostClassRoomInfo(id,num,where){
    var status = false;
    $.ajax({
        type: "POST",
        url: "Classroom_add.action",
        async: false,
        data: {"Classroomid":id,"Classroomnum":num,"Classroomwhere":where},
        dataType: "json",
        success: function (data) {
            //bool
            status = JSON.parse(data).type;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("服务器请求出错： " + textStatus);
        }
    });
    return status;
}
$(".btn-default").click(function(){
   if($(".btn-group").hasClass('open')){
       $(".btn-group").removeClass('open');
   }
   else{
       $(".btn-group").addClass('open');
   }
});
$(".dropdown-menu li a").click(function(){
    $(this).parent().addClass('active').siblings().removeAttr('class');
   $(".option").text($(this).text());
   $(".btn-group").removeClass('open');
    if($(".option").text() == '其他'){
        $(".detail").removeClass('hidden');
    }
    else{
        $(".detail").addClass('hidden');
    }
});
$(document).click(function(e){
    e = window.event || e;
    obj = $(e.srcElement || e.target);
    if(obj.attr('id') == "hide-one" || obj.attr('id') == 'hide-two'){
        return;
    }
    else{
        $(".btn-group").removeClass('open');
    }
});
/*添加错误或者success样式*/
function SetError(i){
    if($(".input-group").eq(i-1).hasClass('has-success')){
        $(".input-group").eq(i-1).children('span').eq(1).remove();
        $(".input-group").eq(i-1).removeClass('has-success').addClass('has-error').append('<span class="form-control-feedback"><i class="fa fa-fw fa-close"></i></span>');
    }
    else{
        $(".input-group").eq(i-1).children('span').eq(1).remove();
        $(".input-group").eq(i-1).addClass('has-error').append('<span class="form-control-feedback"><i class="fa fa-fw fa-close"></i></span>');
    }
}
function SetSuccess(i){
    if($(".input-group").eq(i-1).hasClass('has-error')){
        $(".input-group").eq(i-1).children('span').eq(1).remove();
        $(".input-group").eq(i-1).removeClass('has-error').addClass('has-success').append('<span class="form-control-feedback"><i class="fa fa-fw fa-check"></i></span>');
    }
    else{
        $(".input-group").eq(i-1).children('span').eq(1).remove();
        $(".input-group").eq(i-1).addClass('has-success').append('<span class="form-control-feedback"><i class="fa fa-fw fa-check"></i></span>');
    }
}
/*恢复原始样式*/
$("input").click(function(){
   $(this).parent().removeClass('has-error').removeClass('has-success');
    $(this).parent().children('span').eq(1).remove();
    $(this).parent().append('<span class="form-control-feedback"><i class="fa fa-fw fa-asterisk" style="font-size: small"></i></span>');
});
/*验证*/
function CheckForm(){
    var status = true;
    CLASSROOMID = $("#class-id").val();
    CLASSROOMNUM = $("#class-num").val();
    if($(".option").text() == '其他'){
        CLASSROOMFATHER = $("#class-father").val();
    }
    else{
        CLASSROOMFATHER = $(".option").text();
    }
    if(CLASSROOMID == ""){
        SetError(1);
        status = false;
    }
    else{
        SetSuccess(1);
    }
    if(CLASSROOMNUM == ""){
        SetError(2);
        status = false;
    }
    else{
        SetSuccess(2);
    }
    if(CLASSROOMFATHER == "" || CLASSROOMFATHER != 'A' && CLASSROOMFATHER != 'B' && CLASSROOMFATHER != 'C'){
        SetError(4);
        status = false;
    }
    else{
        SetSuccess(4);
    }
    return status;
}
$(".btn-post").click(function(){
   if(CheckForm() == false){
       return;
   }
    if(PostClassRoomInfo(CLASSROOMID,CLASSROOMNUM,CLASSROOMFATHER) == false){
        return;
    }
    ShowSuccess();
});
function ShowSuccess(){
    $(".input").addClass('hidden');
    $(".success").removeAttr('class');
    $('code').eq(0).text(CLASSROOMID);
    $('code').eq(1).text(CLASSROOMNUM);
    $('code').eq(2).text(CLASSROOMFATHER);
}