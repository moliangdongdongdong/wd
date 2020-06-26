<%--
  Created by IntelliJ IDEA.
  User: kgc
  Date: 2020/6/25
  Time: 下午7:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="admin_menu.jsp" %>
<!--/sidebar-->
<div class="main-wrap">

    <div class="crumb-wrap">
        <div class="crumb-list">
            <i class="icon-font"></i>
            <a href="${pageContext.request.contextPath}/manage/admin_index.jsp">首页</a>
            <span class="crumb-step">&gt;</span>
            <a class="crumb-name" href="${pageContext.request.contextPath}/manage/admin_douserselect">用户管理</a>
            <span class="crumb-step">&gt;</span>
            <span>修改用户</span>
        </div>
    </div>
    <div class="result-wrap">
        <div class="result-content">
            <form action="${pageContext.request.contextPath}/manage/admin_douserupdate" method="post" id="myform"
                  name="myform">
                <table class="insert-tab" width="100%">
                    <tbody>

                    <tr>
                        <th><i class="require-red">*</i>用户昵称：</th>
                        <td>
                            <input class="common-text required" id="title" name="userName" size="50" value="${user.user_id}"
                                   type="text">
                        </td>
                    </tr>

                    <tr>
                        <th><i class="require-red">*</i>用户姓名</th>
                        <td>
                            <input class="common-text required" id="title" name="name" size="50" value="${user.user_name}" type="text">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>登录密码</th>
                        <td>
                            <input class="common-text required" id="title" name="password" size="50" value="${user.user_password}"
                                   type="text">
                        </td>
                    </tr>

                    <tr>
                        <th>姓别</th>
                        <td>
                            <input type="radio" name="sex" value="T" ${user.user_sex=='T'?"checked":""} >男
                            <input type="radio" name="sex" value="F" ${user.user_sex=='F'?"checked":""}>女
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>手机号码</th>
                        <td>
                            <input class="common-text required" id="title" name="mobile" size="50" value="${user.user_mobile}" type="text">
                        </td>
                    </tr>
                    <tr>
                        <th><i class="require-red">*</i>电子邮箱</th>
                        <td>
                            <input class="common-text required" id="title" name="email" size="50" value="${user.user_email}" type="text">
                        </td>
                    </tr>

                    <tr>
                        <th>出生日期</th>
                        <td><input class="common-text require" id="title" name="birthday" size="50" value="${user.user_birthday}"
                                   type="text"></td>
                    </tr>
                    <tr>
                        <th>注册日期</th>
                        <td><input class="common-text require" id="title" name="register" size="50" value="${user.register_time}"
                                   type="text"></td>
                    </tr>

                    <tr>
                        <th><i class="require-red">*</i>送货地址</th>
                        <td>
                            <input class="common-text required" id="title" name="address" size="50" value="${user.user_address}"
                                   type="text">
                        </td>
                    </tr>

                    <tr>
                        <th>其他</th>
                        <td><input class="common-text require" id="title" name="usercode" size="50" value="${user.user_idenity_code}"
                                   type="text"></td>
                    </tr>

                    <tr>
                        <th></th>
                        <td>
                            <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                            <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

</div>
<!--/main-->
</div>
</body>
</html>

