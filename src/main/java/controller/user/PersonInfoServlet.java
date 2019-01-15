package controller.user;

import bean.info.StudentBean;
import bean.result.PersonResBean;
import utils.JsonUtils;
import dao.user.PersonInfoDao;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(value = {"/PersonInfo"})
public class PersonInfoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)	               {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)

        {

       StudentBean userInfo =  (StudentBean)request.getSession().getAttribute("userInfo");
       PersonResBean resBean = null;
       if (userInfo ==  null)
       {
           try {
               response.sendRedirect("/login.html");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else {
           //获得session中保存的学号和昵称
           String sid = userInfo.getSid();
           String nickname= userInfo.getNickname();
           //获得表单数据


           String classroom = request.getParameter("classroom");
           System.out.println(" ----------->>>>>>>>>>>>"+ classroom);
           String photo = request.getParameter("photo");
           String usersign = request.getParameter("usersign");



           resBean = PersonInfoDao.getPersonInfo(sid,nickname,classroom,photo,usersign);

       }




        //不管昵称有没有改变都重新保存
       // userInfo.setNickname(nickname);

         JsonUtils.returnJson(response, resBean);
    }
}




