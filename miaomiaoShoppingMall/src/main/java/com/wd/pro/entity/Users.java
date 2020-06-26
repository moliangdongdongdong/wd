package com.wd.pro.entity;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Auther: 莫良咚咚咚
 * @Date: 2020/6/23 00:11
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
    /**
     * 功能描述:
     *
     * @param: user_ID  编号
     * @return:
     * @date: 2020/6/23 上午12:19
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_id;
//    /**
//     * 功能描述:
//     *
//     * @param: uuid   唯一标识符
//     * @return:
//     * @date: 2020/6/23 上午12:20
//     * @throws :
//     * @author: 莫良咚咚咚
//     */
//    private String uuid;
    /**
     * 功能描述:
     *
     * @param: user_Name 姓名
     * @return:
     * @date: 2020/6/23 上午12:20
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_name;
    /**
     * 功能描述:
     *
     * @param: user_password 密码
     * @return:
     * @date: 2020/6/23 上午12:20
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_password;
    /**
     * 功能描述:
     *
     * @param: user_mobile 手机号码
     * @return:
     * @date: 2020/6/23 上午12:21
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_mobile;
    /**
     * 功能描述:
     *
     * @param: user_sex 性别 1 男  2 女
     * @return:
     * @date: 2020/6/23 上午12:21
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_sex;
    /**
     * 功能描述:
     *
     * @param: user_email  邮箱
     * @return:
     * @date: 2020/6/23 上午12:22
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_email;
    /**
     * 功能描述:
     *
     * @param: register_Time 注册时间
     * @return:
     * @date: 2020/6/23 上午12:22
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String register_time;
    /**
     * 功能描述:
     *
     * @param: user_birthday 出生日期
     * @return:
     * @date: 2020/6/23 上午12:23
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_birthday;
    /**
     * 功能描述:
     *
     * @param: user_idenity_code  扩展信息
     * @return:
     * @date: 2020/6/23 上午12:23
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_idenity_code;
    /**
     * 功能描述:
     *
     * @param: user_address  用户地址（可以有多个地址）
     * @return:
     * @date: 2020/6/23 上午12:24
     * @throws :
     * @author: 莫良咚咚咚
     */
    private String user_address;
    /**
     * 功能描述:
     *
     * @param: user_status 用户的角色 1 普通用户    2 管理员
     * @return:
     * @date: 2020/6/23 上午12:25
     * @throws :
     * @author: 莫良咚咚咚
     */
    private Integer user_status;

    @Override
    public String toString() {
        return "Users{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_mobile='" + user_mobile + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_email='" + user_email + '\'' +
                ", register_time='" + register_time + '\'' +
                ", user_birthday='" + user_birthday + '\'' +
                ", user_idenity_code='" + user_idenity_code + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_status=" + user_status +
                '}';
    }
}
