package controller;

import bean.info.OrganizationBean;
import bean.result.OrgaResBean;
import dao.OrganizationDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrgaListServlet",value = {"/OrgaList"})
public class OrgaListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        List<OrganizationBean> list =  OrganizationDao.OrgaList();

        OrgaResBean listResBean = new OrgaResBean();
        if (list != null){
            listResBean.ListSuccess(list);
        }
        else{
            listResBean.ListFail();
        }

        try {
            JsonUtils.sendJsonBack(response.getWriter(), listResBean); //以json的形式传回给js
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
