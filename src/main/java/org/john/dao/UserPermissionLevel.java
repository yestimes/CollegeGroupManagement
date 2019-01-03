package org.john.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.john.config.dao.DataSourceConfiguration;


import java.beans.PropertyVetoException;
import java.sql.*;


public class UserPermissionLevel {

    public static int getLevel(String s_id){
        int level = -1;
        int isSa = inSA(s_id);
        int isGma = inGroupManager(s_id);
        int isGa  = inGroupAssistant(s_id);

        if (isSa >0 ){
            level = 3;
        }else if (isGma > 0){
            level = 2;
        }else if (isGa > 0){
            level = 1;
        }else {
            level = 0;
        }
        System.out.println("Level" + level);
        return level;

    }

    private static int inSA(String username){
        try {
            Connection conn= DataSourceConfiguration.getDataSource().getConnection();
            String sql = "SELECT COUNT(sa_index) FROM SA WHERE s_id = '" + username + "'" ;
            /*PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet res = pstmt.executeQuery(sql);
            */
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println(sql);
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

    private static int inGroupManager(String username){
        try {
            Connection conn= DataSourceConfiguration.getDataSource().getConnection();
            String sql = "SELECT COUNT(gm_index) FROM GROUPMANAGER WHERE s_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet res = pstmt.executeQuery();
            if (res.next()){
                return res.getInt(1);
            }
            else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    private static int inGroupAssistant(String username){
        try {
            Connection conn= DataSourceConfiguration.getDataSource().getConnection();
            String sql = "SELECT COUNT(as_index) FROM ASSISTANT WHERE s_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet resultSet =  pstmt.executeQuery();
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

    private static int inGroupMember(String username){
        try {
            Connection conn= DataSourceConfiguration.getDataSource().getConnection();
            String sql = "SELECT COUNT(me_index) FROM MEMBER WHERE s_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet resultSet =  pstmt.executeQuery();
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


}
