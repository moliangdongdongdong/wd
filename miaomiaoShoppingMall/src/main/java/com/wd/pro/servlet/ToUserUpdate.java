package com.wd.pro.servlet;

import com.wd.pro.entity.Users;
import com.wd.pro.service.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: 莫良咚咚咚
 * @Date: 2020/6/25 19:32
 * @Description:
 */
@WebServlet("/manage/admin_touserupdate")
public class ToUserUpdate extends HttpServlet {

    /**
     * 功能描述: 序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
     *
     * @param: serialVersionUID
     * @return:
     * @date: 2020/6/25 下午7:33
     * @throws :
     * @author: 莫良咚咚咚
     */
    private static final long serialVersionUID = 4076985914066671551L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集，防止有乱码出现
        request.setCharacterEncoding("utf-8");
        //使客户端浏览器，区分不同种类的数据,并且根据设置的不同的“编码方式（utf-8）”调用浏览器内不同的程序嵌入模块来处理相应的数据
        response.setContentType("text/html;charset=utf-8");


        String id = request.getParameter("id");

        //通过id到数据库中查找
        Users user = UserDao.selectById(id);

        //用户发送到请求域里
        request.setAttribute("user",user);

        //将分页数据传过去，修改成功后回到cpage页码
        request.setAttribute("cpage",request.getParameter("cpage"));

        //网页重定向，跳转到admin_usermodify.jsp页面，向admin_user.jsp中传入EL表达式
        request.getRequestDispatcher("admin_usermodify.jsp").forward(request, response);
    }
}
