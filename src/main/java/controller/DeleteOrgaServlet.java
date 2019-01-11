package controller;

import bean.result.OrgaLinkListResBean;
import bean.result.OrgaResBean;
import dao.OrganizationDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteOrgaServlet",value = {"/DeleteOrga"})
public class DeleteOrgaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int o_id = 0;
        String o_id1 = request.getParameter("o_id");
        if(o_id1 != null && !("".equals(o_id1))){
            o_id = Integer.parseInt(o_id1); //获取到了要删除的o_id
        }

        int result = OrganizationDao.DeleteOrga(o_id);


        response.sendRedirect("/admin/groupInfo-edit.html");




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
