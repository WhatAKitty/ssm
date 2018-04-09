package com.whatakitty.ssm.utils;

import com.whatakitty.ssm.dto.Sort;
import java.util.Iterator;
import java.util.Map;

/**
 * 排序工具类
 *
 * @author yuhailun
 * @date 2018/02/12
 * @description
 **/
public class OrderByUtils {

    /**
     * 获取排序方式
     *
     * @param sort         排序信息
     * @param orderMapping 排序映射
     * @return 排序方式
     */
    public static String getOrderBy(Sort sort, Map<String, String> orderMapping) {
        if (sort == null) {
            return null;
        }

        StringBuilder orderBy = new StringBuilder();
        Iterator<Sort.Order> iterator = sort.iterator();
        while (iterator.hasNext()) {
            Sort.Order order = iterator.next();
            if (orderMapping.containsKey(order.getProperty())) {
                orderBy.append(orderMapping.get(order.getProperty()));
                orderBy.append(" ");
                orderBy.append(order.getDirection().name());
                if (iterator.hasNext()) {
                    orderBy.append(", ");
                }
            }
        }

        return orderBy.toString();
    }

}
