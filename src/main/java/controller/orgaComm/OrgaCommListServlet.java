package controller.orgaComm;

import bean.info.OrgaCommBean;
import bean.result.OrgaCommListResBean;
import dao.organization.OrgaCommDao;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrgaCommListServlet",value = {"/OrgaCommList"})
public class OrgaCommListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        List<OrgaCommBean> list = new ArrayList<OrgaCommBean>();
        list = OrgaCommDao.OrgaCommList();

        OrgaCommListResBean commListResBean = new OrgaCommListResBean();
        if (list != null){
            System.out.println("Get OrgaCommList Success!");
            commListResBean.OrgaCommListSuccess(list);
        }
        else {
            System.out.println("Get OrgaCommList Fail!");
            commListResBean.OrgaCommListFail();
        }

        JsonUtils.returnJson(response, commListResBean);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
