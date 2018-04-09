package com.whatakitty.ssm.utils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合工具类
 *
 * @author yuhailun
 * @date 2018/01/25
 * @description
 **/
public class CollectionUtils {


    /**
     * 比较两个集合，取相同的部分（取交集）
     *
     * @param source         需要被比较的集合
     * @param target         比较集合
     * @param sourceFunction 被比较的集合自定义函数
     * @param targetFunction 比较集合自定义函数
     * @param <T>            被比较集合类型
     * @param <K>            比较集合类型
     * @return 返回被比较集合类型的集合
     */
    public static <T, K, W> List<T> takeIntersection(Collection<T> source, Collection<K> target, Function<T, W> sourceFunction, Function<K, W> targetFunction) {
        return source.parallelStream().filter(
            t -> target.parallelStream().filter(k -> sourceFunction.apply(t).equals(targetFunction.apply(k))).count() > 0
        ).collect(Collectors.toList());
    }


}

