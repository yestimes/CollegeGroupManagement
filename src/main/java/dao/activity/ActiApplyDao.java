package dao.activity;

import bean.info.ActiApplyBean;
import bean.result.ActiApplyResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiApplyDao {
    //申请加入活动
    public static int AddApply(ActiApplyBean bean){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("System error!");
            }
            else {
                String sql = "insert into ACTIAPPLY(ac_id,s_id) values(?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,bean.getAc_id());
                pstmt.setString(2,bean.getS_id());
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //受理申请，将该条申请记录的status位置1
    public static int AcceptApply(ActiApplyBean bean){
        int result = 0;
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
                result = -1;
            }
            else {
                String sql = "update ACTIAPPLY set status = 1 where ac_id = ? and s_id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,bean.getAc_id());
                pstmt.setString(2,bean.getS_id());
                result = pstmt.executeUpdate();
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //查看申请List
    public static List<ActiApplyBean> ActiApplyList(){
        List<ActiApplyBean> list = new ArrayList<ActiApplyBean>();
        Connection conn = null;
        try {
            if ((conn = DataSourceConfiguration.getConnection()) == null){
                System.out.println("System error!");
            }
            else {
                String sql = "select * from ACTIAPPLY";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()){
                    ActiApplyBean bean = new ActiApplyBean();
                    bean.setAc_id(rs.getInt("ac_id"));
                    bean.setS_id(rs.getString("s_id"));
                    list.add(bean);
                }
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
    private static boolean validate(ActiApplyBean bean){
        boolean flag = true;
        if (!isStringArgsCorrect(bean.getS_id())){
            flag = false;
        }
        if (bean.getAc_id() == 0){
            flag = false;
        }
        return flag;
    }

    //申请信息返回——待受理
    public static ActiApplyResBean AddApplyRes(ActiApplyBean bean){
        ActiApplyResBean resBean = new ActiApplyResBean();
        if (validate(bean)){//保证这个bean各字段都不为空
            if (AddApply(bean) > 0){ //申请操作成功
                resBean.ActiApplySuccess();
            }else {
                resBean.AcceptApplyFail();//返回400未找到该记录
            }
        }
        else {
            resBean.onArgsInCorrect();//返回403参数错误
            System.out.println("ac_id:"+ bean.getAc_id());
            System.out.println("s_id:"+ bean.getS_id());
        }
        return resBean;
    }

    //受理申请信息返回
    public static ActiApplyResBean AcceptApplyRes(ActiApplyBean bean){
        ActiApplyResBean resBean = new ActiApplyResBean();
        if (validate(bean)){
            if (AcceptApply(bean) > 0){
                resBean.ActiApplySuccess();//受理成功
            }
            else {
                resBean.AcceptApplyFail();//受理失败
            }
        }
        else {
            resBean.onArgsInCorrect();
            System.out.println("ac_id:"+bean.getAc_id());
            System.out.println("s_id:"+bean.getS_id());
        }
        return resBean;
    }


}
