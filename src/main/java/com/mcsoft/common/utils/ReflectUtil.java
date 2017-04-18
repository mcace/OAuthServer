package com.mcsoft.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具
 *
 * @author : Mc
 * @date : 2017/3/16 10:50
 */
public class ReflectUtil {
    /**
     * 将bean转为map，必须是符合javabean规范的对象，使用get+[Filedname首字母大写]作为获取值的方法
     * 转为Map对象key是变量名，值是变量值，如果值为null则跳过
     *
     * @param obj
     * @return
     */
    public static <T> Map<String, Object> convertObjToMap(Object obj) {
        if (null == obj) {
            return null;
        }
        return convertObjToMap(obj, obj.getClass());
    }

    /**
     * 将bean转换为map，规定转换模板类，检测父类，过滤Object类模板
     *
     * @param obj   转换的bean
     * @param clazz 转换的模板
     * @return 转换后的map
     */
    private static Map<String, Object> convertObjToMap(Object obj, Class clazz) {
        if (null == obj || null == clazz || !clazz.isAssignableFrom(obj.getClass()) || clazz.equals(Object.class)) {
            //判断传来的class是否是对象的父类或其本身，剔除Object类
            return null;
        }
        Map<String, Object> data = new HashMap<String, Object>();

        Map<String, Object> result;
        Class parentClazz = clazz.getSuperclass();
        if (null != parentClazz) {
            result = convertObjToMap(obj, parentClazz);
        } else {
            result = convertObjToMap(obj, clazz);
        }

        if (null != result) {
            data.putAll(result);
        }

        Field[] dataField = clazz.getDeclaredFields();
        for (int i = 0; i < dataField.length; i++) {
            Field field = dataField[i];
            try {
                String fieldName = field.getName();
                //获取getXxx方法
                Method method = clazz.getMethod("get" + StringUtils.capitalize(fieldName));
                //调用getXxx方法
                Object fieldObject = method.invoke(obj);
                //过滤值为null的情况
                if (null == fieldObject) {
                    continue;
                }
                data.put(fieldName, fieldObject);
            } catch (IllegalAccessException e) {
            } catch (NoSuchMethodException e) {
            } catch (InvocationTargetException e) {
            }
        }

        return data;
    }

    /**
     * bean转为map，内省版，可检测父类，一样是get+[Filedname首字母大写]作为获取值的方法
     *
     * @param obj
     * @return
     */
    private static Map<String, Object> convertObjToMapByIntrospector(Object obj) {
        Map<String, Object> data = new HashMap<String, Object>();

        Class clazz = obj.getClass();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                try {
                    Method method = propertyDescriptor.getReadMethod();
                    String name = propertyDescriptor.getName();
                    Object value = method.invoke(obj);

                    data.put(name, value);
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e) {
                }
            }
            data.remove("class");//删除“class”属性

        } catch (IntrospectionException e) {
        }

        return data;
    }
}
