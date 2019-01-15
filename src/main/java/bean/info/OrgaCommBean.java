package bean.info;

import java.util.Date;

public class OrgaCommBean {
    private int s_o_index;//留言号
    private int o_id;//社团号
    private String s_id;//学生学号
    private String s_o_comment;//留言内容
    private Date s_o_comment_time;//留言时间

    public void setS_o_index(int s_o_index) {
        this.s_o_index = s_o_index;
    }

    public int getS_o_index() {
        return s_o_index;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_o_comment(String s_o_comment) {
        this.s_o_comment = s_o_comment;
    }

    public String getS_o_comment() {
        return s_o_comment;
    }

    public void setS_o_comment_time(Date s_o_comment_time) {
        this.s_o_comment_time = s_o_comment_time;
    }

    public Date getS_o_comment_time() {
        return s_o_comment_time;
    }
}
