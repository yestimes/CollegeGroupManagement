package controller.activity;

import bean.dataBuffer.ActivityBean;
import bean.result.ActiResBean;
import dao.activity.ActivityDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/AddActi"})
public class ActiAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从表单获取的信息
        String ac_name = request.getParameter("ac_name");
        String ac_description = request.getParameter("ac_description");
        String o_ids = request.getParameter("o_id");
        String ac_limits = request.getParameter("ac_limit");

        String ac_start_time = request.getParameter("ac_start_time");
        String ac_end_time = request.getParameter("ac_end_time");


        ActivityBean bean = new ActivityBean();

        bean.setAc_start_time(ac_start_time);
        bean.setAc_end_time(ac_end_time);
        bean.setAc_name(ac_name);
        bean.setAc_description(ac_description);
        bean.setO_id(o_ids);


        int result = ActivityDao.AddActi(bean);

        ActiResBean resBean = new ActiResBean();
        if (result > 0){
            System.out.println("Acti Insert Success！");
            resBean.AddSuccess();
        }
        else {
            System.out.println("Acti Insert fail！");
            resBean.AddFail();
        }

        JsonUtils.returnJson(response, resBean);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
