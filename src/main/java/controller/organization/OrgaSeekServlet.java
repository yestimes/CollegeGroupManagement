package controller.organization;

import bean.info.OrganizationBean;
import bean.result.OrgaResBean;
import dao.organization.OrganizationDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/SeekOrga"})
public class OrgaSeekServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String o_name = request.getParameter("o_name");
        OrganizationDao dao = new OrganizationDao();
        OrganizationBean bean = new OrganizationBean();
        bean = dao.FindOrgaByName(o_name);//获取到这个名字的社团信息

        OrgaResBean resBean = new OrgaResBean();
        if (bean != null){
            System.out.println("该社团信息不为空:get organization----"+o_name);
            resBean.BeanSuccess(bean);
        }
        else{
            System.out.println(o_name+"信息为空");
            resBean.BeanFail();
        }

        try {
            JsonUtils.sendJsonBack(response.getWriter(),resBean);//结果集信息
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
