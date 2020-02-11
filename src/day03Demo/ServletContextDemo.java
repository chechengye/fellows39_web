package day03Demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletContextDemo" , urlPatterns = "/servletContextDemo")
public class ServletContextDemo extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取servlet上下文对象
        ServletContext servletContext = this.getServletContext();
        System.out.println(servletContext.getInitParameter("contextConfig"));
        System.out.println("----------------------------");
        System.out.println(servletContext.getRealPath("WEB-INF/test1.html"));
        System.out.println("----------------------------");
        servletContext.setAttribute("name","lucy");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
