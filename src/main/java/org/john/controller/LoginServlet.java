package org.john.controller;



import org.john.bean.result.LoginResBean;
import org.john.bean.info.StudentBean;
import org.john.dao.JsonUtils;
import org.john.dao.StudentDao;
import org.john.dao.UserPermissionLevel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
       doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        StudentBean stuBean = new StudentBean();
        LoginResBean resBean = StudentDao.getUserInfo(stuBean, username, password);

        response.setContentType("text/json;charset=utf-8");

        try {
            JsonUtils.sendJsonBack(response.getWriter(), resBean);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (resBean.getStatus() == 200){
            stuBean.setUserType(UserPermissionLevel.getLevel(username));
            request.getSession().setAttribute("userInfo", stuBean);
        }




    }



}
