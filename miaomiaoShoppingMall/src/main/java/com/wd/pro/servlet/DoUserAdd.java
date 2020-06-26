package com.wd.pro.servlet;

import com.wd.pro.entity.Users;
import com.wd.pro.service.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: 莫良咚咚咚
 * @Date: 2020/6/23 01:53
 * @Description:
 */
@WebServlet("/manage/admin_douseradd")
public class DoUserAdd extends HttpServlet {
    /**
     * 功能描述: 序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
     *
     * @param: serialVersionUID
     * @return: 
     * @date: 2020/6/23 上午2:03
     * @throws :
     * @author: 莫良咚咚咚
     */
    private static final long serialVersionUID = 4685292301442856770L;
    
    /**
     * 功能描述: 客户端把数据传送到服务器端,隐藏传送给服务器的任何数据
     *
     * @param: doPost
     * @return: null
     * @date: 2020/6/23 上午2:01
     * @throws :
     * @author: 莫良咚咚咚
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集，防止有乱码出现
        request.setCharacterEncoding("utf-8");
        //使客户端浏览器，区分不同种类的数据,并且根据设置的不同的“编码方式（utf-8）”调用浏览器内不同的程序嵌入模块来处理相应的数据
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("userName");
        String name = request.getParameter("name");
        String pwd = request.getParameter("password");
        String mobile = request.getParameter("mobile");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String regist = request.getParameter("register");
        String birth = request.getParameter("birthday");
        String usercode = request.getParameter("usercode");
        String address = request.getParameter("address");

        //创建用户实体类,把数据加到数据库中
        Users users = new Users(username,name,pwd,mobile,sex,email,regist,birth,usercode,address,1);

        System.out.println(users);
        //加入到数据库用户表中
        int count = UserDao.innsert(users);
        System.out.println(count);
        //成功失败的网页重定向
        if (count > 0) {
            response.sendRedirect("admin_douserselect");
        }else {
            PrintWriter out = response.getWriter();

            out.write("<script>");
            out.write("alert('用户添加失败')");
            out.write("location.href='/manage/admin_useradd.jsp'");
            out.write("/<script>");
        }
    }

}
