/**
 * Created by format on 2016/2/17.
 */
var serverpoststatus = true;
var servergetstatus = true;

var PAG_SHOW_NUMBER = 6;    //初始每页显示的用户数 || 1366*768分辨率取值6,1440*900分辨率取8，1920*1080及以上取12
var ALL_USER_INFO;  //所有用户信息
var PASS_USER_INFO = {pass_user:[]};    //已通过审核的用户
var PASS_USER_LENGTH;   //普通用户数量
var NO_PASS_USER_LENGTH;    //未审核用户数量
var NO_PASS_USER_INFO = {no_pass_user:[]};  //未通过审核的用户
var USER_COLLEGE;

/*点击 通过 || 驳回 按钮，得到职工号*/
function GetId($i){
    return $i.parent().prevAll().eq(1).text();
}

/*ajax获取学院所有用户信息*/
function GetUsersInfo(college){
    $.ajax({
        type: "POST",
        url: "AuditUser.action",
        async: false,
        data: {"College":college},
        dataType: "json",
        success: function(data){
            ALL_USER_INFO = JSON.parse(data);
            UserClassify(ALL_USER_INFO);
        },
        error: function(jqXHR,textStatus,errorThrown){
            servergetstatus = false;
            alert("服务器请求出错： " + textStatus);
        }
    })
}
/*ajax发送处理请求并返回结果*/
function SendData(id,type){
    var serverRetType = 0;
    $.ajax({
        type: "POST",
        url: "Audit.action",
        async: false,
        data: {"ID": id,"Type":type},
        dataType: "json",
        success: function(data){
            serverRetType = JSON.parse(data).type;
            var number = parseInt($(".audit span").text());
            UpDateMsg(--number);
            return serverRetType;
        },
        error: function(jqXHR,textStatus,errorThrown){
            serverpoststatus = false;
            alert("服务器请求出错: " + textStatus);
        }
    });
    if(serverpoststatus == false){
        return 0;
    }
    else{
        return serverRetType;
    }
}

/*对用户进行分类*/
function UserClassify(data){
    if (data.userlist.length == 0){
        return;
    }
    var number_pass = 0;
    var number_no_pass = 0;
    for(var i = 0;i < data.userlist.length;i++){
        if(data.userlist[i].type == -1){
            NO_PASS_USER_INFO.no_pass_user[number_no_pass] = data.userlist[i];
            number_no_pass++;
        }
        else if(data.userlist[i].type == 2){
            PASS_USER_INFO.pass_user[number_pass] = data.userlist[i];
            number_pass++;
        }
    }
    NO_PASS_USER_LENGTH = NO_PASS_USER_INFO.no_pass_user.length;
    PASS_USER_LENGTH = PASS_USER_INFO.pass_user.length;
}
/*切换表格*/
$("#no_handle").click(function(){
    $(".status").removeClass('status-agree');
    $(".handle").addClass('hidden');
    $(".no-handle").removeClass('hidden');
    GetUsersInfo(USER_COLLEGE);
    ShowNoPassUserInfo(1);
    ShowPagination(NO_PASS_USER_LENGTH);
});
$("#_handle").click(function(){
    $(".status").addClass('status-agree');
    $(".no-handle").addClass('hidden');
    $(".handle").removeClass('hidden');
    GetUsersInfo(USER_COLLEGE);
    ShowPassUserInfo(1);
    ShowPagination(PASS_USER_LENGTH);
});
/*点击通过按钮*/
$(".no-handle").on("click",".btn-agree",function(){
    var id = GetId($(this));
    if(SendData(id,1) != 0){
        $(this).attr("disabled",'disabled');    //设置按钮不可点击属性
        $(this).parents('tr').removeAttr('class').addClass('success');   //为行添加success样式
        $(this).addClass('table-btn-disabled'); //添加不可点击的样式
        $(this).nextAll().eq(1).attr("disabled",'disabled');
        $(this).nextAll().eq(1).addClass('table-btn-disabled');
    }
});

/*点击驳回按钮*/
$(".no-handle").on("click",".btn-disagree",function(){
    $(".hide-screen").removeClass('hidden').addClass('hide-screen-finish');
    $(".ask").removeClass('hidden').addClass('ask-finish');
    $cache = $(this);
    $(".ask-btn-one").click(function(){
        var id = GetId($cache);
        if(SendData(id,0) != 0){
            $cache.attr("disabled",'disabled');    //设置按钮不可点击属性
            $cache.parents('tr').removeAttr('class').addClass('danger');    //为行添加danger样式
            $cache.addClass('table-btn-disabled'); //添加不可点击样式
            $cache.prevAll().eq(1).attr("disabled",'disabled'); //移除不可点击属性
            $cache.prevAll().eq(1).addClass('table-btn-disabled');  //移除不可点击样式
        }
        $(".hide-screen").removeClass('hide-screen-finish');
        $(".ask").removeClass('ask-finish');
        setTimeout(function(){
            $(".hide-screen").addClass('hidden');
            $(".ask").addClass('hidden');
        },500);
    });
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
/*点击删除按钮*/
$(".handle").on("click","#delete_user",function(){
    $(".hide-screen").removeClass('hidden').addClass('hide-screen-finish');
    $(".ask").removeClass('hidden').addClass('ask-finish');
    $cache = $(this);
    $(".ask-btn-one").click(function(){
        var id = GetId($cache);
        if(SendData(id,0) != 0){
            $cache.attr("disabled",'disabled');    //设置按钮不可点击属性
            $cache.parents('tr').removeAttr('class').addClass('danger');    //为行添加danger样式
            $cache.addClass('table-btn-disabled'); //添加不可点击样式
        }
        $(".hide-screen").removeClass('hide-screen-finish');
        $(".ask").removeClass('ask-finish');
        setTimeout(function(){
            $(".hide-screen").addClass('hidden');
            $(".ask").addClass('hidden');
        },500);
    });
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
                AutoShowUserInfo();
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
                AutoShowUserInfo();
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
            AutoShowUserInfo();
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

/*更新通知数字*/
function UpDateMsg(i){
    if(i == 0){
        $(".audit span").addClass('hidden');
        $(".audit").css('padding-left','0');
    }
    else{
        $(".audit span").text(i);
    }
}
/*向表格中插入整行:未审核用户*/
function NoPassUserAppend(i,which){
    $(which).append(
        "<tr>"+
        "<th>" + NO_PASS_USER_INFO.no_pass_user[i].username + "</th>"+
        "<th>" + NO_PASS_USER_INFO.no_pass_user[i].age + "</th>"+
        "<th>" + NO_PASS_USER_INFO.no_pass_user[i].therid + "</th>"+
        "<th>未审核</th>"+
        "<th>"+
        '<button class="table-btn-success btn-agree">通&nbsp&nbsp&nbsp过</button>'+
        '<div style="width: 80px;display: inline-block;"></div>'+
        '<button class="table-btn-danger btn-disagree">驳&nbsp&nbsp&nbsp回</button>'+
        "</th>"+
        "</tr>"
    );
}
/*向表格中插入整行:已通过用户*/
function PassUserAppend(i,which){
    $(which).append(
        "<tr>"+
        "<th>" + PASS_USER_INFO.pass_user[i].username + "</th>"+
        "<th>" + PASS_USER_INFO.pass_user[i].age + "</th>"+
        "<th>" + PASS_USER_INFO.pass_user[i].therid + "</th>"+
        "<th>已通过</th>"+
        "<th>"+
        '<button class="table-btn-danger btn-disagree" id="delete_user"><i class="fa fa-fw fa-warning"></i>&nbsp删&nbsp除</button>'+
        "</th>"+
        "</tr>"
    );
}
/*检测显示器分辨率，确定每页显示的用户数量*/
function CheckLED(){
    if(screen.height == 900){
        PAG_SHOW_NUMBER = 8;
    }
    else if(screen.height >= 1080){
        PAG_SHOW_NUMBER = 12;
    }
}
/*调整pagination数量*/
function ShowPagination(i){
    $(".pagination").empty();
    $(".pagination").append(
        '<li class="disabled pe"><a href="#">&laquo;</a></li>'+
        '<li class="active"><a href="#">1</a></li>'
    );
    var number = Math.ceil(i/PAG_SHOW_NUMBER);
    for(var j = number;j > 1;j--){
        $(".active").after('<li><a href="#">' + j + '</a></li>');
    }
    $(".pagination li:last-child").after('<li class="ne"><a href="#">&raquo;</a></li>');
    UpDatePagStatus();	//更新状态
}
/*显示第i页未审核用户信息*/
function ShowNoPassUserInfo(i){
    $(".no-handle").empty();
    var begin = (i-1)*PAG_SHOW_NUMBER;
    for(var j = 0;j < PAG_SHOW_NUMBER && begin < NO_PASS_USER_LENGTH;j++){
        NoPassUserAppend(begin,".no-handle");
        begin++;
    }
}
/*显示第i页通过注册用户信息*/
function ShowPassUserInfo(i){
    $(".handle").empty();
    var begin = (i-1)*PAG_SHOW_NUMBER;
    for(var j = 0;j < PAG_SHOW_NUMBER && begin < PASS_USER_LENGTH;j++){
        PassUserAppend(begin,".handle");
        begin++;
    }
}
/*自动判断类型，显示第页码页用户信息*/
function AutoShowUserInfo(){
    if($(".no-handle").hasClass("hidden")){
        ShowPassUserInfo($(".active").text());
    }
    else{
        ShowNoPassUserInfo($(".active").text());
    }
}
/*文档加载完成后执行的操作*/
$(document).ready(function(){
    USER_COLLEGE = ReturnUserInfo().userinfo.college;
    //Step1. 从服务器获取数据
    GetUsersInfo(USER_COLLEGE);
    //Setp2. 确定每页显示的数量，显示Pagination
    CheckLED();
    ShowPagination(NO_PASS_USER_LENGTH);
    //Step3. 显示第一页未审核信息
    ShowNoPassUserInfo(1);
});
