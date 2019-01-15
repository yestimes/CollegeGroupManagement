package controller.activity;

import bean.dataBuffer.ActivityBean;
import bean.result.ActiResBean;
import dao.activity.ActivityDao;
import utils.ArgsCheck;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(value = {"/EditActi"})
public class ActiEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        //从表单获取的信息
        String ac_name = request.getParameter("ac_name");
        String ac_description = request.getParameter("ac_description");
        String o_id1 = request.getParameter("o_id");
        String ac_ids = request.getParameter("ac_id");

        int o_id = 0;
        if (o_id1 != null && !("".equals(o_id1))){
            o_id = Integer.parseInt(o_id1);
        }
        String ac_start_time = request.getParameter("ac_start_time");
        String ac_end_time = request.getParameter("ac_end_time");

        ActivityBean bean = new ActivityBean();
        bean.setAc_name(ac_name);
        bean.setAc_description(ac_description);
        bean.setO_id(o_id);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            bean.setAc_start_time(ac_start_time); //格式.parse(String)
            bean.setAc_end_time(ac_end_time);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ArgsCheck.isStringArgsCorrect(ac_ids)){
            bean.setAc_id(Integer.parseInt(ac_ids));//确认有这个oid才行
        }
        else {
            response.sendError(404);
        }

        ActiResBean resBean = ActivityDao.EditRes(bean);

        JsonUtils.returnJson(response, resBean);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
