package controller.user;



import bean.info.StudentBean;
import bean.result.LoginResBean;
import utils.JsonUtils;
import dao.user.StudentDao;
import dao.user.UserPermissionLevel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = {"/userLogin"})

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
       doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        

        response.setContentType("application/json;charset=utf-8");

        StudentBean stuBean = (StudentBean)request.getSession().getAttribute("userInfo");
        LoginResBean resBean = null;
        if (stuBean == null){
            //尚未登陆
            String s_id = request.getParameter("username");
            String password = request.getParameter("password");
            stuBean = new StudentBean();
            resBean = StudentDao.getUserInfo(stuBean, s_id, password);

            if (resBean.getStatus() == 200){
                stuBean.setUserType(UserPermissionLevel.getLevel(s_id));
                request.getSession().setAttribute("userInfo", stuBean);
            }
        }
        else {
            //已经登陆
            String s_id = request.getParameter("username");
            if (stuBean.getSid().equals(s_id)){
                resBean = new LoginResBean();
                resBean.onSuccess(stuBean.getNickname());
            }else {
                request.getSession().removeAttribute("userInfo");
                resBean.onFail(302, "请重新登陆");
            }
        }
        JsonUtils.returnJson(response, resBean);


    }



}
