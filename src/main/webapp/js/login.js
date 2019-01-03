function login() {
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/userLogin" ,//url
        data: $('#userInfoForm').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 200) {
                alert("欢迎您，" + result.nickname);
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