package controller.admin;


import bean.info.OrganizationBean;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = {"/OrgaPaneInit"})
public class OrgaPaneInitServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrganizationBean orgaInfo = (OrganizationBean)request.getSession().getAttribute("orgaInfo");
        if (orgaInfo == null){
            response.sendError(403);
        }else {
            JsonUtils.returnJson(response, orgaInfo);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }




}
