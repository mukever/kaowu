/**
 * Created by format on 2016/2/17.
 */


var serverstatus = true;
/*点击 通过 || 驳回 按钮，得到职工号*/
function GetId($i){
    return $i.parent().prevAll().eq(1).text();
}

/*ajax发送数据并返回结果*/
function SendData(id,type){
    var serverRetType = 0;
    $.ajax({
        type: "POST",
        url: "Audit.action",
        async: "false",
        data: {"ID": id,"Type":type},
        dataType: "json",
        success: function(data){
            serverRetType = JSON.parse(data).type;
            return serverRetType;
        },
        error: function(jqXHR,textStatus,errorThrown){
            serverstatus = false;
            alert("服务器请求出错: " + textStatus);
        }
    });
    if(serverstatus == false){
        return 0;
    }
    else{
        return serverRetType;
    }
}

/*点击通过按钮*/
$(".btn-agree").click(function(){
    var id = GetId($(this));
    if(SendData(id,1) != 0){
        $(this).attr("disabled",'disabled');    //设置按钮不可点击属性
        $(this).parents('tr').removeAttr('class').addClass('success');   //为行添加success样式
        $(this).addClass('table-btn-disabled'); //添加不可点击的样式
        if($(this).nextAll().eq(1).hasClass('table-btn-disabled')){
            $(this).nextAll().eq(1).removeAttr("disabled"); //移除不可点击属性
            $(this).nextAll().eq(1).removeClass('table-btn-disabled');  //移除不可点击样式
        }
    }
});

/*点击驳回按钮*/
$(".btn-disagree").click(function(){
    var id = GetId($(this));
    if(SendData(id,0) != 0){
        $(this).attr("disabled",'disabled');    //设置按钮不可点击属性
        $(this).parents('tr').removeAttr('class').addClass('danger');    //为行添加danger样式
        $(this).addClass('table-btn-disabled'); //添加不可点击样式
        if($(this).prevAll().eq(1).hasClass('table-btn-disabled')){
            $(this).prevAll().eq(1).removeAttr("disabled"); //移除不可点击属性
            $(this).prevAll().eq(1).removeClass('table-btn-disabled');  //移除不可点击样式
        }
    }
});

/*pagination逻辑处理*/
$(".pagination li a").click(function(){
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
    //更新pagination状态
    if($(".pagination li").eq(1)){

    }
});