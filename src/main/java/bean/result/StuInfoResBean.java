package bean.result;

public class StuInfoResBean {

    private int status;
    private String info;

    private String s_name;
    private String s_id;

    public void onSysemException(){
        setS_id("");
        setS_name("");
        setStatus(500);
        setInfo("系统内部错误");
    }


    public void onNotFound(){
        setS_id("");
        setS_name("");
        setStatus(404);
        setInfo("没有找到");
    }

    public void  onSuccess(String s_id, String s_name){
        setS_id(s_id);
        setS_name(s_name);
        setStatus(200);
        setInfo("success");
    }

    public void onArgsInValidate(){
        setS_id("");
        setS_name("");
        setStatus(403);
        setInfo("参数缺失");
    }


    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public String getS_name() {
        return s_name;
    }

    public String getS_id() {
        return s_id;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

}
