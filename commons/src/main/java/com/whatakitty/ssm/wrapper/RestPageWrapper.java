package com.whatakitty.ssm.wrapper;

import com.github.pagehelper.Page;

/**
 * 分页信息包装
 *
 * @author yuhailun
 * @date 2018/02/11
 * @description
 **/
public class RestPageWrapper {

    /**
     * 分页信息包装
     *
     * @param obj         数据
     * @param restWrapper 自定义数据包装
     * @return 包装对象
     */
    public static Object wrap(Object obj, RestWrapper restWrapper) {
        if (!Page.class.isAssignableFrom(obj.getClass())) {
            return restWrapper.wrap(obj);
        }

        Page page = (Page) obj;
        return RestWrapper
            .create("pageNum", "pageSize", "totalRow", "list")
            .addHandler("pageNum", pageProperty -> page.getPageNum())
            .addHandler("pageSize", pageProperty -> page.getPageSize())
            .addHandler("totalRow", pageProperty -> page.getTotal())
            .addHandler("list", list -> restWrapper.wrap(page))
            .build();
    }

}
