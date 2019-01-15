package dao.activity;

import bean.dataBuffer.ActivityBean;
import bean.result.ActiResBean;
import config.DataSourceConfiguration;
import utils.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDao {
    public static int AddActi(ActivityBean bean){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else{
                String sql = "insert into ACTIVITY(ac_name,ac_description,o_id,ac_start_time,ac_end_time) values(?,?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,bean.getAc_name());
                pstmt.setString(2,bean.getAc_description());
                pstmt.setInt(3,bean.getO_id());
                pstmt.setDate(4,new java.sql.Date(bean.getAc_start_time().getTime()));
                pstmt.setDate(5,new java.sql.Date(bean.getAc_end_time().getTime()));

                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int DeleteActi(int ac_id){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "delete from ACTIVITY where ac_id = '" + ac_id + "'";
                Statement stmt = conn.createStatement();
                result = stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //加载要修改的活动信息
    public static ActivityBean LoadActi(int ac_id){
        ActivityBean bean = new ActivityBean();
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else{
                String sql = "select * from ACTIVITY where ac_id = '" + ac_id + "'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                   bean.setAc_name(rs.getString("ac_name"));
                   bean.setAc_description(rs.getString("ac_description"));
                   bean.setO_id(rs.getInt("o_id"));
                   bean.setAc_start_time(DateUtils.dataBaseDate2String(rs.getDate("ac_start_time")));
                   bean.setAc_end_time(DateUtils.dataBaseDate2String(rs.getDate("ac_end_time")));
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int EditActi(ActivityBean bean){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else{
                String sql = "update ACTIVITY set ac_name = ?, ac_description = ?, o_id = ?, ac_start_time = ?, ac_end_time = ? where ac_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,bean.getAc_name());
                pstmt.setString(2,bean.getAc_description());
                pstmt.setInt(3,bean.getO_id());
                pstmt.setDate(4,new java.sql.Date(bean.getAc_start_time().getTime()));
                pstmt.setDate(5,new java.sql.Date(bean.getAc_end_time().getTime()));
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    //判断传入的字符串是否为空
    private static boolean isStringArgsCorrect(String src){
        if(src == null || "".equals(src)){
            return false;
        }
        else {
            return true;
        }
    }

    //判断传来的bean的葛子端是否都不为空，用到了isStringArgsCorrect函数
    private static boolean validate(ActivityBean bean){
        boolean flag = true;
        if (!isStringArgsCorrect(bean.getAc_name())){
            flag = false;
        }
        if( !isStringArgsCorrect(bean.getAc_description())){
            flag =  false;
        }
        if (bean.getO_id() == 0){
            flag = false;
        }
        if (!isStringArgsCorrect(bean.getAc_start_time().toString())){
            flag = false;
        }
        if (!isStringArgsCorrect(bean.getAc_start_time().toString())){
            flag = false;
        }
        return flag;
    }

    //返回一个resBean结果集  其实是加载load的结果传进来
    public static ActiResBean EditRes(ActivityBean bean){
        ActiResBean res = new ActiResBean();

        if (validate(bean)){//保证这个bean各字段都不为空
            if (EditActi(bean) > 0){ //修改操作成功
                res.onEditSuccess();
            }else {
                res.onOrgaNotFound();//返回400未找到该记录
            }
        }
        else {
            res.onArgsInCorrect();//返回403参数错误
            System.out.println("ac_name"+ bean.getAc_name());
            System.out.println("o_id"+ bean.getO_id());
            System.out.println("ac_desc"+ bean.getAc_description());
            System.out.println("ac_start_time"+ bean.getAc_start_time());
            System.out.println("ac_end_time"+bean.getAc_end_time());
        }
        return res;
    }

    //获取所有活动列表
    public static List<ActivityBean> ActiList(){
        List<ActivityBean> list = new ArrayList<ActivityBean>();
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select * from ACTIVITY";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    ActivityBean bean = new ActivityBean();
                    bean.setAc_id(rs.getInt("ac_id"));
                    bean.setAc_name(rs.getString("ac_name"));
                    bean.setAc_description(rs.getString("ac_description"));
                    bean.setO_id(rs.getInt("o_id"));

                    bean.setAc_start_time(DateUtils.dataBaseDate2String( rs.getDate("ac_start_time")));
                    bean.setAc_end_time( DateUtils.dataBaseDate2String(rs.getDate("ac_end_time")));
                    list.add(bean);
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //按活动名查找
    public static ActivityBean FindActiByName(String ac_name){
        ActivityBean bean = new ActivityBean();
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select * from ACTIVITY where ac_name = "+ ac_name + "'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    bean.setAc_id(rs.getInt("ac_id"));
                    bean.setAc_name(rs.getString("ac_name"));
                    bean.setAc_description(rs.getString("ac_description"));
                    bean.setO_id(rs.getInt("o_id"));

                    bean.setAc_start_time(DateUtils.dataBaseDate2String( rs.getDate("ac_start_time")));
                    bean.setAc_end_time( DateUtils.dataBaseDate2String(rs.getDate("ac_end_time")));
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /*public static int FindAcid(String ac_name){
        int ac_id = 0;
        Connection conn = null;
        try {
            if ((conn = config.DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "selete ac_id from ACTIVITY where ac_name = '" + ac_name + "'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    ac_id = rs.getInt("ac_id");
                }
                stmt.close();

            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return ac_id;

    }*/


}
