/**
 * Created by format on 2016/1/23.
 */
$(document).ready(function(){
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
    //var $content-height = $(".content");
    //alert($content-height.height());
    //if($content-height.height() < window.innerHeight-60)
    //{
    //    $content-height.height(window.innerHeight-60);
    //}
    /*判断用户注册信息，是否显示未处理提示*/
    if($(".audit span").text() == 0){
        $(".audit span").css('display','none');
        $(".audit").css('padding-left','0');
    }
});
