package controller.organization;



import bean.info.OrganizationBean;
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
                //以json的形式传回给js
                JsonUtils.returnJson(response, bean);
                request.getSession().removeAttribute("orgaInfo");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
