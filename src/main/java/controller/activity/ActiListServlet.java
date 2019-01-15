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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ActiListServlet",value = {"/ActiList"})
public class ActiListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        List<ActivityBean> list = new ArrayList<ActivityBean>();
        list = ActivityDao.ActiList();

        ActiResBean resBean = new ActiResBean();
        if (list != null){
            System.out.println("获取所有活动列表成功！");
            resBean.ListSuccess(list);//获得list+status+info
        }
        else{
            System.out.println("获取所有活动列表失败！");
            resBean.ListFail();
        }

        JsonUtils.returnJson(response, resBean);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
