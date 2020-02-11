package day03Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注解的方式配置servlet
 * doGet或doPost方法就是对原来生命周期中的service方法进行的二次封装。
 */
@WebServlet(name = "QuickStartHttp" ,urlPatterns = "/quickStartHttp")
public class QuickStartHttpServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //doPost做统一处理
        System.out.println("doPost is called!!!");
        System.out.println("从servletContext域对象中取值: " + this.getServletContext().getAttribute("name"));

    }

    /**
     * 不管为get还是post方式，进入servlet组件中执行的都是一块代码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }


}
