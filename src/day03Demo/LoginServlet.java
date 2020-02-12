package day03Demo;

import day02Demo.C3P0Utils;
import day02Demo.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet" , urlPatterns = "/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");//解决从前端取过来的请求数据乱码的问题
        System.out.println("username : " + username);
        try {
            QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
            User user = qr.query("select u.username from t_user u where username = ? and password = ?", new BeanHandler<>(User.class), username, password);
            if(null != user){
                //resp.getWriter().write("欢迎,"+ user.getUsername());
                resp.sendRedirect(req.getContextPath() + "/home.html");
            }else{
                //内部转发回login.html页面，讲解req对象时使用
                req.setAttribute("loginInfo" , "用户名或密码错误!");
                req.getRequestDispatcher("/login.jsp").forward(req , resp);
                //resp.getWriter().write("用户名或密码错误!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }
}
