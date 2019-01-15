package controller.organization;

import bean.info.OrganizationBean;
import dao.organization.OrganizationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrgaLoadJumpServlet",value = {"/OrgaEditWould"})
public class OrgaLoadJumpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String o_ids = request.getParameter("o_id");

        OrganizationBean bean = OrganizationDao.LoadOrga(o_ids);//已获得要修改的bean信息
        bean.setO_id(Integer.parseInt(o_ids));

        request.getSession().setAttribute("orgaInfo", bean);

        response.sendRedirect("/admin/group-edit.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
