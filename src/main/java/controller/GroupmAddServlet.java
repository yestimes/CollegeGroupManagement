package controller;

import bean.result.GroupmResBean;
import dao.GroupmDao;
import utils.JsonUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class GroupmAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        String sid = request.getParameter("sid");
        String oid = request.getParameter("oid");
        int o = Integer.parseInt(oid);


        GroupmResBean resBean = GroupmDao.addGroupm(sid, o);


        response.setContentType("application/json;charset=utf-8");

        try {
            JsonUtils.sendJsonBack(response.getWriter(), resBean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
