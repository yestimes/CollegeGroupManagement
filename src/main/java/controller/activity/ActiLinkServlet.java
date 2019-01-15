package controller.activity;

import bean.result.ActiLinkListResBean;
import dao.activity.ActiLinkListDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/getActiList"})
public class ActiLinkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        ActiLinkListResBean res = new ActiLinkListResBean();
        res.setData(ActiLinkListDao.getActiLinkList());

        try {
            JsonUtils.sendJsonBack(response.getWriter(), res); //以json的形式传回给js
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
