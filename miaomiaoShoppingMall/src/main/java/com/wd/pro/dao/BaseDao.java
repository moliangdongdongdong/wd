package com.wd.pro.dao;


import com.wd.pro.util.jdbc.BaseJdbc;

import java.sql.*;

/**
 * @Auther: 莫良咚咚咚
 * @Date: 2020/6/23 03:15
 * @Description:
 */
public class BaseDao {
    /*//静态代码块加载一次驱动就可以了
    static {
        //加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 功能描述: 连接数据库
     *
     * @throws :
     * @param: getConn
     * @return: Connection
     * @date: 2020/6/23 上午3:17
     * @author: 莫良咚咚咚
     */

    public static Connection getConn() {
        Connection conn = null;
        try {
            BaseJdbc.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 功能描述: 增删改的通用的操作
     *
     * @throws :
     * @param: execuIUD
     * @return: int
     * @date: 2020/6/23 上午3:21
     * @author: 莫良咚咚咚
     */
    public static int execuIUD(String sql, Object[] params) {
        int count = 0;
        Connection conn = null;
        //准备sql语句
        PreparedStatement ps = null;

        try {
            conn = BaseJdbc.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                BaseJdbc.rollbackAndClose();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }


    /**
     * 功能描述: 关闭所有
     *
     * @throws :
     * @param: closeAll
     * @return: null
     * @date: 2020/6/23 上午3:17
     * @author: 莫良咚咚咚
     */
    /*public static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }*/
}
