package controller.orgaComm;

import bean.info.OrgaCommBean;
import bean.result.CommResBean;
import dao.organization.OrgaCommDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = {"/AddOrgaComm"})
public class OrgaCommAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String s_id = (String) request.getSession().getAttribute("s_id");//获得session中的s_id
        String o_id1 = request.getParameter("o_id");//获得隐藏框中的o_id并转成int型
        int o_id = 0;
        if(o_id1 != null && !("".equals(o_id1))){
            o_id = Integer.parseInt(o_id1);
        }
        Date s_o_comment_time = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            s_o_comment_time = df.parse(df.format(new Date()));// new Date()为获取当前系统时间
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s_o_comment = request.getParameter("s_o_comment");

        OrgaCommBean bean = new OrgaCommBean();
        bean.setO_id(o_id);
        bean.setS_id(s_id);
        bean.setS_o_comment(s_o_comment);
        bean.setS_o_comment_time(s_o_comment_time);

        int result = OrgaCommDao.AddOrgaComm(bean);
        CommResBean resBean = new CommResBean();
        if (result > 0){
            System.out.println("Comment Insert Success!");
            resBean.CommSuccess();
        }
        else{
            System.out.println("Comment Insert Fail!");
            resBean.CommFail(o_id,s_id,s_o_comment,s_o_comment_time);
        }

        JsonUtils.returnJson(response, resBean);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
