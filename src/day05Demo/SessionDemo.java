package day05Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "SessionDemo" , urlPatterns = "/sessionDemo")
public class SessionDemo extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取或创建session
        //销毁：
        //1）服务器（非正常）关闭时  。若是正常关闭的时候，服务端会将已存在的session写入磁盘
        //2）session过期/失效（默认30分钟）
        HttpSession session = req.getSession();
        session.setAttribute("name" , "tom");
        String id = session.getId();//获取服务端session的唯一标识
        Cookie cookie = new Cookie("JSESSIONID" , id);
        cookie.setMaxAge(60*10);
        resp.addCookie(cookie);

        if(30 == 60){
            session.invalidate();//手动销毁此session
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
