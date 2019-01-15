package controller.admin;


import bean.result.GroupmResBean;
import dao.GroupmDao;
import utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = {"/editOrgaGm"})
public class OrgaGmChangeServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) {
            doPost(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        String sid = request.getParameter("s_id");
        String o_ids = request.getParameter("o_id");
        int o_id = Integer.parseInt(o_ids);


        GroupmResBean resBean = GroupmDao.UpdateGroupm(sid, o_id);


        response.setContentType("application/json;charset=utf-8");
        try {
            JsonUtils.sendJsonBack(response.getWriter(), resBean);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
