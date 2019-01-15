package bean.result;

import bean.info.OTBean;

import java.util.List;

public class TagResBean {
    private int status;
    private String info;
    private List<OTBean> list;

    public void onArgsInCorrect(){
        setStatus(403);
        setInfo("参数错误");
    }

    public void NoneTag(){
        setStatus(500);
        setInfo("Do not Have This Tag ID in TAG Table!");
    }

    public void AddSuccess(){
        setStatus(200);
        setInfo("Add Tag Success!");
    }

    public void AddFail(){
        setStatus(500);
        setInfo("Add Tag Failed!");
    }

    public void ListSuccess(List<OTBean> list){
        setStatus(200);
        setInfo("Get All Tag_Organization List Success!");
        setList(list);
    }

    public void ListFail(){
        setStatus(500);
        setInfo("Get All Tag_Organization List Failed!");
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setList(List<OTBean> list) {
        this.list = list;
    }

    public List<OTBean> getList() {
        return list;
    }
}
