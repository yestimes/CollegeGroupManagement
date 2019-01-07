package controller;



import bean.info.StudentBean;
import bean.result.LoginResBean;
import dao.JsonUtils;
import dao.StudentDao;
import dao.UserPermissionLevel;

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

        response.setContentType("application/json;charset=utf-8");

        StudentBean stuBean = (StudentBean)request.getSession().getAttribute("userInfo");
        LoginResBean resBean = null;
        if (stuBean == null){
            //尚未登陆
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            stuBean = new StudentBean();
            resBean = StudentDao.getUserInfo(stuBean, username, password);

            if (resBean.getStatus() == 200){
                stuBean.setUserType(UserPermissionLevel.getLevel(username));
                request.getSession().setAttribute("userInfo", stuBean);
            }
        }
        else {
            //已经登陆
            resBean = new LoginResBean();
            resBean.onSuccess(stuBean.getNickname());
        }

        try {
            JsonUtils.sendJsonBack(response.getWriter(), resBean);
        } catch (IOException e) {
            e.printStackTrace();
        }






    }



}
