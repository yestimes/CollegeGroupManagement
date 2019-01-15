
$(document).ready(
  function () {
    $.getJSON("/OrgaPaneInit", function(res){
      console.log(res);
      $("#o_id").val(res.o_id);
    });
  }
);


function commit() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/AddActi" ,//url
        data: $('#acti-form').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 200) {
                alert( result.info);
                window.location.href = "/OrgaPane/index.html";
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
