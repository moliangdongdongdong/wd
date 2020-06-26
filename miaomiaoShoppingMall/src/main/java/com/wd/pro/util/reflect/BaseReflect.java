package com.wd.pro.util.reflect;

import java.lang.reflect.Field;

/**
 * @author 李昊哲
 * @version 1.0
 * @date 2020/5/22 下午1:10
 */
public abstract class BaseReflect {
    /**
     * @param <T>    数据类型
     * @param father 父类对象
     * @param child  子类对象
     * @throws Exception Exception
     */
    public static <T> T fatherToChildWithFiled(T father, T child) throws Exception {
//        if (child.getClass().getSuperclass() != father.getClass()) {
//            throw new Exception("child 不是 father 的子类");
//        }
        Class<?> fatherClass = father.getClass();
        Field[] declaredFields = fatherClass.getDeclaredFields();
        for (Field filed : declaredFields) {
            filed.setAccessible(true);
            if (filed.get(father) != null) {
                filed.set(child, filed.get(father));
            }
        }
        return child;
    }
}
