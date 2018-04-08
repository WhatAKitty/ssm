/*
 * *****************************************************************************
 *  杭州高网，机密
 *  __________________
 *
 *  [2016] - [2020] 杭州高网信息技术有限公司
 *  版权所有。
 *
 *  注意：此处包含的所有信息均为杭州高网信息技术有限公司的财产。知识和技术理念
 *  包含在内为杭州高网信息技术有限公司所有，可能受中国和国际专利，以及商业秘密
 *  或版权法保护。严格禁止传播此信息或复制此材料，除非事先获得来自杭州高网信
 *  息技术有限公司的书面许可。
 *
 */

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
