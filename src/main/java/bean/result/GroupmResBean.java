package bean.result;

public class GroupmResBean {

    //200 成功
    private int status;
    //如果发生错误，补充错误信息
    private String info;

    private String url;



    public void onGroupmSuccess(){
        setStatus(200);
        setInfo("Success");

    }


    public void onAssisSuccess(){
        setStatus(200);
        setInfo("Success");

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


    public void setStatus(int status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}


