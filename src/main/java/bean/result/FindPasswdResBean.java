package bean.result;

public class FindPasswdResBean {

    private int status;
    private String info;
    private String url;




    public void onNotFound(){
        setStatus(404);
        setInfo("邮箱学号不匹配或不存在");
    }

    public void onPassed(String url){
        setStatus(200);
        setInfo("请前往邮箱收取您的密码");
        setUrl(url);
    }

    public void onEmailREquired(){
        setStatus(403);
        setInfo("未填写邮箱");
    }

    public void onS_idRequired(){
        setStatus(403);
        setInfo("未填写学号");
    }



    public String getUrl() {
        return url;
    }

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
