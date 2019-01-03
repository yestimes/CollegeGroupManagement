package org.john.bean.result;

import org.john.bean.info.StudentBean;
import org.john.utils.Latin2utf8;

public class IndexInitBean {

    //错误码
    private int status;
    /**用户信息
     */
    private String name;
    private String userType;
    private String college;
    /**描述信息
    */
    private String info;

    /** 快捷状态设置
    */
    public void onNotLogin(){
        this.setStatus(403);
        this.setInfo(Latin2utf8.transform("没有的登陆"));
        setUserType(0);
        setCollege("");
        setName(Latin2utf8.transform("路人甲"));
    }
    public void onPassed(){
        this.setStatus(200);
        this.setInfo("ok");
    }

    public void onError(String exp){
        this.setStatus(500);
        this.setInfo(exp);
    }

    /*
    * 填充用户信息
    * */

    public int setUserInfo(StudentBean userInfo){
        if (userInfo == null){
            System.out.println("StuidentBean info, null pointer exception ");
            return -1;
        }
        else {
            setUserType(userInfo.getUserType());
            setCollege(userInfo.getCollege());
            setName(userInfo.getName());
            return 0;
        }
    }

    /*
    * 用户角色
    * */
    public void setUserType(int userType) {
        String []userTypes = {"学生", "助理", "负责人", "超级管理员"};
        this.userType = Latin2utf8.transform(userTypes[userType]);

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getUserType() {
        return userType;
    }

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public String getCollege() {
        return college;
    }

    public String getName() {
        return name;
    }
}
