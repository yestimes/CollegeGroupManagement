package controller.activity;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = {"/ActiApplyList"})
public class ActiApplyListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        List<ActiApplyBean> list = new ArrayList<ActiApplyBean>();
        list = ActiApplyDao.ActiApplyList();

        ActiApplyResBean resBean = new ActiApplyResBean();
        if (list != null){
            resBean.ApplyListSuccess(list);
            System.out.println("Get Activity Apply List Success!");
        }
        else {
            resBean.ApplyListFail();
            System.out.println("Get Activity Apply List Fail!");
        }

        try {
            JsonUtils.sendJsonBack(response.getWriter(), resBean); //以json的形式传回给js
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
