package bean.result;

import utils.Latin2utf8;

public class PersonResBean {
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

    public void onCollegeSelectNone(){
        String str = getInfo();
        if (str == null) {
            setInfo(new String("没有填写班级;"));
        }
        else {
            setInfo(str + "没有填写班级;");
        }
        setStatus(417);
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

    public void onPassed(){
        setInfo("设置成功");
        setUrl("/index.html");
    }




}
