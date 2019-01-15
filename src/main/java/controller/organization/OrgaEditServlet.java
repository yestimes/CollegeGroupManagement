package controller.organization;

import bean.info.OrganizationBean;
import bean.result.OrgaResBean;
import dao.organization.OrganizationDao;
import utils.ArgsCheck;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/EditOrga"})
public class OrgaEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String o_name = request.getParameter("o_name");
        String o_logo = request.getParameter("o_logo");
        String o_description = request.getParameter("o_description");
        String o_ids = request.getParameter("o_id");

        OrganizationBean bean = new OrganizationBean();
        bean.setO_name(o_name);
        bean.setO_logo(o_logo);
        bean.setO_description(o_description);

        if (ArgsCheck.isStringArgsCorrect(o_ids)){
            bean.setO_id(Integer.parseInt(o_ids));
        }
        else {
            response.sendError(404);
        }

        OrgaResBean resBean  = OrganizationDao.EditOrga(bean);
        try {
            response.setContentType("application/json;charset=utf-8");
            JsonUtils.sendJsonBack(response.getWriter(), resBean); //以json的形式传回给js
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
