package org.john.config.dao;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.omg.CORBA.PUBLIC_MEMBER;


import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;


public class DataSourceConfiguration {


    private static ComboPooledDataSource dataSource = null;


    private static ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/group_db?characterEncoding=utf8&useSSL=false");
        dataSource.setUser("groupdbu");
        dataSource.setPassword("admin234");
        // 关闭连接后不自动提交
        dataSource.setAutoCommitOnClose(false);

        return dataSource;
    }

    public static   ComboPooledDataSource getDataSource(){
        if (dataSource == null){
            try {
                dataSource = createDataSource();
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            return dataSource;

        }
        else {
            return dataSource;
        }
    }

    public static Connection getConnection() throws PropertyVetoException, SQLException {
        if (dataSource == null){
            dataSource = createDataSource();
            return dataSource.getConnection();
        }
        else {
            return dataSource.getConnection();
        }
    }

}
