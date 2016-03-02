/**
 * Created by format on 2016/2/28.
 */

var GRADEID;
var GRADENAME;
var GRADENUM;
/*发送班级数据，添加班级*/
function PostGradeInfo(id,name,num){
    var status = false;
    $.ajax({
        type: "POST",
        url: "Grade_add.action",
        async: false,
        data: {"Gradeid":id,"Gradename":name,"Gradenum":num},
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
    GRADEID = $("#grade-id").val();
    GRADENAME = $("#grade-name").val();
    GRADENUM = parseInt($("#grade-num").val());
    if(GRADEID == ""){
        SetError(1);
        status = false;
    }
    else{
        SetSuccess(1);
    }
    if(GRADENAME == ""){
        SetError(2);
        status = false;
    }
    else{
        SetSuccess(2);
    }
    if(isNaN(GRADENUM)){
        SetError(3);
        status = false;
    }
    else{
        SetSuccess(3);
    }
    return status;
}
$(".btn-post").click(function(){
    if(CheckForm() == false){
        return;
    }
    if(PostGradeInfo(GRADEID,GRADENAME,GRADENUM) == false){
        return;
    }
    ShowSuccess();
});
function ShowSuccess(){
    $(".input").addClass('hidden');
    $(".success").removeAttr('class');
    $('code').eq(0).text(GRADEID);
    $('code').eq(1).text(GRADENAME);
    $('code').eq(2).text(GRADENUM);
}