
$(document).ready(function () {
  console.log("init");

  $.getJSON("/getIndexInitInfo",function(result){
      console.log(result);
      //初始化页面信息
      $("#student-name-box").text(result.name);
      //根据权限，初始化左边菜单
      if(result.permission == 0){
        window.location = "/index.html"
      }
      else if(result.permission == 1 || result.permission == 2){
          //$("#control-box").empty();
      }

  });

  $.getJSON("/LoadOrga", function(res){
      console.log(res);
      $("#o_description").text(res.o_description);

      var orgaName = document.getElementById("orgaName");
      orgaName.setAttribute("value",res.o_name);

      $("#orgaId").val(res.o_id);

  });


});

function commit(){
  console.log($('#orga-form').serialize());
  $.ajax({
    //几个参数需要注意一下
    type: "POST",//方法类型
    dataType: "json",//预期服务器返回的数据类型
    url: "/EditOrga" ,//url
    data: $('#orga-form').serialize(),
    success: function (result) {
        console.log(result);//打印服务端返回的数据(调试用)
        if (result.status == 200) {
            alert( result.info);
            window.location.href = "/admin/groupInfo-edit.html";
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
