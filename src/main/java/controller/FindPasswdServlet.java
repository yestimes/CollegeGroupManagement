package controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.info.PasswdBean;
import bean.result.FindPasswdResBean;
import dao.PasswdDao;
import utils.JsonUtils;
import utils.SendMail;



@WebServlet(value = {"/getPasswd"})
//@WebServlet(value = {"/getIndexInitInfo"})
public class FindPasswdServlet extends HttpServlet {




	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
		//获取用户的邮箱
		response.setContentType("application/json;charset=utf-8");

		String s_id = request.getParameter("s_id");
		String email = request.getParameter("email");


		FindPasswdResBean resBean = new FindPasswdResBean();
		String password = PasswdDao.judge(resBean, s_id, email);

		//用来判断是否验证成功
		if (password != null)
		{
			//实例化一个发送邮件的对象
			SendMail mySendMail = new SendMail();
		    //设置收件人和消息内容
			mySendMail.sendMail(email, "大学生社团管理系统提醒，您的密码为："+ password);
		}

		try {
			JsonUtils.sendJsonBack(response.getWriter(), resBean);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
