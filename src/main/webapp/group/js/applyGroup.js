function  apply(){
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/ApplyMe",//url
        data: $('#PersonInfoForm').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 200) {
                alert("申请成功");

            } else {
                alert(result.info);
            }
        },
        error: function (resBean) {
            console.log(resBean)
            alert("异常！");
        }
    });


}

