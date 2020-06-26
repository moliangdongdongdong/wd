package com.wd.pro.util.servlet;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 李昊哲
 * @version 1.0
 * @date 2020/5/21 下午3:35
 */
public abstract class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = -9087367189544367009L;

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!StringUtils.isEmpty(request.getParameter("methodName"))) {
            // 如果methodName的值不为空，则获取该对象的同名方法
            Method method;
			try {
				method = this.getClass().getDeclaredMethod(request.getParameter("methodName").trim(), HttpServletRequest.class, HttpServletResponse.class);
				// 设置可访问权限
				method.setAccessible(true);
				// 执行方法
				method.invoke(this, request, response);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            // 如果methodName的值为空则回到登录页面
            toLogin(request, response, "您请求的服务开小差了");
        }
    }

    /**
     * 回到登录页面
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param msg      友情提示
     * @throws IOException IOException
     */
    public void toLogin(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException {
        // session失效
        request.getSession().invalidate();
        if (!StringUtils.isEmpty(msg)) {
            System.out.println(msg);
            // 把错误消息存储到session域
            request.getSession().setAttribute("errMsg", msg);
        }
        // 网页重定向到登录页面
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    /**
     * 向页面输出json字符串
     *
     * @param response HttpServletResponse
     * @param json     json字符串
     * @throws IOException IOException
     */
    public void pringJson(HttpServletResponse response, String json) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
    }

    /**
     * 向页面输出json字符串
     *
     * @param response HttpServletResponse
     * @param json     对象
     * @throws IOException IOException
     */
    public void pringJson(HttpServletResponse response, Object json) throws IOException {
        pringJson(response, JSON.toJSONString(json));
    }
}
