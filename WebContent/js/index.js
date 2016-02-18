/**
 * Created by format on 2015/12/2.
 */
function show_class(){
    var child1 = document.getElementById("right-1");
    var child2 = document.getElementById("right-2");
    child1.style.display = 'block';
    child2.style.display = 'block';
}
function hidden_class(){
    var child1 = document.getElementById("right-1");
    var child2 = document.getElementById("right-2");
    child1.style.display = 'none';
    child2.style.display = 'none';
}
function show_room(){
    var child1 = document.getElementById("right-3");
    var child2 = document.getElementById("right-4");
    child1.style.display = 'block';
    child2.style.display = 'block';
}
function hidden_room(){
    var child1 = document.getElementById("right-3");
    var child2 = document.getElementById("right-4");
    child1.style.display = 'none';
    child2.style.display = 'none';
}
$(document).ready(function(){
   $("#left_nav_4").click(function(){
       $("#right-1").fadeIn(700);
       $("#right-2").fadeIn(700);
       $("#right-3").fadeOut(500);
       $("#right-4").fadeOut(500);
       $("#right-4-test").fadeOut(500);
       $("#right-5-test").fadeOut(500);
   });
    $("#left_nav_1").click(function(){
        $("#right-1").fadeOut(500);
        $("#right-2").fadeOut(500);
        $("#right-3").fadeIn(700);
        $("#right-4").fadeIn(700);
        $("#right-4-test").fadeOut(500);
        $("#right-5-test").fadeOut(500);
    });
    $("#left_nav_5").click(function(){
        $("#right-1").fadeOut(500);
        $("#right-2").fadeOut(500);
        $("#right-3").fadeOut(500);
        $("#right-4").fadeOut(500);
        $("#right-4-test").fadeIn(700);
        $("#right-5-test").fadeIn(700);
    });
});


