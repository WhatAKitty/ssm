package com.whatakitty.ssm.service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whatakitty.ssm.db.mybatis.BaseEntity;
import com.whatakitty.ssm.dto.Pageable;
import java.util.Date;
import java.util.List;
import tk.mybatis.mapper.common.Mapper;

/**
 * 基础业务层
 *
 * @param <T> 传入的实体泛型
 * @author xuqiang
 * @date 2017/6/11
 */
public abstract class BaseService<T> {

    private final Mapper<T> mapper;

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    public BaseService(Mapper<T> mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取数据库操作实例
     *
     * @return 数据库操作实例
     */
    public Mapper<T> getMapper() {
        return mapper;
    }

    /**
     * 根据条件查询某个记录
     *
     * @param entity 条件
     * @return 记录结果
     */
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    /**
     * 根据主键查询某条记录
     *
     * @param key 主键
     * @return 记录结果
     */
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 根据条件查询某些记录
     *
     * @param entity 条件
     * @return 查询结果列表
     */
    public List<T> select(T entity) {
        return mapper.select(entity);
    }

    /**
     * 查询所有记录
     *
     * @return 所有结果列表
     */
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 根据条件{@link tk.mybatis.mapper.entity.Example}查询某些记录
     *
     * @param example 条件
     * @return 结果集合
     */
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    /**
     * 根据分页信息以及传入的查询回调返回分页结果
     *
     * @param pageable 分页信息
     * @param select   查询回到
     * @return 分页结果 {@link Page}
     */
    public Page<T> selectPage(Pageable pageable, ISelect select) {
        return PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(), true).doSelectPage(select);
    }

    /**
     * 保存实体
     *
     * @param entity 需要保存的实体
     * @param date   保存日期
     * @return 保存的记录数
     */
    public int save(T entity, Date date) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setCreateDate(date);
            baseEntity.setModifyDate(date);
        }
        return mapper.insert(entity);
    }

    /**
     * 根据主键删除记录
     *
     * @param key 主键
     * @return 删除的结果数
     */
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    /**
     * 根据主键更新传入的实体信息（包含所有字段更新，即使这个字段的值为null）
     *
     * @param entity 实体信息
     * @param date   更新时间
     * @return 更新的结果数量
     */
    public int updateAll(T entity, Date date) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setModifyDate(date);
        }
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新传入的实体（不包含为null的值）
     *
     * @param entity 实体信息
     * @param date   更新时间
     * @return 更新的结果数量
     */
    public int updateNotNull(T entity, Date date) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setModifyDate(date);
        }
        return mapper.updateByPrimaryKeySelective(entity);
    }

}
