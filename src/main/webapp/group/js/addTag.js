function AddTag() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/AddTag" ,//url
        data: $('#TagForm').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 200) {
                alert("添加标签成功！");
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
