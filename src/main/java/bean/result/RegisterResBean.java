package bean.result;

import utils.Latin2utf8;

public class RegisterResBean {
    //状态
    private int status;
    //错误定位
    private String info;
    //注册成功后重定向的链接
    private String url;

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public void onServerInternalException(){
        setStatus(510);
        setInfo("服务器内部错误");
    }

    public void onSidRequired(){

        String str = getInfo();
        if (str == null) {
            setInfo(new String("学号为空;"));
        }
        else {
            setInfo(str + "学号为空;");
        }

        setStatus(410);
    }

    public void onEmailRequired(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("邮箱为空;"));
        }
        else {
            setInfo(str + "邮箱为空;");
        }

        setStatus(411);
    }

    public void onInvaildEmail(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("邮箱格式不正确;"));
        }
        else {
            setInfo(str + "邮箱格式不正确;");
        }
        setStatus(412);
    }

    public void onPasswordRequired(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("密码不为空;"));
        }
        else {
            setInfo(str + "密码不为空;");
        }
        setStatus(413);
    }

    public void onPassword2Required(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("重复密码不为空;"));
        }
        else {
            setInfo(str + "重复密码不为空;");
        }
        setStatus(414);
    }

    public void onPasswordNotSame(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("两次密码不匹配;"));
        }
        else {
            setInfo(str + "两次密码不匹配;");
        }
        setStatus(415);
    }
    public void onSexNotChoose(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("没有选择性别;"));
        }
        else {
            setInfo(str + "没有选择性别;");
        }
        setStatus(416);
    }

    public void onCollegeSelectNone(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("没有选择院系、部门;"));
        }
        else {
            setInfo(str + "没有选择院系、部门;");
        }
        setStatus(417);
    }

    public void onRealnameRequired(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("没有填写姓名;"));
        }
        else {
            setInfo(str + Latin2utf8.transform("没有填写姓名;"));
        }
        setStatus(418);
    }

    public void onNicknameRequired(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("没有填写昵称;"));
        }
        else {
            setInfo(str + "没有填写昵称;");
        }
        setStatus(419);
    }

    public void onDataBaseException(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("服务器数据库错误;"));
        }
        else {
            setInfo(str + "数据库错误;");
        }
        setStatus(500);
    }

    public void onRegistPassed(){
        setInfo("注册成功");
        setUrl("/login.html");
    }

    public void onExists(){
        setStatus(403);
        setInfo("学号已经存在");
    }




}
