package day05Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CookieDemo" , urlPatterns = "/cookieDemo")
public class CookieDemo extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建cookie对象
        Cookie cookie = new Cookie("age" , "100");
        //设定时长，单位为秒
        cookie.setMaxAge(60*10);
        //设定访问路径
        cookie.setPath(req.getContextPath() + "/cookieDemo");
        //存入客户端中,添加cookie，默认时长为0浏览器关闭即消失。任何页面均可以访问到。
        //访问第一遍的时候，是进行存储，看不到。存完之后，第二次访问此页面时才可以看到
        resp.addCookie(cookie);
        //代码清楚此cookie
        /*Cookie cookie1 = new Cookie("age" , "100");
        cookie1.setMaxAge(0);
        cookie1.setPath(req.getContextPath() + "/cookieDemo");
        resp.addCookie(cookie1);*/
        Cookie[] cookies = req.getCookies();
        if(null != cookies){
            for(Cookie c : cookies){
                if(c.getName().equals("age")){
                    resp.getWriter().write(c.getValue());
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
