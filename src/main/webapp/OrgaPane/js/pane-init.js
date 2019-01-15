$(document).ready(function(){
    $.getJSON("/getIndexInitInfo",function(res){
        console.log(res);
        //初始化页面信息
        //根据权限，初始化左边菜单
        if(res.status == 200){
          if(res.permission <= 0){
            alert("不允许非授权用户访问");
               window.location = "/login.html"
          }
          else {
            //sa user
            $("#user-name-box").text(res.name);
          }
        }
        else{
          alert("未登陆");
          window.location = "/login.html"
        }
    });

    
});
