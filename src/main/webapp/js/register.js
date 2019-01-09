function  commit(){
  if ($("#item-checkbox").prop('checked') != true){
    alert("请同意许可条款");
    return;
  }

  if (!userNameCheck()){
    alert("请正确填写用户名");
    return ;
  }
  if (!emailCheck()){
    alert("请正确填写邮箱");
    return ;

  }
  if (!nameCheck("name-box")){
    alert("请正确填写姓名");
    return ;

  }
  if ($("#nickname-box").val().length < 1){
    alert("请正确填写昵称");
    return ;

  }

  if (!isPasswordInSame()){
    alert("两次密码不匹配");
    return ;
  }

  if (!isMale()){
    alert("请务必勾选性别");
    return;
  }

  $.ajax({
      //几个参数需要注意一下
      type: "POST",//方法类型
      dataType: "json",//预期服务器返回的数据类型
      url: "/register" ,//url
      data: $("#registerForm").serialize(),
      success: function (result) {
          console.log(result);//打印服务端返回的数据(调试用)
          if (result.status == 200) {
              alert(result.info);
              window.location.href = result.url;
          }
          else {
              alert(result.info);
          }
      },
      error : function(res) {
          console.log(res)
          alert("异常！");
      }
  });


}

function userNameCheck() {
  //用户名正则，4到16位（字母，数字，下划线，减号）
  var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
  //输出 true
  return (uPattern.test($("#sid-box").val()))
}

function emailCheck(){
  //Email正则
  var ePattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
  //输出 true
  return (ePattern.test($("#email-box").val()));
}

function nameCheck( elemId) {
  //包含中文正则
  var cnPattern = /[\u4E00-\u9FA5]/;
//输出 true
  return (cnPattern.test($("#" + elemId).val()));

}

function isPasswordInSame(){
  return $("#password").val() == $("#password2").val();
}

function isMale(){
  return ($("#male").is(':checked') && !$("#formale").is(':checked'))

}
