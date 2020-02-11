package day03Demo;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet生命周期
 * 1、第一次访问会调用init和service方法
 * 2、第二次或之后的访问，仅会调用service方法
 * 3、destroy销毁方法,web应用从tomcat中移除或tomcat关闭的时候会访问。
 */
public class QuickStartServlet implements Servlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init running...");
        System.out.println(servletConfig.getInitParameter("url"));
        System.out.println(servletConfig.getServletName());
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service running...");
        servletResponse.setContentType("text/html;charset=utf-8");
        servletResponse.getWriter().write("<html lang=\"en\">");
        servletResponse.getWriter().write("<head>");
        servletResponse.getWriter().write("<meta charset=\"UTF-8\">");
        servletResponse.getWriter().write("<title>商城首页</title>");
        servletResponse.getWriter().write("</head>");

    }
    @Override
    public void destroy() {
        System.out.println("destroy running...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }


}
