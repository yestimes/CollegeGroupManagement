package dao;



import bean.result.ApplyResBean;
import config.DataSourceConfiguration;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplyDao {

    public static ApplyResBean Judge(String sid, int level, int oid){
        ApplyResBean resBean = new ApplyResBean();

        int status=0;//0：待审核 1：审核中 200：成功  -1：失败
        //退出社团
        if (level==-1){
            deleMember(sid);//删除成员表中学号为sid的成员

            resBean.onApplySuccess();
        }
        //申请成员
        else if (level==1) {
            ApplyOp(sid,oid,level,status);//加入申请表
            resBean.onStatus(0,"待审核");

        }
        //申请社团助理
        else if(level==2){

            ApplyOp(sid,oid,level,status);
            resBean.onStatus(0,"待审核");
        }
        else{
          resBean.onSystemError();
        }



        return resBean;


    }

//退出社团操作
    public static void deleMember(String sid){

        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            String sql="delete from member where s_id='"+sid+"'" ;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }

//加入申请表（申请成员或申请社团助理）
    public static void ApplyOp(String sid,int oid,int level,int status){

        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            String sql="insert into apply(s_id,o_id,level,status) values(?,?,?,?)" ;
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, sid);
            pstmt.setInt(2, oid);
            pstmt.setInt(3, level);
            pstmt.setInt(4, status);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }


    public static ApplyResBean AgreeApp(String sid,int level,int oid){
        ApplyResBean resBean = new ApplyResBean();

        int status=0;

        if (level==1) {
            InsertMember(sid,oid);//添加成员
            deleApply(sid);
            resBean.onApplySuccess();

        }
        //申请社团助理
        else if(level==2){

            try {
                Connection conn = null;
                if ((conn = DataSourceConfiguration.getConnection()) == null) {
                    System.out.println("datasource error");
                }
                String sql="select * from assistant where s_id='"+sid+"'" ;

                PreparedStatement pstmt = conn.prepareStatement(sql);


               int res= pstmt.executeUpdate();

               if(res>1){ //表示该用户已经担任了两个社团助理
                   resBean.onStatus(-1,"该用户已经担任了两个社团助理");
               }

               else{
                   //添加社团助理
                   InsertAssis(sid,oid);
                   //删除申请表中的数据
                   deleApply(sid);
                   resBean.onApplySuccess();

               }
                pstmt.close();
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }

        }
        else{
            resBean.onSystemError();
        }



        return resBean;


    }

    public static void InsertMember(String sid,int oid){

        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            String sql="insert into assistant(s_id,o_id) values(?,?)" ;
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, sid);
            pstmt.setInt(2, oid);


            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }

    //添加社团助理
    public static void InsertAssis(String sid,int oid){

        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            String sql="insert into member(s_id,o_id) values(?,?)" ;
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, sid);
            pstmt.setInt(2, oid);


            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }



//删除申请表中的数据
    public static void deleApply(String sid){

        try {
            Connection conn = null;
            if ((conn = DataSourceConfiguration.getConnection()) == null) {
                System.out.println("datasource error");
            }
            String sql="delete from apply where s_id='"+sid+"'" ;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }


}
