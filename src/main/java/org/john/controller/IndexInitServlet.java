package org.john.controller;

import org.john.bean.info.StudentBean;
import org.john.bean.result.IndexInitBean;
import org.john.dao.JsonUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexInitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/json;charset=utf-8");
        StudentBean userInfo = (StudentBean) request.getSession().getAttribute("userInfo");
        //获取不到信息代表没有登陆
        IndexInitBean res = new IndexInitBean();
        if (userInfo == null) {
            System.out.println("null");
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
