package com.whatakitty.ssm.wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;

/**
 * REST返回结果封装层
 *
 * @author xuqiang
 * @date 2018/02/09
 * @description
 **/
public class RestWrapper {

    /**
     * 构建一个RestWrapper的实例
     *
     * @param properties 需要的属性值
     * @return restWrapper实例
     */
    public static RestWrapper create(String... properties) {
        return new RestWrapper(properties);
    }

    @Getter
    private final String[] properties;
    private final Map<String, WrapperHandler> handlers = new ConcurrentHashMap<>();

    public RestWrapper(String... properties) {
        this.properties = properties;
    }

    /**
     * 添加字段处理
     *
     * @param property 字段
     * @param handler  处理方式
     * @return wrapper实例
     */
    public RestWrapper addHandler(String property, WrapperHandler handler) {
        this.handlers.put(property, handler);
        return this;
    }

    /**
     * 根据properties和handlers包装一个对象
     *
     * @return 新的对象
     */
    public Map<String, Object> build() {
        return Stream.of(this.properties)
            .parallel()
            .map(property -> {
                WrapperHandler wrapperHandler = handlers.get(property);
                Object val = wrapperHandler != null
                    ? wrapperHandler.handle(property) : null;

                return new AbstractMap.SimpleEntry<>(property, val);
            })
            .collect(HashMap::new, (m, entry) -> m.put(String.valueOf(entry.getKey()), entry.getValue()), HashMap::putAll);
    }

    /**
     * 包装目标对象
     *
     * @param obj 目标对象
     * @return 处理后结果
     */
    public Object wrap(Object obj) {
        if (Iterable.class.isAssignableFrom(obj.getClass())) {
            return wrapIterator((Iterable) obj);
        } else {
            return wrapObj(obj);
        }
    }

    private Map<String, Object> wrapObj(Object obj) {
        Map<Object, Object> map = Map.class.isAssignableFrom(obj.getClass())
            ? (Map<Object, Object>) obj : new ObjectMapper().convertValue(obj, Map.class);

        return map
            .entrySet()
            .parallelStream()
            .filter(entry -> ArrayUtils.contains(properties, entry.getKey()))
            .map(entry -> {
                WrapperHandler wrapperHandler = handlers.get(entry.getKey());
                Object val = wrapperHandler != null
                    ? wrapperHandler.handle(entry.getValue()) : entry.getValue();
                return new AbstractMap.SimpleEntry<>(entry.getKey(), val);
            })
            .collect(HashMap::new, (m, entry) -> m.put(String.valueOf(entry.getKey()), entry.getValue()), HashMap::putAll);
    }

    private List<Map<String, Object>> wrapIterator(Iterable iterable) {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterator iter = iterable.iterator();
        while (iter.hasNext()) {
            result.add(wrapObj(iter.next()));
        }
        return result;
    }

    /**
     * 处理类
     *
     * @author xuqiang
     * @date 2018/02/09
     */
    @FunctionalInterface
    public interface WrapperHandler {

        Object handle(Object value);

    }


}
