//退出登陆
$("#logout-link").click(function(){
  $.getJSON("/userLogout", function(res){
    if (res.status == 200){
      alert("再见，" + res.username);
      }
      else {
        alert(res.info);
      }
      window.location = "/login.html";
    });
});
