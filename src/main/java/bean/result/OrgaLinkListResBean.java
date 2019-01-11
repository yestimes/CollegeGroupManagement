package bean.result;

import bean.dataBuffer.OrgaLinkBean;

import java.util.List;

public class OrgaLinkListResBean {


    private int status;
    private String info;
    private List<OrgaLinkBean> data;
    private String url;

    //删除社团
    public void onPermissionDenied(){
        setStatus(403);
        setInfo("权限不足");
        setUrl("/login.html");
        setData(null);
    }
    public void DeleteSuccess(){
        setStatus(200);
        setInfo("删除成功！");
        setUrl("/admin/group-delete.html");
    }

    public void DeleteFail(){
        setStatus(500);
        setInfo("警告，删除失败！");
        setUrl("/admin/group-delete.html");

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

    public List<OrgaLinkBean> getData() {
        return data;
    }

    public void setData(List<OrgaLinkBean> data) {
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
