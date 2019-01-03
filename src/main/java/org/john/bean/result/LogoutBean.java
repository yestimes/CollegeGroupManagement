package org.john.bean.result;

import org.john.bean.info.StudentBean;

public class LogoutBean {

    private int status;
    private String username;
    private String info;


    public void onNoLogin(){
        setStatus(403);
        setUsername("游客");
        setInfo("无效操作");
    }

    public void onSuccess(StudentBean bean){
        setStatus(200);
        setUsername(bean.getName());
        setInfo("退出成功");
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public String getUsername() {
        return username;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
