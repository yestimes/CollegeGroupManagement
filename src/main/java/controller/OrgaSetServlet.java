package controller;

import bean.info.OrganizationBean;
import dao.JsonUtils;
import dao.OrganizationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrgaSetServlet", value = {"/groups"})
public class OrgaSetServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String o_id1 = request.getParameter("o_id");
        int o_id = 0;
        if (o_id1 != null && !("".equals(o_id1))){
            o_id = Integer.parseInt(o_id1);
        }
        OrganizationBean bean = OrganizationDao.getOrgaInfo(o_id); //得到该社团信息
        request.getSession().setAttribute("orgaInfo",bean); //将查到的社团bean存入session

        response.sendRedirect("/group/index.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
