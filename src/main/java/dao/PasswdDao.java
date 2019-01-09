package dao;


import java.beans.PropertyVetoException;
import java.sql.*;


import bean.info.PasswdBean;
import bean.result.FindPasswdResBean;
import config.DataSourceConfiguration;


public class PasswdDao {


	public static void validate(FindPasswdResBean res, String s_id, String email){
		if (s_id == null || "".equals(s_id)){
			res.onS_idRequired();
		}
		if (email == null || "".equals(email)){
			res.onEmailREquired();
		}

	}

	public static String judge(FindPasswdResBean res, String s_id, String email) {
		res.setStatus(200);
		validate(res, s_id, email);
		String password = null;

		if (res.getStatus() == 200){

			String sql = "select password from stu where s_id = ? and email = ?";

			try {
				Connection conn = DataSourceConfiguration.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, s_id);
				pstmt.setString(2, email);

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					res.onPassed("mail." + email.substring(email.indexOf('@') + 1));
					password =  (rs.getString("password"));
				}
				else {
					res.onNotFound();
				}
				rs.close();
				pstmt.close();
				conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}

		return password;
	}
	
}
