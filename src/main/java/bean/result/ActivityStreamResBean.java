package bean.result;

import bean.dataBuffer.ActiLinkBean;

import java.util.LinkedList;
import java.util.List;

public class ActivityStreamResBean {
    private int status;
    private String info;
    private String url;
    private List<ActiLinkBean> list;

    public void onSuccess(){
        setStatus(200);
        setInfo("success");
        setUrl("/groups?o_id=1");
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

    public List<ActiLinkBean> getList() {
        return list;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setList(List<ActiLinkBean> list) {
        this.list = list;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
