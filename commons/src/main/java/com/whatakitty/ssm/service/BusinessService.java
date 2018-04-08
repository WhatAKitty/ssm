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

package com.whatakitty.ssm.service;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.db.mybatis.BaseEntity;
import com.whatakitty.ssm.db.mybatis.IdEntity;
import com.whatakitty.ssm.db.mybatis.SDelEntity;
import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.utils.IdGenerate;
import com.whatakitty.ssm.utils.BusinessIdsUtils;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 业务基础Service层
 *
 * @param <T> 传入实体泛型，且必须继承自{@link IdEntity}
 * @param <D> 传入载体泛型
 * @author xuqiang
 * @date 2018/01/09
 * @description
 **/
public abstract class BusinessService<T extends IdEntity, D> extends BaseService<T> {

    private final Class<T> clazz;
    private final BusinessIdsUtils<T> businessIdsUtils;

    @Getter(AccessLevel.PROTECTED)
    @Autowired
    private IdGenerate idGenerate;

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    public BusinessService(Mapper<T> mapper, Class<T> clazz) {
        super(mapper);
        this.clazz = clazz;
        this.businessIdsUtils = new BusinessIdsUtils<>(mapper);
    }

    /**
     * 根据主键判断是否已经存在
     *
     * @param id    主键
     * @param force 是否强制查询已被标记软删除的记录
     * @return 是否存在
     */
    public boolean existsByPrimaryKey(Long id, boolean force) {
        T t = newInstance();
        t.setId(id);
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            ((SDelEntity) t).setIsDel(SDelEntity.DEL_FALSE);
        }

        return getMapper().selectCount(t) == 1;
    }

    /**
     * 根据条件查询是否已经存在
     *
     * @param d     条件载体
     * @param force 是否强制查询已被标记软删除的记录
     * @return 是否存在
     */
    public boolean existsByDTO(D d, boolean force) {
        T t = copyFromDTO(d);
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            ((SDelEntity) t).setIsDel(SDelEntity.DEL_FALSE);
        }

        return getMapper().selectCount(t) > 0;
    }

    /**
     * 根据条件表达式查询是否已经存在
     *
     * @param example 条件表达式
     * @param force   是否强制查询已被标记软删除的记录
     * @return 是否存在
     */
    public boolean existsByExample(Example example, boolean force) {
        // 存在软删除标记，并且非强制查询，则查询所有未被标记删除的记录
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            example.and().andEqualTo(SDelEntity.DEL_LABEL, SDelEntity.DEL_FALSE);
        }

        return getMapper().selectCountByExample(example) > 0;
    }

    /**
     * 查询全部记录
     *
     * @param force 是否包含被软删除的记录
     * @return 查询结果
     */
    public List<T> all(boolean force) {
        // 存在软删除标记，并且非强制查询，则查询所有未被标记删除的记录
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            // 存在软删除，查询软删除
            T t = newInstance();
            ((SDelEntity) t).setIsDel(SDelEntity.DEL_FALSE);
            return select(t);
        }

        return selectAll();
    }

    /**
     * 通过条件查询记录
     *
     * @param d     查询条件载体
     * @param force 是否包含被软删除的记录
     * @return 查询结果
     */
    public List<T> byDTO(D d, boolean force) {
        // 存在软删除标记，并且非强制查询，则查询所有未被标记删除的记录
        T t = copyFromDTO(d);
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            // 存在软删除，查询软删除
            ((SDelEntity) t).setIsDel(SDelEntity.DEL_FALSE);
        }

        return select(t);
    }

    /**
     * 通过条件表达式查询记录
     *
     * @param example 查询条件表达式
     * @param force   是否包含被软删除的记录
     * @return 查询结果
     */
    public List<T> byExample(Example example, boolean force) {
        // 存在软删除标记，并且非强制查询，则查询所有未被标记删除的记录
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            example.and().andEqualTo(SDelEntity.DEL_LABEL, SDelEntity.DEL_FALSE);
        }

        return getMapper().selectByExample(example);
    }

    /**
     * 通过主键编号列表查询记录
     *
     * @param collection 编号集合
     * @param force      是否包含被软删除的记录
     * @return 查询结果
     */
    public List<T> inByIds(Iterable<Long> collection, boolean force) {
        Example example = Example.builder(clazz).build();
        example.createCriteria().andIn("id", collection);
        // 存在软删除标记，并且非强制查询，则查询所有未被标记删除的记录
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            example.and().andEqualTo(SDelEntity.DEL_LABEL, SDelEntity.DEL_FALSE);
        }

        return getMapper().selectByExample(example);
    }

    /**
     * 根据条件负载和分页信息查询结果
     *
     * @param pageable 分页信息
     * @param d        条件载体
     * @param isPage   是否允许分页
     * @param force    是否包含被软删除的记录
     * @return 查询结果
     */
    public Object pageByDTO(Pageable pageable, D d, boolean isPage, boolean force) {
        if (isPage) {
            return selectPage(pageable, () -> byDTO(d, force));
        }

        return byDTO(d, force);
    }

    /**
     * 根据条件表达式和分页信息查询结果
     *
     * @param pageable 分页信息
     * @param example  条件表达式
     * @param isPage   是否允许分页
     * @param force    是否包含被软删除的记录
     * @return 查询结果
     */
    public Object pageByExample(Pageable pageable, Example example, boolean isPage, boolean force) {
        if (isPage) {
            return selectPage(pageable, () -> byExample(example, force));
        }

        return byExample(example, force);
    }

    /**
     * 通过条件查询某条记录
     *
     * @param d     查询条件
     * @param force 是否包含被软删除的记录
     * @return 查询结果
     */
    public T oneByDTO(D d, boolean force) {
        T t = copyFromDTO(d);
        // 存在软删除标记，并且非强制查询，则查询所有未被标记删除的记录
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            // 存在软删除，查询软删除
            ((SDelEntity) t).setIsDel(false);
        }

        return selectOne(t);
    }

    /**
     * 通过条件表达式查询某条记录
     *
     * @param example 条件表达式
     * @param force   是否包含被软删除的记录
     * @return 查询结果
     */
    public T oneByExample(Example example, boolean force) {
        // 存在软删除标记，并且非强制查询，则查询所有未被标记删除的记录
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            // 存在软删除，查询软删除
            example.and().andEqualTo(SDelEntity.DEL_LABEL, SDelEntity.DEL_FALSE);
        }

        return getMapper().selectByExampleAndRowBounds(example, new RowBounds(1, 1)).get(0);
    }

    /**
     * 根据主键查询某条记录
     *
     * @param key   主键
     * @param force 是否包含被软删除的记录
     * @return 查询结果
     */
    public T byPrimaryKey(Long key, boolean force) {
        T t = newInstance();
        t.setId(key);
        // 存在软删除标记，并且非强制查询，则查询所有未被标记删除的记录
        if (SDelEntity.class.isAssignableFrom(clazz) && !force) {
            // 存在软删除，查询软删除
            ((SDelEntity) t).setIsDel(SDelEntity.DEL_FALSE);
        }

        return selectOne(t);
    }

    /**
     * 创建记录
     *
     * @param d 传输载体
     * @return 创建响应
     */
    public T create(D d, Date date) {
        T t = newInstance();
        BeanUtils.copyProperties(d, t);
        if (t instanceof SDelEntity) {
            ((SDelEntity) t).setIsDel(false);
        }

        int result = save(t, date);
        Asserts.isTrue(result == 1, 500, "创建失败");
        return t;
    }

    /**
     * 批量创建记录
     *
     * <p>
     * 注意：该方法并不标注为事务性，由调用方决定隔离级别。
     * 建议{@see org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED}
     * </p>
     *
     * @param ds   数据列表载体
     * @param date 创建日期
     * @return 列表
     */
    public List<T> batchCreate(List<D> ds, Date date) {
        return businessIdsUtils.batchInsert(ds, date, (i) -> {
            final D item = ds.get(i);
            final T t = newInstance();
            BeanUtils.copyProperties(item, t);
            t.setId(idGenerate.getNextValue());
            if (BaseEntity.class.isAssignableFrom(clazz)) {
                ((BaseEntity) t).setCreateDate(date);
                ((BaseEntity) t).setModifyDate(date);
            }
            if (SDelEntity.class.isAssignableFrom(clazz)) {
                ((SDelEntity) t).setIsDel(SDelEntity.DEL_FALSE);
            }
            return t;
        });
    }

    /**
     * 更新记录
     *
     * @param id    编号
     * @param d     传输载体
     * @param force 是否包含被软删除的记录
     * @return 创建响应
     */
    public T update(Long id, D d, boolean force) {
        return update(id, d, force, new Date());
    }

    /**
     * 更新记录
     *
     * @param id               编号
     * @param d                传输载体
     * @param force            是否包含被软删除的记录
     * @param ignoreProperties 复制时忽略的属性
     * @return 创建响应
     */
    public T update(Long id, D d, boolean force, String... ignoreProperties) {
        return update(id, d, force, new Date(), ignoreProperties);
    }

    /**
     * 更新记录
     *
     * @param id    编号
     * @param d     传输载体
     * @param force 是否包含被软删除的记录
     * @param date  时间
     * @return 创建响应
     */
    @Transactional(isolation = READ_COMMITTED)
    public T update(Long id, D d, boolean force, Date date, String... ignoreProperties) {
        T t = newInstance();
        t.setId(id);

        // 查询目标对象是否存在，如果标记强制更新则忽略软删除标记
        if (t instanceof SDelEntity && !force) {
            ((SDelEntity) t).setIsDel(false);
        }
        Asserts.isTrue(getMapper().selectCount(t) > 0, 400, String.format("不存在编号为%s的记录", id));

        ignoreProperties = ArrayUtils.add(ignoreProperties, "id");
        BeanUtils.copyProperties(d, t, ignoreProperties);

        Asserts.isTrue(updateNotNull(t, date) == 1, 500, String.format("更新编号为%s的记录失败", id));

        return t;
    }

    /**
     * 硬删除记录
     *
     * @param id 编号
     * @return 删除记录
     */
    public T destroy(Long id) {
        return destroy(id, true);
    }

    /**
     * 删除记录
     *
     * <p>
     * <b>目标记录是否可以软删除：</b>
     * <ul>
     * <li>可以软删除，并且未标记硬删除，则更新软删除字段</li>
     * <li>可以软删除，并且标记硬删除，则直接删除目标记录</li>
     * <li>不可以软删除，不论是否标记硬删除，直接删除目标记录</li>
     * </ul>
     * </p>
     *
     * @param id    编号
     * @param force 是否包含被软删除的记录
     * @return 删除响应
     */
    @Transactional(isolation = READ_COMMITTED, rollbackFor = Throwable.class)
    public T destroy(Long id, boolean force) {
        T t = newInstance();
        t.setId(id);
        Asserts.isTrue(getMapper().selectCount(t) > 0, 400, String.format("不存在编号为%s的记录", id));

        int result;
        // 存在软删除标记并且未标记硬删除
        if (t instanceof SDelEntity && !force) {
            // 存在软删除标记
            ((SDelEntity) t).setIsDel(true);
            result = updateNotNull(t, new Date());
        } else {
            // 对目标记录硬删除
            result = delete(id);
        }

        Asserts.isTrue(result == 1, 500, String.format("删除编号为%s的记录失败", id));
        return t;
    }

    private T newInstance() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private T copyFromDTO(D d) {
        T t = newInstance();
        BeanUtils.copyProperties(d, t);
        return t;
    }

}
