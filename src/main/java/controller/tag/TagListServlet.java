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
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = {"/TagList"})
public class TagListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OTBean> list = new ArrayList<OTBean>();

        list = TagDao.OTList();

        TagResBean resBean = new TagResBean();
        if (list != null){
            System.out.println("获取所有标签-社团映射成功！");
            resBean.ListSuccess(list);
        }
        else {
            System.out.println("获取所有标签-社团映射失败！");
            resBean.ListFail();
        }

        JsonUtils.returnJson(response, resBean);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
