package com.whatakitty.generator.storage;

import java.util.Map;

/**
 * 本地存储接口
 *
 * @author xuqiang
 * @date 2018/03/07
 * @description
 **/
public interface LocalStorage {

    /**
     * 获取大小
     *
     * @return 大小
     */
    int getSize();

    /**
     * 获取所有的内容
     *
     * @return 内容
     */
    Map<String, Object> getAll();

    /**
     * 存储
     *
     * @param key   健
     * @param value 值
     * @return 是否成功
     */
    boolean put(String key, Object value);

    /**
     * 获取
     *
     * @param key 健
     * @param <T> 获取的对象类型
     * @return 对象
     */
    <T> T get(String key);

    /**
     * 删除某个键以及其值
     *
     * @param key 键
     * @return 是否删除成功
     */
    boolean remove(String key);

    /**
     * 清空存储的值
     *
     * @return 是否成功
     */
    boolean clear();

    /**
     * 是否存在某个键值
     *
     * @param key 键
     * @return 是否存在
     */
    boolean exists(String key);

}
