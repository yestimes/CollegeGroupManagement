package bean.result;

import java.util.Date;

public class CommResBean {
    private int status;
    private String info;

    public void OpenCommentSuccess(){
        setStatus(200);
        setInfo("Open this activity's comment Success!");
    }

    public void OpenCommentFail(){
        setStatus(500);
        setInfo("Open this activity's comment Failed!");
    }

    public void CloseCommentSuccess(){
        setStatus(200);
        setInfo("Close this activity's comment Success!");
    }

    public void CloseCommentFail(){
        setStatus(500);
        setInfo("Close this activity's comment Failed!");
    }

    public void CommSuccess(){
        setStatus(200);
        setInfo("Add Comment Success!");
    }

    public void CommFail(int id, String s_id, String comment, Date comment_time){
        setStatus(500);
        setInfo("Add Comment Failed!id:"+id+",s_id:"+s_id+",comment:"+comment+",comment_time"+comment_time);
    }

    public void DeleCommSuccess(){
        setStatus(200);
        setInfo("Delete Comment Success!");
    }

    public void DeleCommFail(){
        setStatus(500);
        setInfo("Delete Comment Failed!");
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
}
