package day03Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ResponseDemo" , urlPatterns = "/responseDemo")
public class ResponseDemo extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决或处理页面乱码问题.
        //resp.setCharacterEncoding("utf-8");
        //设置页面响应的回浏览器的编码格式，包含了setCharacterEncoding()。所以使用setContentType此方法
        resp.setContentType("text/html;charset=utf-8");
        //操作响应行
        resp.setStatus(500);//500服务器异常
        //操作响应头
        resp.addHeader("name" , "zhangsan");//key值可以重复，相当于出入列表
        resp.addHeader("name" , "wangwu");
        resp.addIntHeader("age" , 18);
        resp.addDateHeader("birthDay",new Date().getTime());
        resp.setHeader("name" , "lisi");//唯一且替换
        //重定向,地址栏的信息会改变，同时窗口内容也会改变
       /* resp.setStatus(302);
        resp.setHeader("Location" , "/home.html");*/
       //此方法是上面方式的一个封装，常用一种重定向方式
        //resp.sendRedirect("/index.html");
        //向页面中写入内容，此时若调用重定向方法，此内容不可见
        resp.getWriter().write("测试向响应体中写入内容");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
