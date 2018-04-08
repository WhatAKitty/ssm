package com.whatakitty.ssm.db.mybatis.mapper;

import com.whatakitty.ssm.db.mybatis.provider.SpecialProvider;
import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;

/**
 * 通用Mapper接口,特殊方法，批量插入，支持批量插入的数据库都可以使用，例如mysql,h2等
 *
 * @param <T> 不能为空
 * @author liuzh
 * @author xuqiang
 * @date 2018/1/24
 */
public interface InsertListMapper<T> {

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列
     *
     * @param recordList 记录集合
     * @return 插入的记录数量
     */
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);

}
