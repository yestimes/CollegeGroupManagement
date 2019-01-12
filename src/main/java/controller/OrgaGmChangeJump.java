package controller;

import bean.result.OrgaGmListResBean;
import dao.OrgaGmListDao;
import dao.OrganizationDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = {"/orgaGmWound"})
public class OrgaGmChangeJump extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String o_ids = request.getParameter("o_id");

        OrgaGmListResBean bean = OrgaGmListDao.queryOne(o_ids);//已获得要修改的bean信息
        if (bean.getData().get(0).getO_name() == null && bean.getStatus() == 200){
            bean.getData().get(0).setO_name(OrganizationDao.getOrgaName(Integer.parseInt(o_ids)));
        }

        request.getSession().setAttribute("orgaGmInfo", bean);

        try {
            response.sendRedirect("/admin/groupGm-edit.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
