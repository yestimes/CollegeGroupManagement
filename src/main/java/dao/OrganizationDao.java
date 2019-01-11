package dao;

import bean.info.OrganizationBean;
import bean.result.OrgaResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static int AddOrga(OrganizationBean bean){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("datasource error");
            }else{
                String sql = "insert into  ORGANIZATION(o_name,o_create_time,o_logo,o_description) values(?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,bean.getO_name());
                pstmt.setDate(2,new Date(bean.getO_create_time().getTime()));
                pstmt.setString(3,bean.getO_logo());
                pstmt.setString(4,bean.getO_description());

                result = pstmt.executeUpdate();
                pstmt.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static int DeleteOrga(int o_id){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "delete from ORGANIZATION where o_id = '" + o_id +"'";
                Statement stmt = conn.createStatement();
                result = stmt.executeUpdate(sql);

                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int edit(OrganizationBean bean){

        Connection conn = null;
        int result = 0;
        try {
            if((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
                result = -1;
            }
            else{
                String sql = "update ORGANIZATION set o_name = ?,  o_logo = ?, o_description = ? where o_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,bean.getO_name());
                pstmt.setString(2,bean.getO_logo());
                pstmt.setString(3,bean.getO_description());
                pstmt.setInt(4,bean.getO_id());

                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private static boolean isStringArgsCorrect(String src){
        if(src == null || "".equals(src)){
            return false;
        }
        else {
            return true;
        }
    }


    private static boolean validate(OrganizationBean bean){
        boolean flag = true;
        if (!isStringArgsCorrect(bean.getO_name())){
            flag = false;
        }
        if( !isStringArgsCorrect(bean.getO_description())){
              flag =  false;
        }
        if (!isStringArgsCorrect(bean.getO_logo())){
            flag = false;
        }

        return flag;
    }


    public static OrgaResBean EditOrga(OrganizationBean bean){
        OrgaResBean res = new OrgaResBean();

        if (validate(bean)){
            if (edit(bean) > 0){
                res.onEditSuccess(bean.getO_id());
            }else {
                res.onOrgaNotFound();
            }
        }
        else {
            res.onArgsInCorrect();
            System.out.println("o_name"+ bean.getO_name());
            System.out.println("o_logo"+ bean.getO_logo());
            System.out.println("o_desc"+ bean.getO_description());

        }

        return res;
    }

    //加载要修改的社团信息
    public static OrganizationBean LoadOrga(String o_ids){
        int o_id = 0;

        if(o_ids != null && !("".equals(o_ids))){
            o_id = Integer.parseInt(o_ids); //获取到了要加载的o_id
        }
        OrganizationBean bean = new OrganizationBean();
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else{
                String sql = "select * from ORGANIZATION where o_id = '" + o_id + "'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    bean.setO_name(rs.getString("o_name"));
                    bean.setO_create_time(rs.getDate("o_create_time"));
                    bean.setO_logo(rs.getString("o_logo"));
                    bean.setO_description(rs.getString("o_description"));
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    //查询全部社团信息
    public static List<OrganizationBean> OrgaList(){
        List<OrganizationBean> list = new ArrayList<OrganizationBean>();
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else{
                String sql = "select * from ORGANIZATION";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    OrganizationBean bean = new OrganizationBean();
                    int o_id = rs.getInt("o_id");
                    bean.setO_id(o_id);
                    bean.setO_name(rs.getString("o_name"));
                    bean.setO_create_time(rs.getDate("o_create_time"));
                    bean.setO_logo(rs.getString("o_logo"));
                    bean.setO_description(rs.getString("o_description"));

                    String sql2 = "select s_name from STU,GROUPMANAGER,ORGANIZATION where STU.s_id = GROUPMANAGER.s_id and GROUPMANAGER.o_id = ORGANIZATION.o_id and GROUPMANAGER.o_id ='" + o_id + "'";
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(sql2);
                    if (rs2.next()){
                        bean.setGma_name(rs2.getString("s_name"));
                    }
                    stmt2.close();

                    String sql3 = "select s_name from STU,ASSISTANT,ORGANIZATION where STU.s_id = ASSISTANT.s_id and ASSISTANT.o_id = ORGANIZATION.o_id and ASSISTANT.o_id ='" + o_id + "'";
                    Statement stmt3 = conn.createStatement();
                    ResultSet rs3 = stmt3.executeQuery(sql3);
                    String [] Ga = new String[2];
                    int i = 0;
                    while (rs3.next()){ //认为都有两个助理的情况
                        Ga[i] = rs3.getString("s_name");
                        i++;
                    }
                    bean.setGa_names(Ga);
                    stmt3.close();


                    list.add(bean);
                }
                stmt.close();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //按照社团名查找社团
    public static OrganizationBean FindOrgaByName(String o_name){
        OrganizationBean bean = null;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("datasource error");
            }
            else{
                bean = new OrganizationBean();
                String sql1 = "select * from ORGANIZATION where o_name = '" + o_name + "'";
                Statement stmt1 = conn.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql1);
                if(rs1.next()){
                    bean.setO_id(rs1.getInt("o_id"));
                    bean.setO_name(rs1.getString("o_name"));
                    bean.setO_create_time(rs1.getDate("o_create_time"));
                    bean.setO_logo(rs1.getString("o_logo"));
                    bean.setO_description(rs1.getString("o_description"));
                }
                stmt1.close();

                String sql2 = "select s_name from STU,GROUPMANAGER,ORGANIZATION where STU.s_id = GROUPMANAGER.s_id and GROUPMANAGER.o_id = ORGANIZATION.o_id and ORGANIZATION.o_name = '" + o_name + "'";
                Statement stmt2 = conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(sql2);
                if (rs2.next()){
                    bean.setGma_name(rs2.getString("s_name"));
                }
                stmt2.close();

                String sql3 = "select s_name from STU,ASSISTANT,ORGANIZATION where STU.s_id = ASSISTANT.s_id and ASSISTANT.o_id = ORGANIZATION.o_id and ORGANIZATION.o_name = '" + o_name + "'";
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

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return bean;
    }

    public static int FindOid(String o_name){
        int o_id = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "selete o_id from organization where o_name = '" + o_name + "'";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    o_id = rs.getInt("o_id");
                }
                stmt.close();

            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return o_id;

    }

    public int AddTag(){
        int result = 0;

        return result;
    }

}
