package controller;

import bean.info.OrganizationBean;
import bean.result.OrgaResBean;
import dao.OrganizationDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddOrgaServlet",value = {"/AddOrga"})
public class AddOrgaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从表单获取的信息
        String o_name = request.getParameter("o_name");
        String o_logo = request.getParameter("o_logo");
        String o_description = request.getParameter("o_description");

        OrganizationBean bean = new OrganizationBean();
        bean.setO_name(o_name);
        bean.setO_logo(o_logo);
        bean.setO_description(o_description);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        String sdf = df.format(new Date());// new Date()为获取当前系统时间

        try {
            bean.setO_create_time(df.parse(sdf)); //格式.parse(String)
        } catch (Exception e) {
            e.printStackTrace();
        }

        //已经准备好了要插入数据库的bean
        OrganizationDao dao = new OrganizationDao();
        int result = dao.AddOrga(bean);

        OrgaResBean resBean = new OrgaResBean();
        //根据result结果设置返回resBean的参数值
        if (result >0){
            System.out.println("Orga Insert Success！");
            resBean.AddSuccess();
        }
        else {
            System.out.println("Orga Insert fail！");
            resBean.AddFail();
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
