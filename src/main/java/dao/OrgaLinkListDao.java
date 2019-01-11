package dao;

import bean.dataBuffer.OrgaLinkBean;
import bean.result.OrgaLinkListResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class OrgaLinkListDao {

    public static List getOrgaLinkList(){

        try {
            Connection conn = DataSourceConfiguration.getConnection();
            String sql = "SELECT o_id, o_name FROM organization";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            List<OrgaLinkBean> orgaData = new LinkedList<OrgaLinkBean>();
            OrgaLinkBean bean = null;

            while (resultSet.next()){
                bean = new OrgaLinkBean();
                bean.setO_id(resultSet.getInt("o_id"));
                bean.setO_name(resultSet.getString("o_name"));
                orgaData.add(bean);
            }

            return  (orgaData);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
