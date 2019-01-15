package dao;

import bean.result.StatisInfoResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisInfoDao {
    //获取评论人数
    public static int GetTotalCount(){
        int count = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select count(*) from S_A_COMMENT group by s_id";
                Statement stmt = conn.createStatement();
                count = stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //获取性别女
    public static int FemaleNum(){
        int count = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select count(*) from STU,S_A_COMMENT where STU.s_id = S_A_COMMENT.s_id and STU.sex = 0";
                Statement stmt = conn.createStatement();
                count = stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //获取性别男
    public static int MaleNum(){
        int count = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select count(*) from STU,S_A_COMMENT where STU.s_id = S_A_COMMENT.s_id and STU.sex = 1";
                Statement stmt = conn.createStatement();
                count = stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //获取15级
    public static int OneNum(){
        int count = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select count(*) from S_A_COMMENT where left (s_id,2) = '15'";
                Statement stmt = conn.createStatement();
                count = stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //获取16级
    public static int TwoNum(){
        int count = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select count(*) from S_A_COMMENT where left (s_id,2) = '16'";
                Statement stmt = conn.createStatement();
                count = stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //获取17级
    public static int ThreeNum(){
        int count = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select count(*) from S_A_COMMENT where left (s_id,2) = '17'";
                Statement stmt = conn.createStatement();
                count = stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //获取18级
    public static int FourNum(){
        int count = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select count(*) from S_A_COMMENT where left (s_id,2) = '18'";
                Statement stmt = conn.createStatement();
                count = stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static StatisInfoResBean StatisInfoRes(){
        StatisInfoResBean resBean = new StatisInfoResBean();

        int TotalNum = StatisInfoDao.GetTotalCount();
        int FemaleNum = StatisInfoDao.FemaleNum();
        int MaleNum = StatisInfoDao.MaleNum();
        int OneNum = StatisInfoDao.OneNum();
        int TwoNum = StatisInfoDao.TwoNum();
        int ThreeNum = StatisInfoDao.ThreeNum();
        int FourNum = StatisInfoDao.FourNum();

        resBean.setTotalNum(TotalNum);
        resBean.setFemaleNum(FemaleNum);
        resBean.setMaleNum(MaleNum);
        resBean.setOneNum(OneNum);
        resBean.setTwoNum(TwoNum);
        resBean.setThreeNum(ThreeNum);
        resBean.setFourNum(FourNum);

        return resBean;
    }

}
