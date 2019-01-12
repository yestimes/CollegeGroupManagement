package dao;



import bean.result.GroupmResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssisDao {

    public static GroupmResBean addAssis(String sid, int oid){
        GroupmResBean resBean = new GroupmResBean();

        if (sid == null || "".equals(sid)){
            resBean.onFail(400, "学号不能为空");
        }
        else if ( "".equals(oid)) {
            resBean.onFail(400, "社团号不能为空");
        }

        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            //在社团助理表中查找学号，如果学号有两个则该用户已担当了两个社团助理的职位
            String sql="select * from assistant where s_id='"+sid+"'" ;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            int result = pstmt.executeUpdate();

            if (result>2) {//该用户已担当两个社团助理
                resBean.onFail(403, "该用户已担当两个社团助理");
            }

            else {
                InsertAssis(sid,oid);
                resBean.onAssisSuccess();
            }

            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return resBean;

    }


//增加社团助理
    public static void InsertAssis(String sid, int oid){


        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            String sql="insert into assistant(s_id,o_id) values(?,?)" ;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sid);
            pstmt.setInt(2, oid);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }


    public static GroupmResBean DeleteAssis(String sid,int oid){

        GroupmResBean resBean=new GroupmResBean();

        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }


        String sql = "delete from assistant where s_id = '"+sid+"'and oid='"+oid+"'";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.executeUpdate();
            resBean.onAssisSuccess();
            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
      return resBean;

    }

}
