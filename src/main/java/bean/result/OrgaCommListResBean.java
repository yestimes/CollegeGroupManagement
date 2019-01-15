package bean.result;

import bean.info.OrgaCommBean;

import java.util.List;

public class OrgaCommListResBean {
    private List<OrgaCommBean> list;
    private int status;
    private String info;

    public void OrgaCommListSuccess(List<OrgaCommBean> list){
        setList(list);
        setStatus(200);
        setInfo("Get OrgaCommList Success!");
    }

    public void OrgaCommListFail(){
        setStatus(500);
        setInfo("Get OrgaCommList Failed!");
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

    public void setList(List<OrgaCommBean> list) {
        this.list = list;
    }

    public List<OrgaCommBean> getList() {
        return list;
    }
}
