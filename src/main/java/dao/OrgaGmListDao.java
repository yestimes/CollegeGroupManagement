package dao;

import bean.dataBuffer.OrgaLinkBean;
import bean.info.OrgaGmBean;
import bean.result.OrgaGmListResBean;
import config.DataSourceConfiguration;
import utils.ArgsCheck;


import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class OrgaGmListDao {



    public static OrgaGmListResBean queryOne(String o_ids){
        OrgaGmListResBean res = new OrgaGmListResBean();
        if(ArgsCheck.isStringArgsCorrect(o_ids)){
            OrgaGmBean bean = query(Integer.parseInt(o_ids));
            if (bean == null){
                bean = new OrgaGmBean();
                bean.setO_id(""  + o_ids);
                res.setData(new LinkedList<OrgaGmBean>());
                res.getData().add(bean);
                res.onDataEmpty();
            }else {
                LinkedList<OrgaGmBean> data = new LinkedList<>();
                data.add(bean);
                res.setData(data);
                res.onSuccess();
            }
        }else {
            res.onArgsIncorrect();
        }

        return res;
    }


    private static OrgaGmBean query(int o_id){
        try {
            Connection conn = DataSourceConfiguration.getConnection();
            if (conn == null){
                return null;
            }else {
                String sql = "SELECT organization.o_name, groupmanager.s_id, stu.s_name FROM groupmanager, organization, stu where groupmanager.o_id = organization.o_id and groupmanager.s_id = stu.s_id and groupmanager.o_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, o_id);
                ResultSet resultSet = pstmt.executeQuery();

                OrgaGmBean bean = null;

                if (resultSet.next()){
                    bean = new OrgaGmBean();
                    bean.setO_id(o_id+ "");
                    bean.setO_name(resultSet.getString("o_name"));
                    bean.setS_id(resultSet.getString("s_id"));
                    bean.setS_name(resultSet.getString("s_name"));

                }
                else {
                    System.out.println("OrgaGm of o_id " + o_id + " not found");
                    return null;
                }
                resultSet.close();
                conn.close();
                pstmt.close();

                return bean;
            }
        } catch (PropertyVetoException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    public static OrgaGmListResBean queryAll(){
        OrgaGmListResBean resBean = new OrgaGmListResBean();
        resBean.setData(new LinkedList<OrgaGmBean>());

        //Orga list
        LinkedList<OrgaLinkBean> orgas = (LinkedList<OrgaLinkBean>)OrgaLinkListDao.getOrgaLinkList();
        OrgaGmBean bean = null;
        for (OrgaLinkBean orga: orgas){
            if (orga.getO_id() != 0){

                if ((bean = query(orga.getO_id())) == null){
                    bean = new OrgaGmBean();
                    bean.onEmpty(orga.getO_name(), orga.getO_id());
                    resBean.getData().add(bean);
                }else {
                    bean.setO_name(orga.getO_name());
                    resBean.getData().add(bean);
                }
            }else {
                System.out.println("OrgaGm query, o_id not correct");
                resBean.onSystemException();
            }
        }
        resBean.onSuccess();
        return resBean;

    }
}
