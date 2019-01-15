package controller;

import bean.info.StudentBean;
import bean.result.ActivityStreamResBean;
import dao.suggest.ActivityStreamDao;
import utils.ArgsCheck;
import utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/getActiSuggest"})
public class ActivityStreamServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String isSuggest = request.getParameter("isSuggest");
        StudentBean userInfo = (StudentBean)request.getSession().getAttribute("userInfo");

        ActivityStreamResBean  streamResBean = null;
        if (userInfo == null){
            response.sendRedirect("/login.html");
        }else {
            if (!ArgsCheck.isStringArgsCorrect(isSuggest)){
                //不推荐
            }else {
                //推荐
            }

            streamResBean = ActivityStreamDao.getActivityInterst(userInfo.getSid());
            if (streamResBean != null){
                streamResBean.onSuccess();
            }

            JsonUtils.returnJson(response, streamResBean);

        }






    }
}
