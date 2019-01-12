package bean.result;

import bean.info.OrgaGmBean;

import java.util.List;

public class OrgaGmListResBean {
    private int status;
    private String info;
    private String url;

    private List<OrgaGmBean> data;

    public void onSuccess(){
        setInfo("数据获取成功");
        setStatus(200);
        setUrl("");
    }

    public void onArgsIncorrect(){
        setStatus(403);
        setData(null);
        setInfo("参数错误, args error");
        setUrl("");
    }

    public void onSystemException(){
        setStatus(500);
        setData(null);
        setInfo("系统内部错误, can not get connection from jdbc");
    }

    public void onDataEmpty(){
        setStatus(200);
        setInfo("数据集查询为空, result set is empty");
    }

    public String getInfo() {
        return info;
    }

    public String getUrl() {
        return url;
    }

    public int getStatus() {
        return status;
    }

    public List<OrgaGmBean> getData() {
        return data;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setData(List<OrgaGmBean> data) {
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
