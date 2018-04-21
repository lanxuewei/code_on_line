package com.lanxuewei.code_on_line.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * create by lanxuewei in 2018/3/31
 * 对比工具类
 */
public class CompareUtil {

    private static final Logger logger = LoggerFactory.getLogger(CompareUtil.class);

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
            field.setAccessible(true);             //设置对象属性的可见行，访问私有属性
            try {
                Object fieldObj1 = field.get(o1);  //获取该o1和o2该字段的具体属性值
                Object fieldObj2 = field.get(o2);
                if (!isEqual(fieldObj1, fieldObj2)){  //属性值对比,不相同则false
                    logger.error("{} property is different : obj1 = {} ---> obj2 = {}", field.getName() , fieldObj1, fieldObj2);
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
                    if (!isEqual(fieldObj1, fieldObj2)) {  //某个属性值不相同，false
                        logger.error("{} property is different : obj1 = {} ---> obj2 = {}", field.getName() , fieldObj1, fieldObj2);
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public static boolean isEqual(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        } else if (o2 == null) {
            return false;
        } else {
            return o1 instanceof Date ? isDateEqual((Date)o1, (Date)o2) : o1.equals(o2);
        }
    }

    public static boolean isStringEqualIgnoreCase(String o1, String o2) {
        if (o1 == null) {
            return o2 == null;
        } else {
            return o2 == null ? false : o1.equalsIgnoreCase(o2);
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBigThan0(Number val) {
        return val != null && val.doubleValue() > 0.0D;
    }

    public static boolean isNotEmpty(Collection list) {
        return list != null && list.size() > 0;
    }

    public static boolean isEmpty(Collection list) {
        return list == null || list.size() == 0;
    }

    public static boolean isBetween(Number num, Number min, Number max) {
        return isGreaterAndEqualThan(num, min) && isLessAndEqualThan(num, max);
    }

    public static boolean isGreaterThan(Number num1, Number num2) {
        double numA = 0.0D;
        double numB = 0.0D;
        if (num1 != null) {
            numA = num1.doubleValue();
        }

        if (num2 != null) {
            numB = num2.doubleValue();
        }

        return numA - numB > 0.0D;
    }

    public static boolean isLessThan(Number num1, Number num2) {
        double numA = 0.0D;
        double numB = 0.0D;
        if (num1 != null) {
            numA = num1.doubleValue();
        }

        if (num2 != null) {
            numB = num2.doubleValue();
        }

        return numA - numB < 0.0D;
    }

    public static boolean isGreaterAndEqualThan(Number num1, Number num2) {
        double numA = 0.0D;
        double numB = 0.0D;
        if (num1 != null) {
            numA = num1.doubleValue();
        }

        if (num2 != null) {
            numB = num2.doubleValue();
        }

        return numA - numB >= 0.0D;
    }

    public static boolean isLessAndEqualThan(Number num1, Number num2) {
        double numA = 0.0D;
        double numB = 0.0D;
        if (num1 != null) {
            numA = num1.doubleValue();
        }

        if (num2 != null) {
            numB = num2.doubleValue();
        }

        return numA - numB <= 0.0D;
    }

    private static boolean isDateEqual(Date o1, Date o2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(o1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(o2);
        return c1.get(1) == c2.get(1) && c1.get(2) == c2.get(2) && c1.get(5) == c2.get(5) && c1.get(11) == c2.get(11) && c1.get(12) == c2.get(12) && c1.get(13) == c2.get(13);
    }

}