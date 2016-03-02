/**
 * Created by format on 2016/2/23.
 */


/*----------------------------------日历相关函数--------------------------------------*/
var YEAR = parseInt($(".year-month h2:first-child").text());    //取得年月
var MONTH = parseInt($(".year-month h2:last-child").text());
/*-------------重设年，月-----------------*/
function UpDataYearAndMonth(string){
    if(string == 'next'){
        if(MONTH + 1 > 12){
            MONTH = 1;
            YEAR++;
        }
        else{
            MONTH++;
        }
    }
    else if(string == 'prev'){
        if(MONTH - 1 < 1){
            MONTH = 12;
            YEAR--;
        }
        else{
            MONTH--;
        }
    }
    else if(string == 'now'){
    }
    $(".year-month h2:first-child").text(YEAR+"/");
    $(".year-month h2:last-child").text(MONTH);
}
/*-------------------判断闰年------------------------*/
function IsLeapYear(year){
    if((year % 4 === 0)&&(year %100 !==0)||(year % 400 ===0)){
        return true;
    }
    return false;
}
/*-------------------得到某月的天数-------------------*/
function GetDays(year,month){
    var num = 31;
    switch (month){
        case 2:
            num = IsLeapYear(year) ? 29 : 28;
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            num = 30;
            break;
    }
    return num;
}
/*-------------------计算星期--------------------------*/
/*以2016-1-1星期五为基准*/
function CalcWeek(year,month,day){
    var number = 0;
    var weekvar = [5,6,0,1,2,3,4];
    if(year < 2016){
       return false;
    }
    if(year > 2016){
        for(var i = 2016;i < year;i++){
            number += IsLeapYear(i) ? 366 : 365;
        }
    }
    if(month == 1){
        number += day - 1;
        return weekvar[number%7];
    }
    for(var i = 1;i < month;i++){
        number += GetDays(year,i);
    }
    return weekvar[(number + day - 1)%7];
}
/*----------------更新日历主体----------------------*/
function UpDataCalendar(year,month){
    var i = CalcWeek(YEAR,MONTH,1);
    var j = 1;
    //得到前一月天数
    var lastmonth = 0;
    if(month == 1){
        lastmonth = 31;
    }
    else{
        lastmonth = GetDays(year,month - 1);
    }
    for(var k = i;k >= 0;k--,lastmonth--){
        $(".calendar-day ul li").eq(k-1).text(lastmonth).addClass('date-old');

    }
    for(i,j = 1;j <= GetDays(year,month);i++,j++){
        $(".calendar-day ul li").eq(i).text(j).removeAttr('class');
    }
    for(i,j = 1;i < 42;i++,j++){
        $(".calendar-day ul li").eq(i).text(j).addClass('date-old');
    }
}
/*---------------获取点击的日期--------------------*/
function GetClickDate($var){
    var year = YEAR;
    var month = MONTH;
    for(var i =0;i < 42;i++){
        $(".calendar-day ul li").eq(i).removeClass('date-active');
    }
    if($var.hasClass('date-old')){
        if(parseInt($var.text()) > 20){
            if(month == 1){
                year--;
                month = 12;
            }
            else{
                month--;
            }
        }
        else{
            if(month == 12){
                year++;
                month = 1;
            }
            else{
                month++;
            }
        }
    }
    $var.removeClass('date-old').addClass('date-active');
    return (year + '-' + month + '-' + $var.text());
}
/*------------------重设日期为当前时间---------------------------*/
function ReSetDate(){
    var today = new Date();
    YEAR = today.getFullYear();
    MONTH = today.getMonth()+1;
    var day = today.getDate();
    UpDataYearAndMonth('now');
    UpDataCalendar(YEAR,MONTH);
    for(var i = 0;i < 42;i++){
        if($(".calendar-day ul li").eq(i).text() == day){
            $(".calendar-day ul li").eq(i).addClass('date-active');
            break;
        }
    }
}
$(".next").click(function(){
    UpDataYearAndMonth('next');
    UpDataCalendar(YEAR,MONTH)
});
$(".prev").click(function(){
    UpDataYearAndMonth('prev');
    UpDataCalendar(YEAR,MONTH);
});
$(".year-month").click(function(){
   ReSetDate();
});
$(".calendar-day ul li").click(function(){
    $("#date-chosen").val(GetClickDate($(this)))
    if($("#date-chosen").parent().hasClass('has-error')){
        $("#date-chosen").parent().removeClass('has-error');
    }
});

/*--------------------------------------------------------------选择时间的插件--------------------------------------------------*/
var h = parseInt($("#time-h").val());
var m = parseInt($("#time-m").val());
$(".time-h-up").on("click", function () {
    if(h+1 == 24){
        $("#time-h").val("00");
        h = 0;
    }
    else{
        $("#time-h").val(++h);
    }
    FullTime();
});
$(".time-h-down").on("click", function () {
    if(h-1 == 0){
        $("#time-h").val("00");
        h = 24;
    }
    else{
        $("#time-h").val(--h);
    }
    FullTime();
});
$(".time-m-up").on("click", function () {
    if(m+10 == 60){
        $("#time-h").val(++h);
        $("#time-m").val("00");
        m = 0;
    }
    else{
        $("#time-m").val(m+=10);
    }
    FullTime();
});
$(".time-m-down").on("click", function () {
    if(m-10 == 0){
        $("#time-m").val("00");
        m = 0;
    }
    else if(m-10 < 0){
        $("#time-h").val(--h);
        $("#time-m").val(50);
        m = 50;
    }
    else{
        $("#time-m").val(m-=10);
    }
    FullTime();
});
/*构造完整时间*/
function GetTime(){
    var h = $("#time-h").val();
    var m = $("#time-m").val();
    return h+":"+m;
}
function FullTime(){
    $(".use").val(GetTime());
}
/*弹出选择框*/
$("#begin-time,#end-time").on("click", function () {
    for(var i = 0;i < 4;i++){
        if($("input").eq(i).hasClass('use')){
            $("input").eq(i).removeClass('use');
        }
    }
    $(this).addClass('use');
   $(".time-chosen").addClass('time-chosen-show');
});
/*隐藏选择框*/
$(".fa-close").on("click", function () {
    $(".time-chosen").removeClass('time-chosen-show');
});
/*点击确定填充输入框*/
$(".time-chosen-bottom [type='button']").on("click", function () {
    FullTime();
    $(".time-chosen").removeClass('time-chosen-show');
});
/*----------验证输入的信息-------------*/
function Check(){
    var status = true;
    if($("#date-chosen").val() == ""){
        $("#date-chosen").parent().addClass('has-error');
        status = false;
    }
    if($("#begin-time").val() == ""){
        $("#begin-time").parent().addClass('has-error')
        status = false;
    }
    if($("#end-time").val() == ""){
        $("#end-time").parent().addClass('has-error')
        status = false;
    }
    if($("#test-name").val() == ""){
        $("#test-name").parent().addClass('has-error')
        status = false;
    }
    return true;
}
/*获取焦点移除error样式*/
$("#date-chosen").focus(function(){
   $(this).parent().removeClass('has-error');
});
$("#begin-time").focus(function(){
   $(this).parent().removeClass('has-error');
});
$("#end-time").focus(function(){
   $(this).parent().removeClass('has-error');
});
$("#test-name").focus(function(){
   $(this).parent().removeClass('has-error');
});
/*----------点击下一步按钮---------------*/
$("#btn-form-one").click(function(){
   if(Check()){
       $("#one").addClass('form-hidden');
       $(".progress-container h1:first-child").addClass('title-hidden').text('①').append('<i class="fa fa-fw fa-check"></i>');
       setTimeout(function(){
           $("#one").removeClass('form-hidden').addClass('hidden');
           $(".progress-container h1:nth-child(2)").removeClass('hidden').addClass('title-2-show');
           $("#two").removeClass('hidden');
       },500);
   }
});
$("#btn-form-two").click(function(){
    $("#two").addClass('form-hidden');
    $(".progress-container h1:nth-child(2)").removeClass('title-2-show').addClass('title-hidden').text('②').append('<i class="fa fa-fw fa-check"></i>');
    setTimeout(function(){
        $(".progress-container h1:nth-child(3)").removeClass('hidden').addClass('title-3-show');
        $("#three").removeClass('hidden');
    },500);
});
$(document).ready(function(){
   ReSetDate();
});