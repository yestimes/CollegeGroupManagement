package dao;

import bean.info.OrganizationBean;
import bean.result.StatisInfoResBean;
import config.DataSourceConfiguration;
import dao.organization.OrganizationDao;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StatisInfoDao {


    public static StatisInfoResBean StatisInfoRes() {
        StatisInfoResBean resBean = new StatisInfoResBean();
        List<String> orgaNames = new LinkedList<>();

        List<OrganizationBean> orgs =  OrganizationDao.OrgaList();
        List<Integer> o_ids = new LinkedList<>();
        for (OrganizationBean bean: orgs){
            orgaNames.add(bean.getO_name());
            o_ids.add(bean.getO_id());
        }
        List<Integer> orgaSum = new LinkedList<>();

        for (int id : o_ids){
            orgaSum.add(getMemCount(id));
        }

        resBean.setOrgaNames(orgaNames);
        resBean.setOrgaMemCount(orgaSum);

        try {
            resBean.setMaleNum(getisMaleSum(true));
            resBean.setFormaleNum(getisMaleSum(false));

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return resBean;
    }

    public static int getMemCount(int o_id){
        int num = 0;
        try {
            Connection conn = DataSourceConfiguration.getConnection();
            String sql = "SELECT COUNT(*) FROM member where o_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, o_id);
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
        return num;
    }

    public static int getisMaleSum(boolean flag) throws PropertyVetoException, SQLException {
        int num = 0;
        Connection conn = DataSourceConfiguration.getConnection();
        String sql = "SELECT COUNT(*) FROM stu WHERE sex = ";
        if (flag){
            sql = sql + "1";
        }else {
            sql = sql + "0";
        }
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        if (resultSet.next()){
            num = resultSet.getInt(1);
        }else {
            return -1;
        }

        return num;
    }

}
