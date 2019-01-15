package controller.activity;

import dao.activity.ActivityDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/DeleteActi"})
public class DeleteActiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        int ac_id = 0;
        String ac_id1 = request.getParameter("ac_id");
        if(ac_id1 != null && !("".equals(ac_id1))){
            ac_id = Integer.parseInt(ac_id1); //获取到了要删除的ac_id
        }

        int result = ActivityDao.DeleteActi(ac_id);

        if (result > 0){
            response.sendRedirect("/admin/activity-delete.html");
        }
        else{
            response.sendRedirect("/admin/activity-delete.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
