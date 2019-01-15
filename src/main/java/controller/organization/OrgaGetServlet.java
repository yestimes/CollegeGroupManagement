package controller.organization;

import bean.info.OrganizationBean;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrgaGetServlet", value={"/getGroupConf"})
public class OrgaGetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrganizationBean orgaInfo =(OrganizationBean) request.getSession().getAttribute("orgaInfo"); //取得session中的社团bean

        if (orgaInfo == null){
            response.sendRedirect("/index.html");
        }else {
            try {
                response.setContentType("application/json;charset=utf-8");
                JsonUtils.sendJsonBack(response.getWriter(), orgaInfo); //以json的形式传回给js
            } catch (IOException e) {
                e.printStackTrace();
            }
            request.getSession().removeAttribute("orgaInfo");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
