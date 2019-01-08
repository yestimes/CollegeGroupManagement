package bean.info;

import java.util.Date;

public class OrganizationBean {
    //社团基本信息
    private int o_id;
    private String o_name;
    private Date o_create_time;
    private String o_logo;
    private String o_description;
    //负责人及助理姓名
    private String gma_name;
    private String[] ga_names;

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_name(String o_name) {
        this.o_name = o_name;
    }

    public String getO_name() {
        return o_name;
    }

    public void setO_create_time(Date o_create_time) {
        this.o_create_time = o_create_time;
    }

    public Date getO_create_time() {
        return o_create_time;
    }

    public void setO_logo(String o_logo) {
        this.o_logo = o_logo;
    }

    public String getO_logo() {
        return o_logo;
    }

    public void setO_description(String o_description) {
        this.o_description = o_description;
    }

    public String getO_description() {
        return o_description;
    }

    public void setGma_name(String gma_name) {
        this.gma_name = gma_name;
    }

    public String getGma_name() {
        return gma_name;
    }

    public void setGa_names(String[] ga_names) {
        this.ga_names = ga_names;
    }

    public String[] getGa_names() {
        return ga_names;
    }
}
