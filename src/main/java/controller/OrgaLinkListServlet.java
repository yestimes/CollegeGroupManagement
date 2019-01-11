package controller;

import bean.info.OrganizationBean;
import bean.result.OrgaLinkListResBean;
import bean.result.OrgaResBean;
import dao.OrgaLinkListDao;
import dao.OrganizationDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = {"/getOrgaList"})
public class OrgaLinkListServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        OrgaLinkListResBean res = new OrgaLinkListResBean();
        res.setData(OrgaLinkListDao.getOrgaLinkList());

        try {
            JsonUtils.sendJsonBack(response.getWriter(), res); //以json的形式传回给js
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
