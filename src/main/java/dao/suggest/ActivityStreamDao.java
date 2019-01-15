package dao.suggest;

import bean.dataBuffer.ActiLinkBean;
import bean.result.ActivityStreamResBean;
import config.DataSourceConfiguration;
import dao.activity.ActiLinkListDao;
import utils.ArgsCheck;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ActivityStreamDao {

    private static ActivityStreamResBean getActivityAll(){
        ActivityStreamResBean streamResBean = new ActivityStreamResBean();
        List<ActiLinkBean> data = ActiLinkListDao.getActiLinkList();
        streamResBean.setList(data);
        return streamResBean;
    }


    public static ActivityStreamResBean getActivityInterst(String s_id){
        //推荐只给50个
        final int total = 50;

        ActivityStreamResBean streamResBean = getActivityAll();
        List<ActiLinkBean> data = ActiLinkListDao.getActiLinkList();
        //查看各个社团留言比例
        int sum = countForS_o_comment(s_id);



        return streamResBean;

    }


    public static int countForS_o_comment(String s_id){
        int num = -1;
        if (ArgsCheck.isStringArgsCorrect(s_id)){
            try {
                Connection conn = DataSourceConfiguration.getConnection();
                String sql = "SELECT COUNT(s_o_index) FROM s_o_comment WHERE s_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, s_id);

                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()){
                    num = resultSet.getInt(1);
                }else {
                    num =  0;
                }

                resultSet.close();
                pstmt.close();
                conn.close();

                return num;
            } catch (PropertyVetoException e) {
                num = -2;
                e.printStackTrace();
            } catch (SQLException e) {
                num = -1;
                e.printStackTrace();
            }
        }else {
            return num;
        }
        return num;
    }

    public static List<Integer> getInterestOrgaFromS_o_comment(String s_id){
        List<Integer> list = new LinkedList<>();
        if (!ArgsCheck.isStringArgsCorrect(s_id)){
            return null;
        }else {
            try {
                Connection conn = DataSourceConfiguration.getConnection();
                String sql = "SELECT  DISTINCT o_id FROM s_o_comment  WHERE s_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                ResultSet resultSet = pstmt.executeQuery();

                while (resultSet.next()){
                    list.add(resultSet.getInt(1));
                }

                resultSet.close();
                pstmt.close();
                conn.close();
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  list;
    }

    public static int s_o_commentCount(int o_id, String s_id){
        int num = -1;
        if (!ArgsCheck.isStringArgsCorrect(s_id)){
            return num;
        }else {
            try {
                Connection conn = DataSourceConfiguration.getConnection();
                String sql = "SELECT COUNT (s_o_index) FROM s_o_comment WHERE s_id = ? AND  o_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, s_id);
                pstmt.setInt(2, o_id);

                ResultSet resultSet = pstmt.executeQuery();
                if (resultSet.next()){
                    num = resultSet.getInt(1);
                }else {
                    num = 0;
                }

                resultSet.close();
                pstmt.close();
                conn.close();
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return num;
    }



}

