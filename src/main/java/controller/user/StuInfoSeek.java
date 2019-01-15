package controller.user;

import bean.result.StuInfoResBean;
import dao.user.StudentDao;
import utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = {"/stuInfoSeek"})
public class StuInfoSeek extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String s_ids = request.getParameter("s_id");
        String s_name= request.getParameter("s_name");

        StuInfoResBean res = StudentDao.getStuInfo(s_ids, s_name);

        JsonUtils.returnJson(response, res);
    }

}
