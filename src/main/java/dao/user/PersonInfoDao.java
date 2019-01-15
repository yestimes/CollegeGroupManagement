package dao.user;

import bean.dataBuffer.Customer;
import bean.info.PersonInfoBean;
import bean.result.PersonResBean;
import bean.result.RegisterResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.*;


public class PersonInfoDao {

    public static PersonResBean getPersonInfo(String sid, String nickname, String classroom, String photo, String usersign) {

        PersonResBean resBean = new PersonResBean();
        resBean.setStatus(200);
        if (classroom == null || "".equals(classroom)) {
            //out.print("班级不能为空");
            resBean.onCollegeSelectNone();
        } else {
            try {
                Connection conn = null;
                if ((conn = DataSourceConfiguration.getConnection()) == null) {
                    System.out.println("datasource error");
                }
                //往userinfo表中插入学号s_id 以便之后更新表
                    addSid(sid);

                //更新userinfo表
                Update(sid,nickname,photo,classroom,usersign);

                resBean.onPassed();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }

        }

        return resBean;
    }

    public static void addSid(String sid1) {
        // TODO Auto-generated method stub

        int flag;
        PersonInfoBean PerBean = new PersonInfoBean();
        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            //如果在userinfo表中找得到学号s_id则不插入
            String sql = "select * from userinfo where s_id='" + sid1 + "'";
            Statement stmt = conn.createStatement();
            System.out.println("sql:" + sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {//userinfo表里有数据
                flag = 0;

            } else {//没有数据
                //插入s_id
                InsertSid(sid1);
                flag = 1;

            }
            System.out.println("-------------->>>>>>>>>>flag:"+flag);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static void InsertSid(String sid) {
      int b;
        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            String sql = "insert into userinfo(s_id) value (?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sid);

           int res= pstmt.executeUpdate();
            if (res == 0) {
                b=0;
            } else {
                b=1;
            }
       System.out.println("------------>>>>>>>b:"+b);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }


    // 更新stu表中nickname的数据
    public static void Update(String sid, String nickname, String photo, String classroom, String usersign) {
//
        int c;
        PersonResBean resBean = null;
        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            } else {


                //更新userinfo表
                String sql = "update userinfo set classroom=? ,photo=?,usersign=? ,nickname=? where s_id=?";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, classroom);
                pstmt.setString(2, photo);
                pstmt.setString(3, usersign);
                pstmt.setString(4, nickname);
                pstmt.setString(5, sid);

                int res = pstmt.executeUpdate();
                if (res == 0) {
                  c=0 ; //resBea
                } else {
                    c=1;//resBean.onPassed();
                }

          System.out.println("-------------->>>>>>c:"+c);
                pstmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();

        }
    }
}



