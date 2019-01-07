package controller;


import bean.info.StudentBean;
import bean.result.LogoutBean;
import org.john.dao.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet(value = {"/userLogout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");

        StudentBean userInfo =  (StudentBean)request.getSession().getAttribute("userInfo");
        LogoutBean res = new LogoutBean();
        if (userInfo == null){
            //没有登陆
            res.onNoLogin();
        }
        else {
            res.onSuccess((StudentBean)request.getSession().getAttribute("userInfo"));
            request.getSession().removeAttribute("userInfo");
        }

        try {
            JsonUtils.sendJsonBack(response.getWriter(), res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
