$(document).ready(function(){
    $.getJSON("/getIndexInitInfo",function(result){
        console.log(result);
        $("#user-type-box").text(result.userType);
        $("#user-college-box").text(result.college);
        $("#student-name-box").text(result.name);
    });
});

$("#logout-link").click(function(){
    alert("退出登陆");
    //$("#test1").text("Hello world!");
});
