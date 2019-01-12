package controller;


import bean.result.OrgaGmListResBean;
import dao.OrgaGmListDao;
import utils.ArgsCheck;
import utils.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/getOrgaGmList"})
public class OrgaGmListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("-----------------------------------------");
        String o_ids= request.getParameter("o_id");
        OrgaGmListResBean res = null;
        if (ArgsCheck.isStringArgsCorrect(o_ids)){
            //query one
            res = OrgaGmListDao.queryOne(o_ids);
        }
        else {
            //query all
            res = OrgaGmListDao.queryAll();
        }
        try {
            response.setContentType("application/json;charset=utf-8");
            JsonUtils.sendJsonBack(response.getWriter(), res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
