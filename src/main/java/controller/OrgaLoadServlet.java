package controller;

import bean.info.OrganizationBean;
import dao.OrganizationDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrgaLoadServlet",value = {"/LoadOrga"})
public class OrgaLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        OrganizationBean bean = (OrganizationBean)request.getSession().getAttribute("orgaInfo");

        if (bean == null){
            System.out.println("not found orgaInfo");
            response.sendError(404);
        }else {
            try {
                response.setContentType("application/json;charset=utf-8");
                JsonUtils.sendJsonBack(response.getWriter(), bean); //以json的形式传回给js
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
