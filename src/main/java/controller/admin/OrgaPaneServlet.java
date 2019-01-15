package controller.admin;

import bean.info.OrganizationBean;
import bean.info.StudentBean;
import dao.organization.OrgaPaneDao;
import dao.organization.OrganizationDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/OrgaPaneCenter"})
public class OrgaPaneServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException{
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentBean userInfo = (StudentBean)request.getSession().getAttribute("userInfo");
        if (userInfo == null){
                //没有登陆
                response.sendError(403);
        }else {
            int level = userInfo.getUserType();

            //普通学生
            if (level == 0) {
                response.sendError(403);
            }else if (level == 1){
                int[] o_ids = OrgaPaneDao.getAssistO_ids(userInfo.getSid());
                if (o_ids.length > 2){
                    response.sendRedirect("/OrgaPane/redirect.html");
                }else {
                    OrganizationBean orgaInfo = OrganizationDao.getOrgaInfo(o_ids[0]);
                    request.getSession().setAttribute("orgaInfo", orgaInfo);
                    response.sendRedirect("/OrgaPane/index.html");
                }
            }
            else if (level == 2){
                String s_id = userInfo.getSid();
                int o_id = OrgaPaneDao.getOrgaIdOfGm(s_id);

                System.out.println("sid: " +s_id + "," + "o_id " + o_id);
                OrganizationBean orgaInfo = OrganizationDao.getOrgaInfo(o_id);
                request.getSession().setAttribute("orgaInfo", orgaInfo);
                //负责人
                response.sendRedirect("/OrgaPane/index.html");
                //准备json
            }else if (level == 3) {
                response.sendRedirect("/admin/index.html");
            }else {
                response.sendError(403);
            }
        }
    }


}


