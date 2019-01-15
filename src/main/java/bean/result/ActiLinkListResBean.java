package bean.result;

import bean.dataBuffer.ActiLinkBean;

import java.util.List;

public class ActiLinkListResBean {
    private int status;
    private String info;
    private List<ActiLinkBean> data;
    private String url;

    //删除活动
    public void onPermissionDenied(){
        setStatus(403);
        setInfo("权限不足");
        setUrl("/login.html");
        setData(null);
    }
    public void DeleteSuccess(){
        setStatus(200);
        setInfo("删除成功！");
        setUrl("/admin/activity-delete.html");
    }

    public void DeleteFail(){
        setStatus(500);
        setInfo("警告，删除失败！");
        setUrl("/admin/activity-delete.html");

    }


    public int getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public String getUrl() {
        return url;
    }

    public List<ActiLinkBean> getData() {
        return data;
    }

    public void setData(List<ActiLinkBean> data) {
        if (data == null){
            setStatus(500);
            setInfo("服务器内部错误");
        }
        else {
            setStatus(200);
        }
        this.data = data;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
