package com.wd.pro.servlet;

import com.wd.pro.entity.Users;
import com.wd.pro.service.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther: 莫良咚咚咚
 * @Date: 2020/6/24 14:59
 * @Description:
 */
@WebServlet("/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {
    /**
     * 功能描述: 序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
     *
     * @param: serialVersionUID
     * @return:
     * @date: 2020/6/24 下午2:59
     * @throws :
     * @author: 莫良咚咚咚
     */
    private static final long serialVersionUID = -4278138112755282295L;

    /**
     * 功能描述: 客户端把数据传送到服务器端,不会隐藏传送给服务器的任何数据
     *
     * @throws :ServletException, IOException
     * @param: doGet  通过Get方法查询用户
     * @return: null
     * @date: 2020/6/24 下午3:00
     * @author: 莫良咚咚咚
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当前页,默认为第一页
        int cpage = 1;
        //每页显示的数量
        int count = 5;

        //获取用户指定的页面
        String cp = request.getParameter("cp");
        //转化成整数，页数都是整数的
        if(null != cp){
            cpage = Integer.parseInt(cp);
        }

        //接收用户搜所的关键字
        String keyword = request.getParameter("keywords");

        //arr[0]指总用户数    arr[1]总页数
        int arr[] = UserDao.totalPage(count,keyword);

        //获取所有用户的记录
        ArrayList<Users> list = UserDao.SelectAllUser(cpage, count, keyword);
        //System.out.println(list);
        //用户发送到请求域里
        request.setAttribute("userlist", list);
        //总用户数发送到请求域里
        request.setAttribute("tsum",arr[0]);
        //总页数发送到请求域里  tpage总页数
        request.setAttribute("tpage",arr[1]);
        //当前页数发送到请求域里
        request.setAttribute("cpage",cpage);


        //判断输入的关键字keyword是不是存在，存在的话，就向admin_user.jsp中传入EL表达式，让页码停留在关键字查询的页码
        if (keyword != null) {
            request.setAttribute("searchParams", "&keywords=" + keyword);
        }


        //网页重定向，跳转到admin_user.jsp页面，向admin_user.jsp中传入EL表达式
        request.getRequestDispatcher("admin_user.jsp").forward(request, response);
    }
}
