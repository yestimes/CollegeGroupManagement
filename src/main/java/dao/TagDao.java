package dao;

import bean.info.OTBean;
import bean.result.TagResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagDao {
    //对数据库的操作函数
    //增加标签
    public static int AddTag(OTBean bean){
        int result = 0;

        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "insert into O_T_INTERACTIVE(o_id,s_id,t_id) values(?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,bean.getO_id());
                pstmt.setString(2,bean.getS_id());
                pstmt.setInt(3,bean.getT_id());
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //判断传入的字符串是否为空
    private static boolean isStringArgsCorrect(String src){
        if(src == null || "".equals(src)){
            return false;
        }
        else {
            return true;
        }
    }

    //判断传来的bean的葛子端是否都不为空，用到了isStringArgsCorrect函数
    private static boolean validate(OTBean bean){
        boolean flag = true;
        if (!isStringArgsCorrect(bean.getS_id())){
            flag = false;
        }
        if(bean.getO_id() == 0){
            flag =  false;
        }
        if (bean.getT_id() == 0){
            flag = false;
        }
        return flag;
    }

    //返回一个resBean结果集
    public static TagResBean AddTagRes(OTBean bean){
        TagResBean resBean = new TagResBean();
        System.out.println("func::add-> t_ud" + bean.getT_id());

        if (validate(bean)){//保证这个bean各字段都不为空
            if (isTidExist(bean.getT_id())){//保证插入的这个标签在标签库中
                if (AddTag(bean) > 0){ //增加操作成功
                    resBean.AddSuccess();
                }
                else {
                    resBean.AddFail();//返回400未找到该记录
                }
            }
            else {
                resBean.NoneTag();
                System.out.println("标签库中没有此标签！");
            }
        }
        else {
            resBean.onArgsInCorrect();//返回403参数错误
            System.out.println("o_id:"+ bean.getO_id());
            System.out.println("s_id:"+ bean.getS_id());
            System.out.println("t_id:"+ bean.getT_id());
        }
        return resBean;
    }

    //查看标签
    public static List<OTBean> OTList(){
        List<OTBean> list = new ArrayList<OTBean>();
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select * from O_T_INTERACTIVE";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    OTBean bean = new OTBean();
                    bean.setO_id(rs.getInt("o_id"));
                    bean.setS_id(rs.getString("s_id"));
                    bean.setT_id(rs.getInt("t_id"));
                    list.add(bean);
                }
                stmt.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    //对逻辑的校验函数
    public static boolean isTidExist(int t_id){

        Connection conn = null;
        try {
            if ((conn = config.DataSourceConfiguration.getConnection()) == null){
                return false;
            }
            String sql = "SELECT COUNT(*) FROM TAG WHERE t_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, t_id);

            ResultSet resultSet = pstmt.executeQuery();
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


}
