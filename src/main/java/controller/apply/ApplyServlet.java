package controller.apply;



import bean.result.ApplyResBean;
import dao.ApplyDao;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

public class ApplyServlet extends HttpServlet {

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

//判断学生是申请加入社团还是申请社团助理，然后加入申请表
        ApplyResBean resBean = ApplyDao.Judge(sid,lev,o);


        JsonUtils.returnJson(response, resBean);

    }

}

