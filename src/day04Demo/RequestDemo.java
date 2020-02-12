package day04Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "RequestDemo" , urlPatterns = "/requestDemo")
public class RequestDemo extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决编码问题
        resp.setContentType("text/html;charset=utf-8");
        //解决前端表单传递过来的数据乱码的问题
        req.setCharacterEncoding("utf-8");
        //获取请求行中的内容
        System.out.println(req.getMethod());
        //获取项目路径
        System.out.println(req.getRequestURI());
        //获取请求路径
        System.out.println(req.getRequestURL());
        //获取web应用路径。偏重要，当你使用重定向方式或者前端跳转接口的方式的时候，加上此路径
        System.out.println(req.getContextPath());
        //重定向操作
        //resp.sendRedirect(req.getContextPath() + "/login");
        //获取get方式请求参数，split("&")，不建议使用
        System.out.println(req.getQueryString());
        //获取远程客户端IP地址，管理系统，您的IP地址为：127.0.0.1 。管理系统的日志
        System.out.println(req.getRemoteAddr());//其它获取访问端信息的api已经被淘汰了
        System.out.println("---------------------------");
        //获取头的信息
        System.out.println("User-Agent : " + req.getHeader("User-Agent"));
        //获取所有头的名称
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()){
            System.out.println(headerNames.nextElement());
        }
        //必须有来源的时候才会出现此header
       /* System.out.println("referer:" + req.getHeader("referer"));
        String referer = req.getHeader("referer");
        if(null != referer && referer.startsWith("http://localhost:81")){
            resp.getWriter().write("今日增长数为0...");
        }else{
            resp.getWriter().write("你是盗链者，不允许!");
        }*/
        System.out.println("----------------------------");
        //获取请求体中的内容
        System.out.println("username : " + req.getParameter("username"));
       /* String[] enjoys = req.getParameterValues("enjoy");
        for(String str : enjoys){
            System.out.println(str);
        }*/
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            System.out.println(parameterNames.nextElement());
        }
        //键为字符串类型，值为字符串数组。BeanUtils.populate(实体类(bean) , map)
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for(Map.Entry<String, String[]> e : entries){
            for(String val : e.getValue()){
                System.out.println(e.getKey() + "->" + val);
            }
        }
        System.out.println("--------------测试从request域中取值------------------");
        System.out.println(req.getAttribute("key"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req , resp);
    }


}
