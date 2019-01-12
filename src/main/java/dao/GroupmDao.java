package dao;


import bean.result.GroupmResBean;
import config.DataSourceConfiguration;
import utils.ArgsCheck;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupmDao {

    public static GroupmResBean addGroupm(String sid, int oid){
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
            String sql="select * from groupmanager where s_id='"+sid+"'" ;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {//每个用户最多担当一个社团负责人
                resBean.onFail(403, "该用户已担任社团负责人");
            }

            else {
               InsertGroupm(sid,oid);
                resBean.onGroupmSuccess();
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

    //添加社团负责人

    public static void InsertGroupm(String sid, int oid){


        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            String sql="insert into groupmanager(s_id,o_id) values(?,?)" ;
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

//修改社团负责人
    public static GroupmResBean UpdateGroupm(String sid, int oid){

        GroupmResBean resBean = new GroupmResBean();
        if (!ArgsCheck.isStringArgsCorrect(sid)){
            resBean.onFail(400, "学号不能为空");
        }else if ( oid <= 0) {
            resBean.onFail(400, "社团号不能为空");
        }

        if(UserPermissionLevel.inGroupManager(sid) >= 1) {
                resBean.onFail(400, "该用户已担当社团负责人");
            }
            else {
                if (Update(sid, oid) == 0){
                    InsertGroupm(sid, oid);
                }
                resBean.onGroupmSuccess();
            }


        return resBean;
    }

    //是否存在



   //更新社团负责人的数据库
    public static int Update(String sid ,int oid){
        int res = 0;

        try {
            Connection conn = null;
            conn = DataSourceConfiguration.getConnection();

            String sql="update groupmanager set s_id = ? where o_id ='"+oid+"'" ;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, sid);
            res =  pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            return res;
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return -1;
    }

}
