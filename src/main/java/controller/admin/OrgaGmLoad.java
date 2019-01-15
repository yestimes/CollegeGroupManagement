package controller.admin;

import bean.result.OrgaGmListResBean;
import utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(value = {"/getOrgaGmInfo"})
public class OrgaGmLoad extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        OrgaGmListResBean resBean = (OrgaGmListResBean)request.getSession().getAttribute("orgaGmInfo");
        try {
            if (resBean == null){
                response.sendError(404, "社团负责人信息不存在");
            }else {
                JsonUtils.returnJson(response, resBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        doPost(request,response);
    }
}
