package dao.user;
import config.DataSourceConfiguration;

import java.sql.*;


public class UserPermissionLevel {

    public static int getLevel(String s_id){
        int level = 0;
        int isSa = inSA(s_id);
        int isGma = inGroupManager(s_id);
        int isGa  = inGroupAssistant(s_id);

        if (isSa >0 ){
            level = 3;
        }else if (isGma > 0){
            level = 2;
        }else if (isGa > 0){
            level = 1;
        }
        System.out.println("sa: " + isSa + "," + "gma: " +  isGma + "," + "ga " + ","+ isGa + "level: "+ level);
        return level;

    }

    private static int inSA(String username){
        try {
            Connection conn= DataSourceConfiguration.getDataSource().getConnection();
            String sql = "SELECT COUNT(sa_index) FROM SA WHERE s_id = ?" ;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt(1);
            }
            else {
                return 0;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int inGroupManager(String username){
        int ret = -1;
        try {
            Connection conn= DataSourceConfiguration.getDataSource().getConnection();
            String sql = "SELECT COUNT(gm_index) FROM GROUPMANAGER WHERE s_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet res = pstmt.executeQuery();

            if (res.next()){
                ret =  res.getInt(1);
            }
            else {
                ret = 0;
            }
            res.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            ret = -1;
            e.printStackTrace();
        }

        return ret;
    }
    public static int inGroupAssistant(String username){
        int ret = -1;
        try {
            Connection conn= DataSourceConfiguration.getDataSource().getConnection();
            String sql = "SELECT COUNT(*) FROM ASSISTANT WHERE s_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet resultSet =  pstmt.executeQuery();

            if (resultSet.next()){
                ret =  resultSet.getInt(1);
            }
            else {
                ret = 0;
            }
            resultSet.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    private static int inGroupMember(String username) {
        int ret = -1;
        try {
            Connection conn = DataSourceConfiguration.getDataSource().getConnection();
            String sql = "SELECT COUNT(*) FROM MEMBER WHERE s_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                ret = resultSet.getInt(1);
            } else {
                ret = 0;
            }
            resultSet.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }


}
