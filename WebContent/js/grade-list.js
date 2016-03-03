/**
 * Created by format on 2016/2/29.
 */

var GRADE_INFO;	//学院所有班级信息
/*---------获取班级信息----------*
* gradelist*/
function GetGradeInfo(college){
    var status = false;
    $.ajax({
        type: "POST",
        url: "Grade_getList.action",
        async: false,
        data: {"College":college},
        dataType: "json",
        success: function(data){
        	 GRADE_INFO = JSON.parse(data);
        	 status = true;
        },
        error: function(jqXHR,textStatus,errorThrown){
            alert("服务器请求出错： " + textStatus);
        }
    });
    return status;
}
/*----------发送删除班级的请求-----------*/
function DelGrade(gradeid){
    var status = false;
    $.ajax({
        type: "POST",
        url: "Grade_delete.action",
        async: false,
        data: {"Gradeid":gradeid},
        dataType: "json",
        success: function(data){
            //bool
        	status = JSON.parse(data).type;
        },
        error: function(jqXHR,textStatus,errorThrown){
            alert("服务器请求出错： " + textStatus);
        }
    });
    return status;
}

/*--------------点击删除按钮--------------------*/
$(".btn-del").click(function(){
    $(".hide-screen").removeClass('hidden').addClass('hide-screen-finish');
    $(".ask").removeClass('hidden').addClass('ask-finish');
    //缓存
    $cache = $(this);
    //点击确定
    $(".ask-btn-one").click(function(){
        //向后台发送请求
        var id = $cache.parent().prevAll().eq(2).text();
        if(DelGrade("1434010") == false){
            $cache.parents('tr').removeAttr('class').addClass('danger');   //为行添加danger样式
            $cache.attr('disabled','disabled').addClass('table-btn-disabled');
            //$cache.prevAll().eq(1).attr('disabled','disabled').addClass('table-btn-disabled');
        }
        $(".hide-screen").removeClass('hide-screen-finish');
        $(".ask").removeClass('ask-finish');
        setTimeout(function(){
            $(".hide-screen").addClass('hidden');
            $(".ask").addClass('hidden');
        },500);
    });
    //点击取消，隐藏
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
    GetGradeInfo(1);
    alert(GRADE_INFO.gradelist);
});