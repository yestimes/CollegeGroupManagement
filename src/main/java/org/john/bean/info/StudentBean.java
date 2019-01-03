package org.john.bean.info;

public class StudentBean {

    private String sid;
    private String password;
    private String name;
    private String nickname;
    private String sex;
    private String college;

    private int userType;


    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSid() {
        return sid;
    }

    private  String getSex(){
        return sex;
    }

    public String getCollege() {
        return college;
    }

    /**
     *  Student 0
     *  Assistant 1
     *  Manager   2
     *  Super Administrator 3
     */
    public int getUserType() {
        return userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUserType(int userType) {
        System.out.println("bean set");
        this.userType = userType;
    }
}
