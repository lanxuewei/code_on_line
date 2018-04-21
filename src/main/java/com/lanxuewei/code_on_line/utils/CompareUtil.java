package com.lanxuewei.code_on_line.utils;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * create by lanxuewei in 2018/3/31
 * 对比工具类 TODO 修改完善对比函数
 */
public class CompareUtil {
    /**
     * 对比两个对象中属性值是否相等
     */
    public static boolean isPropertiesEquals(Object o1, Object o2) {
        if (o1 == null && o2 == null) {  //两者为空
            return true;
        }
        if (o1 == null || o2 == null) {  //其中有一个为空
            return false;
        }
        //两者都不为空，对实例中属性值进行对比
        Field[] fields = o1.getClass().getDeclaredFields();  //获得所有属性值
        for (Field field : fields){
            field.setAccessible(true);               //设置对象属性的可见行，访问私有属性
            try {
                Object fieldObj1 = field.get(o1);    //获取该o1和o2该字段的具体属性值
                Object fieldObj2 = field.get(o2);
                if (!isEqual(fieldObj1, fieldObj2)){  //属性值对比,不相同则false
                    System.out.println("该属性值不同：" + field + "item1 = " + fieldObj1 + "item2 = " + fieldObj2);
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 对比两个对象的属性值是否相等，ignoreProperties数组为忽略比较的属性值集合
     */
    public static boolean isPropertiesEquals(Object o1, Object o2, Set<String> ignoreProperties){
        if (o1 == null && o2 == null) {  //两者为空
            return true;
        }
        if (o1 == null || o2 == null) {  //其中有一个为空
            return false;
        }
        //两者都不为空，对实例中属性值进行对比
        Field[] fields = o1.getClass().getDeclaredFields();  //获得所有属性值
        for (Field field : fields) {
            field.setAccessible(true);                       //设置对象属性的可见行，访问私有属性
            try {
                if (!ignoreProperties.contains(field.getName())) {    //遇到需要比较的属性值则比较
                    Object fieldObj1 = field.get(o1);
                    Object fieldObj2 = field.get(o2);
                    if (!isEqual(fieldObj1, fieldObj2)) {     //某个属性值不相同，false
                        System.out.println("该属性值不同：" + field + "item1 = " + fieldObj1 + "item2 = " + fieldObj2);
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 对比两个对象是否相等 TODO 如果是对比数据库时间和java Date 时间则会出错
     * @param o1
     * @param o2
     * @return
     */
    public static boolean isEqual(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }

}
