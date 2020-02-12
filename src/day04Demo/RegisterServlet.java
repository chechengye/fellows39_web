package day04Demo;

import day02Demo.DBCPUtils;
import day04Demo.pojo.User;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(name = "Register" , urlPatterns = "/register")
public class RegisterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getParameterMap()
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        User user = new User();
        //String username = new String(req.getParameter("username").getBytes("iso8859-1" ), "utf-8");
       /* user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        String enjoy = "";
        for(String en : req.getParameterValues("enjoy")){
            enjoy += en + ",";
        }
        user.setEnjoy(enjoy);*/
        try {
            //自动映射封装实体类对象。原则：前端name值，与实体类中的属性一一对应
            BeanUtils.populate(user , req.getParameterMap());
            String enjoy = "";
            for(String en : req.getParameterValues("enjoy")){
                enjoy += en + ",";
            }
            user.setEnjoy(enjoy);
            registerUser(user , req , resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private void registerUser(User user , HttpServletRequest req, HttpServletResponse resp){

        try {
            //实现将user存入数据库DBUtils
            QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());
            int rows = qr.update("insert into t_user values(?,?,?,?,?,?,?)", null, user.getUsername(), user.getPassword(), user.getEmail(), user.getSex(), user.getEnjoy(), user.getCode());
            if(rows > 0){
                System.out.println("添加成功！");
                try {
                    //重定向
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("添加失败!");
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
