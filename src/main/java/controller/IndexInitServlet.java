package controller;


import bean.info.StudentBean;
import bean.result.IndexInitBean;
import utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(value = {"/getIndexInitInfo"})

public class IndexInitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        StudentBean userInfo = (StudentBean) request.getSession().getAttribute("userInfo");
        //获取不到信息代表没有登陆
        IndexInitBean res = new IndexInitBean();
        if (userInfo == null) {
            res.onNotLogin();
        }else {
            //登陆后才填充信息
            res.onPassed();
            res.setUserInfo(userInfo);
        }
        try {
            JsonUtils.sendJsonBack(response.getWriter(), res);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
