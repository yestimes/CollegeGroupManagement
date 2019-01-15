package dao.organization;

import config.DataSourceConfiguration;
import utils.ArgsCheck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class OrgaPaneDao {

    public static int getOrgaIdOfGm(String s_id)  {
        int o_id = 0;
        if (!ArgsCheck.isStringArgsCorrect(s_id)){
            o_id = -1;
        }else {
            Connection conn = null;
            try {
                conn = DataSourceConfiguration.getConnection();
                String sql = "SELECT o_id from groupmanager where s_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, s_id);

                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()){
                    o_id = resultSet.getInt("o_id");
                }else {
                    System.out.println("Query set is empty");
                }

                resultSet.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("Finished  query, o_id :" + o_id);
        return o_id;
    }

    public static int[] getAssistO_ids(String s_id){
        int[] o_ids = new int[2];
        if (ArgsCheck.isStringArgsCorrect(s_id)){
            return null;
        }else {
            Connection conn = null;
            try {
                conn = DataSourceConfiguration.getConnection();
                String sql = "SELECT o_id from  assistant where s_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, s_id);

                ResultSet resultSet = pstmt.executeQuery();
                int i = 0;
                while (resultSet.next()){
                    o_ids[i++] = resultSet.getInt("s_id");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return o_ids;
    }
}
