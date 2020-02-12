package day04Demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试内部转发与request域对象
 */
@WebServlet(name = "RequestServletOne" , urlPatterns = "/requestServletOne")
public class RequestServletOne extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试向域中存值，request域中的内容只会存在于一次内部转发过程中
        req.setAttribute("key" , "123400");
        //内部转发
        req.getRequestDispatcher("/requestServletTwo").forward(req , resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
