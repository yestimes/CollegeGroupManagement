package dao;

import bean.info.OrganizationBean;
import config.DataSourceConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrganizationDao {
    public static OrganizationBean getOrgaInfo(int o_id){
        OrganizationBean bean = null;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("datasource error");
            }
            bean = new OrganizationBean();

            String sql1 = "select * from ORGANIZATION where o_id = '" + o_id + "'";
            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if(rs1.next()){
                bean.setO_name(rs1.getString("o_name"));
                bean.setO_create_time(rs1.getDate("o_create_time"));
                bean.setO_logo(rs1.getString("o_logo"));
                bean.setO_description(rs1.getString("o_description"));
            }
            stmt1.close();

            String sql2 = "select s_name from STU,GROUPMANAGER where STU.s_id = GROUPMANAGER.s_id and GROUPMANAGER.o_id = '" + o_id +"'";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql2);
            if (rs2.next()){
                bean.setGma_name(rs2.getString("s_name"));
            }
            stmt2.close();

            String sql3 = "select s_name from STU,ASSISTANT where STU.s_id = ASSISTANT.s_id and ASSISTANT.o_id = '" + o_id +"'";
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery(sql3);
            String [] Ga = {"assistant1","assistant2"};
            while (rs3.next()){ //认为都有两个助理的情况
                int i = 0;
                    Ga[i] = rs3.getString("s_name");
                    i++;
            }
            bean.setGa_names(Ga);
            stmt3.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return bean;
    }

}
