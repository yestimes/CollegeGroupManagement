package bean.info;

public class PersonInfoBean {

    private  String sid;
    private String nickname;
    private String classroom;
    private String photo;
    private String usersign;


public String getSid(){return sid;}

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname(){return nickname;}

    public void setSid(String username) {
        this.sid = sid;
    }

    public String getClassroom(){return classroom;}

    public void setClassroom(String classsroom){this.classroom=classsroom;}

    public String getPhoto(){return photo;}

    public void setPhoto(String photo){this.photo=photo;}

    public String getUsersign(){return usersign;}

    public void setUsersign(String usersign){this.usersign=usersign;}


}
