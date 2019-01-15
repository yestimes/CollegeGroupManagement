package controller.orgaComm;



import dao.organization.OrgaCommDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/DeleteOrgaComm"})
public class DeleteOrgaCommServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String index = request.getParameter("s_o_index");
        int s_o_index = 0;
        if (index != null && !("".equals(index))){
            s_o_index = Integer.parseInt(index);
        }

        int result = OrgaCommDao.DeleteOrgaComm(s_o_index);
        //不管删除成功与否，都重定向到删除html
        if (result > 0){
            response.sendRedirect("/admin/CommList.html");
        }
        else{
            response.sendRedirect("/admin/CommList.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
