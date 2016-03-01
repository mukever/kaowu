/**
 * Created by format on 2016/2/27.
 */


/*--------------向后台发送修改教室信息请求-------------------*/
function PostClassInfo(count,where,type){
    var returnval = 0;
    $.ajax({
       type: "POST",
        url: "",
        async: false,
        data: {"Classroom_count":count,"Classroom_where":where,"What":type},
        dataType: "json",
        success: function (data) {
             returnval = JSON.parse(data).type;
        },
        error: function(jqHXR,textStatus,errorThrown){
            alert("服务器请求出错： "+textStatus);
        }
    });
    return returnval;
}
/*------------表格分组标题------------*/
$(".class-room-navbar li").click(function(){
    for(var i = 0;i < 4;i++){
        $(".class-room-navbar li").eq(i).removeAttr('class');
        $("tbody").addClass('hidden');
    }
   $(this).addClass('where-active');
    var $temp = $(this);
    $(".table-title span:nth-child(2)").text($temp.text()+"教学楼");
    $("tbody").eq($temp.index()).removeClass('hidden');
});
/*-------------确认步骤---------------*/
/*修改*/
$("tbody").on("click",".btn-change",function(){
    $(".hide-screen").removeClass('hidden').addClass('hide-screen-finish');
    $(".info-change").removeClass('hidden').addClass('info-change-finish');
    $(".info-change h3").text("教室编号："+$(this).parent().prevAll().eq(3).text());
    $(".info-change input").val($(this).parent().prevAll().eq(2).text());
    $(".info-change input").eq(1).val($(this).parent().prevAll().eq(1).text());
    //缓存
    $cache = $(this);
    //点击确定，执行请求
    $(".info-btn-one").click(function(){
        var count = parseInt($(".info-change input").val());
        var where = $(".info-change input").eq(1).val();
        //向后台发送请求
        if(PostClassInfo(count,where,1) == 1){
            $cache.parents('tr').removeAttr('class').addClass('warning');   //为行添加warning样式
        }
        $(".hide-screen").removeClass('hide-screen-finish');
        $(".info-change").removeClass('info-change-finish');
        setTimeout(function(){
            $(".hide-screen").addClass('hidden');
            $(".info-change").addClass('hidden');
        },500);
    });
    //点击取消，隐藏div
    $(".info-btn-two").click(function(){
        $(".hide-screen").removeClass('hide-screen-finish');
        $(".info-change").removeClass('info-change-finish');
        setTimeout(function(){
            $(".hide-screen").addClass('hidden');
            $(".info-change").addClass('hidden');
        },500);
        return;
    });
});
/*删除*/
$("tbody").on("click",".btn-del",function(){
    $(".hide-screen").removeClass('hidden').addClass('hide-screen-finish');
    $(".ask").removeClass('hidden').addClass('ask-finish');
    //缓存
    $cache = $(this);
    //点击确定，执行请求
    $(".ask-btn-one").click(function(){
        //向后台发送请求
        if(PostClassInfo(0,0,0) == 1){
            $cache.parents('tr').removeAttr('class').addClass('danger');   //为行添加danger样式
            $cache.attr('disabled','disabled').addClass('table-btn-disabled');
            $cache.prevAll().eq(1).attr('disabled','disabled').addClass('table-btn-disabled');
        }
        $(".hide-screen").removeClass('hide-screen-finish');
        $(".ask").removeClass('ask-finish');
        setTimeout(function(){
            $(".hide-screen").addClass('hidden');
            $(".ask").addClass('hidden');
        },500);
    });
    //点击取消，隐藏div
    $(".ask-btn-two").click(function(){
        $(".hide-screen").removeClass('hide-screen-finish');
        $(".ask").removeClass('ask-finish');
        setTimeout(function(){
            $(".hide-screen").addClass('hidden');
            $(".ask").addClass('hidden');
        },500);
        return;
    });
});
/*pagination逻辑处理*/
$(".pagination").on("click","a",function(){
    UpDatePagStatus();
    //如果按钮处于active或者disabled状态，不处理
    if($(this).parent().hasClass('active') || $(this).parent().hasClass('disabled')){
        return;
    }
    //获取页码长度
    var pagLength = $(".pagination li").length;
    //如果点击的是向前一页按钮
    if($(this).parent().hasClass('pe')){
        //遍历列表li
        for(var i = 1;i < pagLength-1;i++){
            if($(".pagination li").eq(i).hasClass('active')){
                $(".pagination li").eq(i).removeAttr('class');
                $(".pagination li").eq(i-1).addClass('active');
                //AutoShowUserInfo();
                break;
            }
        }
        UpDatePagStatus();
        return;
    }
    //如果点击的是向后一页按钮
    if($(this).parent().hasClass('ne')){
        //遍历列表li
        for(var i = 1;i < pagLength - 1;i++){
            if($(".pagination li").eq(i).hasClass('active')){
                $(".pagination li").eq(i).removeAttr('class');
                $(".pagination li").eq(i+1).addClass('active');
                //AutoShowUserInfo();
                break;
            }
        }
        UpDatePagStatus();
        return;
    }
    //随机点击
    for(var i = 1;i < pagLength - 1;i++){
        if($(".pagination li").eq(i).hasClass('active') && $(this).parent().index() != i){
            $(".pagination li").eq(i).removeAttr('class');
            $(this).parent().addClass('active');
            //AutoShowUserInfo();
            UpDatePagStatus();
            return;
        }
    }
});
/*更新pagination前后按钮状态*/
function UpDatePagStatus(){
    var pagLength = $(".pagination li").length;
    if($(".pagination li").eq(1).hasClass('active')){
        $(".pagination li").eq(0).addClass('disabled');
    }
    else{
        $(".pagination li").eq(0).removeClass('disabled');
    }
    if($(".pagination li").eq(pagLength-2).hasClass('active')){
        $(".pagination li").eq(pagLength-1).addClass('disabled');
    }
    else{
        $(".pagination li").eq(pagLength-1).removeClass('disabled');
    }
}
/*文档加载完成后执行的操作*/
$(document).ready(function(){
   UpDatePagStatus();
});