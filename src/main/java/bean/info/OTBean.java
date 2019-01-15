package bean.info;

public class OTBean {
    //该学生给该社团添加了这个标签
    private int o_id;//社团号
    private String s_id;//学号
    private int t_id;//添加的标签号

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getO_id() {
        return o_id;
    }
}
