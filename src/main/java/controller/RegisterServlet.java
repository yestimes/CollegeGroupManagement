package controller;

import bean.dataBuffer.Customer;
import bean.result.RegisterResBean;
import dao.RegisterDao;
import utils.JsonUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = {"/register"})
public class RegisterServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");

		String s_id = request.getParameter("s_id");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String s_name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String sex = request.getParameter("sex");

		String email = request.getParameter("email");
		String c_id = request.getParameter("c_id");


		Customer customer = new Customer();
		customer.setS_id(s_id);
		customer.setPassword(password);
		customer.setPassword2(password2);
		customer.setS_name(s_name);
		customer.setNickname(nickname);
		customer.setSex(sex);
		customer.setEmail(email);
		customer.setC_id(c_id);

		RegisterResBean res = RegisterDao.add(customer);

		//返回
        JsonUtils.sendJsonBack(response.getWriter(), res);
	}
}


