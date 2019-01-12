package dao;

import bean.dataBuffer.Customer;
import bean.result.RegisterResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao {

    public static boolean isExists(String s_id){
        String sql = "SELECT COUNT(*) FROM stu WHERE s_id = ?";
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                return false;
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s_id);

            ResultSet resultSet = pstmt.executeQuery();
            pstmt.close();
            conn.close();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private static RegisterResBean validate(Customer c){
        
        
        RegisterResBean resBean = new RegisterResBean();
        //设置初始状态，任意参数错误将导致不进入数据库操作
        resBean.setStatus(200);

        if ("".equals(c.getS_id()) || c.getS_id() == null) {
            //out.print("学号不能为空！");
            resBean.onSidRequired();
        }

        if ("".equals(c.getEmail()) || c.getEmail() == null) {
            //out.print("邮箱不能为空！");
            resBean.onEmailRequired();
        } else if (c.getEmail().indexOf('@') < 0 || c.getEmail().length() < 4) {
            //out.print("邮箱格式不正确");
            resBean.onInvaildEmail();
        }

        if ("".equals(c.getPassword()) || c.getPassword() == null) {
            //out.print("密码不能为空");
            resBean.onPasswordRequired();
        } else if ("".equals(c.getPassword2()) || c.getPassword2() == null) {
            //out.print("确定密码不能为空");
            resBean.onPassword2Required();
        }else if (!c.getPassword().equals(c.getPassword2())){
            resBean.onPasswordNotSame();
        }


        if (c.getSex() == null || "".equals(c.getSex())) {
            //out.print("请选择性别！");
            resBean.onSexNotChoose();
        }

        if (c.getC_id() == null ||  "".equals(c.getC_id())) {
            //out.print("院系号不能为空！");
            resBean.onCollegeSelectNone();
        }

        if (c.getS_name() == null || "".equals(c.getS_name())) {
            //out.print("真实姓名不能为空");
            resBean.onRealnameRequired();
        }
        if (c.getNickname() == null || "".equals(c.getNickname())) {
            //out.print("昵称不能为空");
            resBean.onNicknameRequired();
        }

        return resBean;

    }

    private static int insert(Customer c){

        // TODO Auto-generated method stub
        String sql = "insert into stu(s_id, password, s_name, nickname, sex, email, c_id)  values(?,?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = DataSourceConfiguration.getConnection();

            PreparedStatement pst =  conn.prepareStatement(sql);
            pst.setString(1, c.getS_id());
            pst.setString(2, c.getPassword());
            pst.setString(3, c.getS_name());
            pst.setString(4, c.getNickname());
            pst.setInt(5, Integer.parseInt(c.getSex()));
            pst.setString(6, c.getEmail());
            pst.setString(7, c.getC_id());

            //执行后都会返回一个数据行，这个result就是来接收这个影响了多少行数据。

            int res =   pst.executeUpdate();
            pst.close();
            conn.close();

            return res;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return -1;
    }
    

	public static RegisterResBean add(Customer c) {

        RegisterResBean resBean = null;
        if ((resBean =  RegisterDao.validate(c)) == null){
            resBean = new RegisterResBean();
            resBean.onServerInternalException();
        }
        else if(isExists(c.getS_id())){
            resBean.onExists();
        }
        else if(resBean.getStatus() == 200){
            if (insert(c) > 0){
                resBean.onRegistPassed();
            }
        }

       return resBean;
	}
}