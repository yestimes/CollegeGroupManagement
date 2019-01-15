function  person(){
    console.log($('#PersonInfoForm').serialize());

    if (!userNameCheck()){
        alert("请正确填写用户名");
        return ;
    }

    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/PersonInfo",//url
        data: $('#PersonInfoForm').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 200) {
                alert(result.info);
                window.location.href = "/index.html";
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

function userNameCheck() {
    //用户名正则，4到16位（字母，数字，下划线，减号）
    var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
    //输出 true
    return (uPattern.test($("#sid-box").val()))
}
