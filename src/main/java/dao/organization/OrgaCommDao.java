package dao.organization;

import bean.info.OrgaCommBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrgaCommDao {
    public static int AddOrgaComm(OrgaCommBean bean){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                 System.out.println("System error!");
            }
            else{
                String sql = "insert into S_O_COMMENT(o_id,s_id,s_o_comment,s_o_comment_time) values(?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,bean.getO_id());
                pstmt.setString(2,bean.getS_id());
                pstmt.setString(3,bean.getS_o_comment());
                pstmt.setDate(4,new java.sql.Date(bean.getS_o_comment_time().getTime()));

                result = pstmt.executeUpdate();
                System.out.println("插入成功了吗? "+result);
                pstmt.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int DeleteOrgaComm(int s_o_index){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "delete from S_O_COMMENT where s_o_index = '" + s_o_index + "'";
                Statement stmt = conn.createStatement();
                result = stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<OrgaCommBean> OrgaCommList(){
        List<OrgaCommBean> list = new ArrayList<OrgaCommBean>();
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select * from S_O_COMMENT";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    OrgaCommBean bean = new OrgaCommBean();
                    bean.setS_o_index(rs.getInt("s_o_index"));
                    bean.setO_id(rs.getInt("o_id"));
                    bean.setS_id(rs.getString("s_id"));
                    bean.setS_o_comment(rs.getString("s_o_comment"));
                    bean.setS_o_comment_time(rs.getDate("s_o_comment_time"));
                    list.add(bean);
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
