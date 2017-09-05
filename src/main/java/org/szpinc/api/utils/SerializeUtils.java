/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package org.szpinc.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Java对象序列化工具类
 */
public class SerializeUtils {

    private static final Log log = LogFactory.getLog(SerializeUtils.class);

    private static void close(ObjectOutputStream objectOutputStream, ByteArrayOutputStream byteArrayOutputStream) {
        try {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (Exception e) {
            log.error("关闭IO资源异常[" + e.getMessage() + "]", e);
        }
    }

    private static void close(ObjectInputStream objectInputStream, ByteArrayInputStream byteArrayInputStream) {
        try {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
        } catch (Exception e) {
            log.error("关闭IO资源异常[" + e.getMessage() + "]", e);
        }
    }

    /**
     * 序列化对象方法
     *
     * @param object the object
     * @return the byte [ ]
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return bytes;
        } catch (Exception e) {
            log.error("关闭IO资源异常[" + e.getMessage() + "]", e);
        } finally {
            close(objectOutputStream, byteArrayOutputStream);
        }
        return null;
    }

    /**
     * 反序列化对象的方法
     *
     * @param <T>   the type parameter
     * @param bytes the bytes
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public static <T> T unserialize(byte[] bytes) {
        if (bytes == null)
            return null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(objectInputStream, byteArrayInputStream);
        }
        return null;
    }
}
