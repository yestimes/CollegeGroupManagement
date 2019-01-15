package bean.result;

import bean.dataBuffer.ActivityBean;

import java.util.List;

public class ActiResBean {
    private int status;//状态
    private String info;//提示信息
    private List<ActivityBean> list;//存放所有活动列表信息
    private ActivityBean bean;//存放一条结果bean

    public void onArgsInCorrect(){
        setStatus(403);
        setInfo("参数错误");
    }

    public void ListSuccess(List<ActivityBean> list){
        setStatus(200);
        setInfo("获取活动列表成功！");
        setList(list);
    }

    public void ListFail(){
        setStatus(500);
        setInfo("获取活动列表失败！");
    }

    public void BeanSuccess(ActivityBean bean){
        setStatus(200);
        setInfo("获取该活动成功！");
        setBean(bean);
    }

    public void BeanFail(){
        setStatus(500);
        setInfo("获取该活动失败！");
    }

    public void onOrgaNotFound(){
        setStatus(400);
        setInfo("未找到该记录");//有字段为空的
    }

    public void AddSuccess(){
        setStatus(200);
        setInfo("创建成功！");
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

    public void onEditSuccess(){
        setStatus(200);
        setInfo("修改成功！");
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

    public void setBean(ActivityBean bean) {
        this.bean = bean;
    }

    public ActivityBean getBean() {
        return bean;
    }

    public void setList(List<ActivityBean> list) {
        this.list = list;
    }

    public List<ActivityBean> getList() {
        return list;
    }
}
