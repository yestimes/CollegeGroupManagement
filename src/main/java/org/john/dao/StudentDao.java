package org.john.dao;



import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.john.bean.result.LoginResBean;
import org.john.bean.info.StudentBean;
import org.john.config.dao.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao {


    //登陆成果的话，就获取用户信息放到Session去
    //失败的话就返回
    public static LoginResBean getUserInfo(StudentBean userInfo, String username, String password){
        LoginResBean resBean = new LoginResBean();

        if (username == null || "".equals(username)){
            resBean.onFail(400, "用户名不能为空");
        }
        else if (password == null || "".equals(password)) {
            resBean.onFail(400, "密码不能为空");
        }
        else {
            try {
                Connection conn = null;
                if ((conn = DataSourceConfiguration.getConnection()) == null){
                    System.out.println("datasource error");
                }

                String sql = "SELECT s_name, password, nickname, sex, c_name FROM STU, COLLEGE WHERE STU.s_id = ? and STU.c_id = COLLEGE.c_id";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);

                ResultSet res = pstmt.executeQuery();

                String[] isMale = {"女","男"};
                if (res.next()){
                    userInfo.setPassword(res.getString("password"));
                    //密码不对
                    if (!userInfo.getPassword().equals(password)){
                        resBean.onFail(403, "密码不正确");
                        return resBean;
                    }

                    userInfo.setName(res.getString("s_name"));
                    userInfo.setNickname(res.getString("nickname"));
                    userInfo.setCollege(res.getString("c_name"));
                    userInfo.setSex(isMale[res.getInt("sex")]);

                    resBean.onSuccess(userInfo.getNickname());

                }
                else {
                    resBean.onFail(404, "用户不存在");
                }
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }

        }


        return resBean;
    }
}
