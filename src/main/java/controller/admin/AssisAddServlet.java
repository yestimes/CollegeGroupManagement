package controller.admin;

import bean.result.GroupmResBean;
import dao.AssisDao;
import utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = {"/AssisAdd"})
public class AssisAddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

       //获得学号跟社团号
        String sid = request.getParameter("sid");
        String oid = request.getParameter("oid");

        //社团号是int型
        int o = Integer.parseInt(oid);


         GroupmResBean resBean = AssisDao.addAssis(sid, o);


        response.setContentType("application/json;charset=utf-8");

        try {
            JsonUtils.sendJsonBack(response.getWriter(), resBean);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
