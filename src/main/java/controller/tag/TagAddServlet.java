package controller.tag;

import bean.info.OTBean;
import bean.result.TagResBean;
import dao.TagDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/AddTag"})
public class TagAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String s_id = (String) request.getSession().getAttribute("s_id");//获得session中的s_id
        String o_id1 = request.getParameter("o_id");
        int o_id = 0;
        if (o_id1 != null && !("".equals(o_id1))){
            o_id = Integer.parseInt(o_id1);//获得社团号
        }
        String t_id1 = request.getParameter("t_id");
        int t_id = 0;
        if (t_id1 != null && !("".equals(t_id1))){
            t_id = Integer.parseInt(t_id1);//获得标签号
        }

        OTBean bean = new OTBean();
        bean.setO_id(o_id);
        bean.setS_id(s_id);
        bean.setT_id(t_id);

        TagResBean resBean = new TagResBean();
        resBean = TagDao.AddTagRes(bean);//已经获得了add反馈信息

        JsonUtils.returnJson(response, resBean);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
