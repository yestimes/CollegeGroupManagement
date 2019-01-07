package bean.result;


public class LoginResBean {

    //200 成功
    private int status;
    //如果发生错误，补充错误信息
    private String info;
    //用户昵称
    private String nickname;

    private String url;



    public void onSuccess(String nickname){
        setStatus(200);
        setInfo("Success");
        setUrl("index.html");
        setNickname(nickname);
    }

    public void onFail(int status, String info){
        setStatus(status);
        setInfo(info);
        setUrl("");
    }

    public void onSystemError(){
        setStatus(500);
        setUrl("");
        setInfo("远程服务器内部错误");
    }


    public String getNickname() {
        return nickname;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public String getUrl() {
        return url;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
