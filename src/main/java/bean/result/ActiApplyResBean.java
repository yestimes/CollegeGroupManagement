package bean.result;

import bean.info.ActiApplyBean;

import java.util.List;

public class ActiApplyResBean {
    private int status;
    private String info;
    private List<ActiApplyBean> list;

    public void ActiApplySuccess(){
        setStatus(200);
        setInfo("Activity Apply Success!Please waiting for accept!");
    }

    public void ActiApplyFail(){
        setStatus(500);
        setInfo("Activity Apply Failed!");
    }

    public void onArgsInCorrect(){
        setStatus(403);
        setInfo("Error Args!");
    }

    public void AcceptApplySuccess(){
        setStatus(200);
        setInfo("Accept Activity Apply Success!");
    }

    public void AcceptApplyFail(){
        setStatus(500);
        setInfo("Accept Activity Apply Failed!");
    }

    public void ApplyListSuccess(List<ActiApplyBean> list){
        setStatus(200);
        setInfo("Get Activity Apply List Success!");
        setList(list);
    }

    public void ApplyListFail(){
        setStatus(500);
        setInfo("Get Activity Apply List Failed!");
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setList(List<ActiApplyBean> list) {
        this.list = list;
    }

    public List<ActiApplyBean> getList() {
        return list;
    }
}
