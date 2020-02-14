package day05Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "LastAccessDemo" , urlPatterns = "/lastAccessDemo")
public class LastAccessDemo extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd+hh:mm:ss");//空格是不被cookie认。
        String time = df.format(new Date());
        Cookie cookie = new Cookie("lastTime" , time);
        cookie.setMaxAge(2000*60*10);
        resp.addCookie(cookie);
        String lastTime = null;
        Cookie[] cookies = req.getCookies();
        if(null != cookies && cookies.length > 0){
            for (Cookie c : cookies){
                if(c.getName().equals("lastTime")){
                    lastTime = c.getValue().replace("+" , " ");
                }
            }
        }

        if(null == lastTime){
            resp.getWriter().write("您是第一次访问！");
        }else{
            resp.getWriter().write("您最后访问的时间为:"+ lastTime);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
