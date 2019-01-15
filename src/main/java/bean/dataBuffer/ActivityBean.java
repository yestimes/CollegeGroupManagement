package bean.dataBuffer;

import utils.ArgsCheck;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityBean {
    private int ac_id;//活动id，自增长
    private String ac_name;//活动名称
    private String ac_description;//活动描述
    private int ac_limit;   //人数上限
    private int o_id;//哪个社团举办的
    private Date ac_start_time;//活动开始时间
    private Date ac_end_time;//活动结束时间
    //还有权限限制没写

    public void setAc_id(int ac_id) {
        this.ac_id = ac_id;
    }

    public int getAc_id() {
        return ac_id;
    }

    public void setO_id(String o_ids) {
        if (ArgsCheck.isStringArgsCorrect(o_ids)){
            this.o_id = Integer.parseInt(o_ids);
        }else {
            System.out.println("o_id fault");
        }
    }

    public void setO_id(int o_id){
        this.o_id = o_id;
    }

    public int getO_id() {
        return o_id;
    }

    public int getAc_limit() {
        return ac_limit;
    }

    public void setAc_limit(String ac_limits) {
        if (ArgsCheck.isStringArgsCorrect(ac_limits)){
            this.ac_limit = Integer.parseInt(ac_limits);
        }else {
            System.out.println("parse to ac limit (int) fault");
        }
    }

    public void setAc_description(String ac_description) {
        this.ac_description = ac_description;
    }

    public Date getAc_end_time() {
        return ac_end_time;
    }

    public void setAc_end_time(String str_time) {
        if (ArgsCheck.isStringArgsCorrect(str_time)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            try {
                this.ac_end_time = sdf.parse(str_time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("not found time ");
            this.ac_end_time = new Date();
        }

    }

    public Date getAc_start_time() {
        return ac_start_time;
    }

    public void setAc_name(String ac_name) {
        this.ac_name = ac_name;
    }

    public String getAc_description() {
        return ac_description;
    }

    public void setAc_start_time(String str_ime) {
        if (ArgsCheck.isStringArgsCorrect(str_ime)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            try {
                this.ac_start_time = sdf.parse(str_ime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("not found time ");
            this.ac_start_time = new Date();
        }
    }

    public String getAc_name() {
        return ac_name;
    }
}
