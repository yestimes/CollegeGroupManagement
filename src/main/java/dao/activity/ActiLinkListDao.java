package dao.activity;

import bean.dataBuffer.ActiLinkBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ActiLinkListDao {
    public static List getActiLinkList(){
        try {
            Connection conn = DataSourceConfiguration.getConnection();
            String sql = "SELECT ac_id, ac_name FROM ACTIVITY";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            List<ActiLinkBean> actiData = new LinkedList<ActiLinkBean>();
            ActiLinkBean bean = null;

            while (resultSet.next()){
                bean = new ActiLinkBean();
                bean.setAc_id(resultSet.getInt("ac_id"));
                bean.setAc_name(resultSet.getString("ac_name"));
                actiData.add(bean);
            }

            return  (actiData);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
