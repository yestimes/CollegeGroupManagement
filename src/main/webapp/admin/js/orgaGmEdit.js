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

    $.getJSON("/getOrgaGmInfo", function(res){
      console.log(res);
      if (res.status == 200){
        $("#o_id").val(res.data[0].o_id);
        $("#orgaName").text(res.data[0].o_name);
        $("#gm_sname").val(res.data[0].sname);

      }else {
        alert("初始化失败，错误码:" + res.status);
      }
    });

});

function sname_validate() {
  var s_name = $('#gm_sname').val();
  $.getJSON("/stuInfoSeek?s_name=" + s_name, function(res) {
        console.log(res);//打印服务端返回的数据(调试用)
        if (res.status == 200) {
              $("#gm_sid").val(res.s_id);
              alert("用户存在，可以提交");
        }
        else {
            alert("不正确");
        }

});

};


function commit(){
  console.log($('#orga-form').serialize());
  $.ajax({
    //几个参数需要注意一下
    type: "POST",//方法类型
    dataType: "json",//预期服务器返回的数据类型
    url: "/editOrgaGm" ,//url
    data: $('#orga-form').serialize(),
    success: function (result) {
        console.log(result);//打印服务端返回的数据(调试用)
        if (result.status == 200) {
            alert( result.info);
            window.location.href = "/admin/groups.html";
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

};
