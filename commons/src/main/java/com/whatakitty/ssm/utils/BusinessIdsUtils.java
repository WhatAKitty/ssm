package com.whatakitty.ssm.utils;

import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.db.MyMapper;
import com.whatakitty.ssm.db.mybatis.IdEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * 业务数据批处理工具
 *
 * @param <T> 传入实体泛型
 * @author xuqiang
 * @date 2018/01/11
 * @description
 **/
public class BusinessIdsUtils<T extends IdEntity> {

    private final MyMapper mapper;

    public BusinessIdsUtils(MyMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 批量创建记录
     *
     * <p>注意：该方法并不标注为事务性，由调用方决定隔离级别。
     * 建议{@see org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED}</p>
     *
     * @param dtos 数据列表载体
     * @param date 创建日期
     * @param f    转化函数
     * @return 列表
     */
    public List<T> batchInsert(List<? extends Object> dtos, Date date, Function<Integer, T> f) {
        final ArrayList<T> ts = new ArrayList<>();
        int result = mapper.insertList(
            IntStream.range(0, dtos.size()).collect(() -> ts, (list, i) -> {
                final T t = f.apply(i);
                list.add(t);
            }, ArrayList::addAll)
        );

        Asserts.isTrue(result == dtos.size(), 500, "批量插入集合不完整");
        return ts;
    }


}
