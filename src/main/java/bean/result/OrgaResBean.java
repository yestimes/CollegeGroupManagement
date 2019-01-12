package bean.result;

import bean.info.OrganizationBean;

import java.util.List;

public class OrgaResBean {
    private int status;//状态
    private String info;//提示信息
    private  String url;//跳转页面
    private List<OrganizationBean> list;//存放所有社团列表信息
    private OrganizationBean bean;//存放一条结果bean


    public void onArgsInCorrect(){
        setStatus(403);
        setInfo("参数错误");
    }

    public void ListSuccess(List<OrganizationBean> list){
        setStatus(200);
        setInfo("获取社团列表成功！");
        setList(list);
    }

    public void ListFail(){
        setStatus(500);
        setInfo("获取社团列表失败！");
    }

    public void BeanSuccess(OrganizationBean bean){
        setStatus(200);
        setInfo("获取该社团成功！");
        setBean(bean);
    }

    public void BeanFail(){
        setStatus(500);
        setInfo("获取社团列表失败！");
    }

    public void onOrgaNotFound(){
        setStatus(400);
        setInfo("未找到该记录");
    }


    public void AddSuccess(){
        setStatus(200);
        setInfo("创建成功！");
        //setUrl("/groups?o_id="+o_id);
    }

    public void AddFail(){
        setStatus(500);
        setInfo("创建失败！");
    }

    public void DeleteSuccess(){
        setStatus(200);
        setInfo("删除成功！");
    }

    public void DeleteFail(){
        setStatus(500);
        setInfo("删除失败！");
    }

    public void onEditSuccess(int o_id){
        setStatus(200);
        setInfo("修改成功！");
        setUrl("/groups?o_id="+o_id);
    }

    public void EditFail(){
        setStatus(500);
        setInfo("修改失败！");
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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setList(List<OrganizationBean> list) {
        this.list = list;
    }

    public List<OrganizationBean> getList() {
        return list;
    }

    public void setBean(OrganizationBean bean) {
        this.bean = bean;
    }

    public OrganizationBean getBean() {
        return bean;
    }
}
