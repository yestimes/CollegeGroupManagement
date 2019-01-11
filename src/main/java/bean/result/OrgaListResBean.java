package bean.result;

import bean.info.OrganizationBean;

import java.util.List;

public class OrgaListResBean {

    private List<OrganizationBean> list;//存放所有社团列表信息
    private int status;
    private String info;

    public void ListSuccess(List<OrganizationBean> list){
        setStatus(200);
        setInfo("获取社团列表成功！");
        setList(list);
    }

    public void ListFail(){
        setStatus(500);
        setInfo("获取社团列表失败！");
        setList(null);
    }

    public void setList(List<OrganizationBean> list) {
        this.list = list;
    }

    public List<OrganizationBean> getList() {
        return list;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
