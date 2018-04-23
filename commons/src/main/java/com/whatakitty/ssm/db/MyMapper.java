package com.whatakitty.ssm.db;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 通用Mapper
 *
 * @author xuqiang
 * @date 2018/04/05
 **/
@RegisterMapper
public interface MyMapper<T> extends Mapper<T>, InsertListMapper<T>, IdsMapper<T> {
}
