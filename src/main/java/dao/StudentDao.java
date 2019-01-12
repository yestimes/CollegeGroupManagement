package dao;

import bean.info.StudentBean;
import bean.result.LoginResBean;
import bean.result.StuInfoResBean;
import config.DataSourceConfiguration;
import utils.ArgsCheck;


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

    private static String queryByName(String s_name){
        String res = null;
        try {
            Connection conn = DataSourceConfiguration.getConnection();
            String sql ="SELECT s_id FROM stu WHERE s_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s_name);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()){
                res = resultSet.getString("s_id");
            }else {
                res =  "";
            }
            resultSet.close();
            pstmt.close();
            conn.close();

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static String queryByS_id(String s_id){
        String res = null;
        try {
            Connection conn = DataSourceConfiguration.getConnection();
            String sql ="SELECT s_id FROM stu WHERE s_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s_id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()){
                res =  resultSet.getString("s_name");
            }else {
                res =  "";
            }
            pstmt.close();
            conn.close();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    //查询学生姓名或者学号
    public static StuInfoResBean getStuInfo(String s_id, String s_name){

        StuInfoResBean resBean = new StuInfoResBean();
        //没有给学号就查名字
        if (!ArgsCheck.isStringArgsCorrect(s_id)){
            //名字都没有就gg
            if (!ArgsCheck.isStringArgsCorrect(s_name)){
                resBean.onArgsInValidate();
            }else {
                //查学号
                String dest_id = queryByName(s_name);
                if (dest_id == null){
                    resBean.onSysemException();
                }else if (dest_id.length() == 0){
                    resBean.onNotFound();
                }else{
                    resBean.onSuccess(dest_id, s_name);
                }
            }
        }else {
            //查姓名
            String dest_name = queryByS_id(s_id);
            if (dest_name == null){
                resBean.onSysemException();
            }else if (dest_name.length() == 0){
                resBean.onNotFound();
            }else {
                resBean.onSuccess(s_id, dest_name);
            }
        }

        return resBean;
    }
}
