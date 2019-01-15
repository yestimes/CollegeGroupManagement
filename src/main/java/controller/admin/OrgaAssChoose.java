package controller.admin;


import bean.info.OrganizationBean;
import bean.info.StudentBean;
import dao.organization.OrganizationDao;
import utils.ArgsCheck;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/OrgaPaneChoose"})

public class OrgaAssChoose  extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException {
        StudentBean userInfo = (StudentBean) request.getSession().getAttribute("userInfo");
        String o_id = request.getParameter("o_id");
        String type = request.getParameter("type");

        if (userInfo == null || o_id == null || ArgsCheck.isStringArgsCorrect(o_id) || ArgsCheck.isStringArgsCorrect(type)){
            //没有登陆
            response.sendError(403);
        }else {
            OrganizationBean orgaInfo = OrganizationDao.getOrgaInfo(Integer.parseInt(o_id));
            request.getSession().setAttribute("orgaInfo", orgaInfo);
            response.sendRedirect("/OrgaPane/index.html");
        }
    }
}
