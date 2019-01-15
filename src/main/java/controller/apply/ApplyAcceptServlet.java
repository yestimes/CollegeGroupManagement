package controller.apply;

import bean.info.ActiApplyBean;
import bean.result.ActiApplyResBean;
import dao.activity.ActiApplyDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/AcceptApply"})
public class ApplyAcceptServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ac_id1 = request.getParameter("ac_id");
        String s_id = request.getParameter("s_id");
        int ac_id = 0;
        if (ac_id1 != null && !("".equals(ac_id1))){
            ac_id = Integer.parseInt(ac_id1);
        }
        ActiApplyBean bean = new ActiApplyBean();
        bean.setAc_id(ac_id);
        bean.setS_id(s_id);

        ActiApplyResBean resBean = ActiApplyDao.AcceptApplyRes(bean);

        JsonUtils.returnJson(response, resBean);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
