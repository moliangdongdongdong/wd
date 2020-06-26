package com.wd.pro.service;

import java.lang.reflect.Field;
import java.sql.*;

import com.wd.pro.dao.BaseDao;
import com.wd.pro.entity.Users;
import com.wd.pro.util.jdbc.BaseJdbc;

import java.util.ArrayList;

/**
 * @Auther: 莫良咚咚咚
 * @Date: 2020/6/23 05:00
 * @Description:
 */
public class UserDao {
    /**
     * 功能描述: 向数据库中插入用户
     *
     * @throws :
     * @param: Users  用户类
     * @return: count
     * @date: 2020/6/24 下午3:08
     * @author: 莫良咚咚咚
     */
    public static int innsert(Users users) {
        //准备sql语句，插入用户
        String sql = "insert into users values(?,?,?,?,?,?, DATE_FORMAT(?, '%Y-%m-%d'), DATE_FORMAT(?, '%Y-%m-%d') ,?,?,?) ";

        Object[] params = {
                users.getUser_id(),
                users.getUser_name(),
                users.getUser_password(),
                users.getUser_mobile(),
                users.getUser_sex(),
                users.getUser_email(),
                users.getRegister_time(),
                users.getUser_birthday(),
                users.getUser_idenity_code(),
                users.getUser_address(),
                users.getUser_status()
        };
        return BaseDao.execuIUD(sql, params);
    }

    /**
     * 功能描述: 查询所有的用户
     *
     * @throws :
     * @param:
     * @return:
     * @date: 2020/6/24 下午3:11
     * @author: 莫良咚咚咚
     */
    public static ArrayList<Users> SelectAllUser(int cpage, int count, String keyword) {
        ArrayList<Users> list = new ArrayList<Users>();
        //建立连接
        Connection conn = null;
        PreparedStatement pst = null;
        //获取结果集
        ResultSet rs = null;
        try {
            //准备sql语句
            String sql = "";
            //判断用户有没有使用搜索功能，也就是keyword==null？
            if (keyword != null) {
                conn = BaseJdbc.getConnection();
                //准备sql语句，查询所有用户
                //sql = "SELECT * FROM users WHERE (user_name LIKE concat('%',?,'%')) OR (user_id LIKE concat('%',?,'%')) OR (user_mobile LIKE concat('%',?,'%')) ORDER BY user_birthday DESC LIMIT ?,? ";
                sql = "select * from users where user_id like ? order by register_time desc limit ?, ?";
                //sql = "select * from users where user_id like concat('%',?,'%') order by register_time desc limit ?, ?";

                pst = conn.prepareStatement(sql);
                //用户输入了关键字，设置关键字，以及限制页数
                pst.setString(1, "%" + keyword + "%");
                //pst.setString(1, keyword);
                //通过从哪一页到哪一页结束，每页查多少个
                pst.setInt(2, (cpage - 1) * count);
                pst.setInt(3, count);

            } else {
                conn = BaseJdbc.getConnection();
                //准备sql语句，查询所有用户
                sql = "select * from users order by register_time desc limit ?, ?";
                pst = conn.prepareStatement(sql);
                //用户没有输入关键字，就设置限制页数
                //通过从哪一页到哪一页结束，每页查多少个
                pst.setInt(1, (cpage - 1) * count);
                pst.setInt(2, count);
            }


            //获取结果集，循环找到用户
            rs = pst.executeQuery();
            while (rs.next()) {
                Users users = new Users(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_mobile"),
                        rs.getString("user_sex"),
                        rs.getString("user_email"),
                        rs.getString("register_time"),
                        rs.getString("user_birthday"),
                        rs.getString("user_idenity_code"),
                        rs.getString("user_address"),
                        rs.getInt("user_status")
                );
                list.add(users);
            }

            //获取结果集的元数据
            /*ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
             while(rs.next()){
                 Users users = new Users();
                 for (int i = 0; i < columnCount; i++) {
                     //获取没一列的列值,通过ResultSet
                     Object columnValue = rs.getObject(i+1);
                     //获取列的名称，通过ResultSetMetaData
                     String columnLabel = rsmd.getColumnLabel(i + 1);
                     //通过反射，将指定列的名称赋值给columnLabel
                     Field field = Users.class.getDeclaredField(columnLabel);
                     field.setAccessible(true);
                     field.set(users, columnLabel);
                 }
                 return list;
             }*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                BaseJdbc.rollbackAndClose();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 功能描述: 通过id查找用户
     *
     * @throws :
     * @param:
     * @return:
     * @date: 2020/6/25 下午7:46
     * @author: 莫良咚咚咚
     */
    public static Users selectById(String id) {
        Users users = null;
        //建立连接
        Connection conn = null;
        PreparedStatement pst = null;
        //获取结果集
        ResultSet rs = null;
        try {
            conn = BaseJdbc.getConnection();
            //准备sql语句
            String sql = "select * from users where user_id = ?  order by register_time desc";
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);

            //获取结果集，循环找到用户
            rs = pst.executeQuery();
            while (rs.next()) {
                users = new Users(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_mobile"),
                        rs.getString("user_sex"),
                        rs.getString("user_email"),
                        rs.getString("register_time"),
                        rs.getString("user_birthday"),
                        rs.getString("user_idenity_code"),
                        rs.getString("user_address"),
                        rs.getInt("user_status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                BaseJdbc.rollbackAndClose();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    /**
     * 功能描述: 获取数据库用户总数和总页数
     *
     * @throws :
     * @param:
     * @return:
     * @date: 2020/6/24 下午7:18
     * @author: 莫良咚咚咚
     */
    public static int[] totalPage(int count, String keyword) {
        //arr[o]总用户数，arr[1]总页数
        int arr[] = {0, 1};
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "";
        try {
            //准备sql语句，查询所有用户总数
            if (sql != null) {
                conn = BaseJdbc.getConnection();
                sql = "select count(*) from users where user_id like ? ";
                pst = conn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
            } else {
                conn = BaseJdbc.getConnection();
                sql = "select count(*) from users";
                pst = conn.prepareStatement(sql);
            }

            rs = pst.executeQuery();
            while (rs.next()) {
                //通过数据库列数获取用户总数arr[0]  （数据库中第一列，无所谓所有列数一样）
                arr[0] = rs.getInt(1);
                //总页数arr[1]
                if (arr[0] % count == 0) {
                    //判断是不是整数页数，是那就是总页数
                    arr[1] = arr[0] / count;
                } else {
                    //有余数，那就让总页数加1
                    arr[1] = arr[0] / count + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                BaseJdbc.rollbackAndClose();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arr;
    }
}
