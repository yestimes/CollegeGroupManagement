package controller.apply;


import bean.result.ApplyResBean;
import dao.ApplyDao;
import utils.JsonUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AgreeAppServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        //level是申请的级别 -1表示退出社团  1表示申请普通成员  2表示申请社团助理
        String level = request.getParameter("level");
        String oid=request.getParameter("o_id");
        String sid =request.getParameter("s_id");

        int lev=Integer.parseInt(level);
        int o=Integer.parseInt(oid);


    //受理学生加入社团或申请社团助理（通过level判断）
        ApplyResBean resBean = ApplyDao.AgreeApp(sid,lev,o);

        JsonUtils.returnJson(response, resBean);


    }



}
