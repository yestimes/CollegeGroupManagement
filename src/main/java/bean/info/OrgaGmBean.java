package bean.info;

public class OrgaGmBean {
    private String o_id;
    private String o_name;
    private String s_id;
    private String s_name;

    public void onEmpty(String o_name, int o_id){
        setS_id("");
        setS_name("空缺");
        setO_name(o_name);
        setO_id(o_id + "");

    }

    public String getO_name() {
        return o_name;
    }

    public String getO_id() {
        return o_id;
    }

    public String getS_id() {
        return s_id;
    }

    public String getS_name() {
        return s_name;
    }


    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

}
