package com.whatakitty.ssm.db.mybatis.typehandler;

import com.whatakitty.ssm.enums.ValueEnum;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * MyBatis 自定义枚举转换
 *
 * @author cyh
 * @date 2015/12/29
 */
public class EnumValueHandler extends BaseTypeHandler<ValueEnum> {
    private Class<ValueEnum> type;

    /**
     * 枚举类型处理
     *
     * @param type 枚举类型
     */
    public EnumValueHandler(Class<ValueEnum> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ValueEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public ValueEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getValuedEnum(i);
        }
    }

    @Override
    public ValueEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getValuedEnum(i);
        }
    }

    @Override
    public ValueEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getValuedEnum(i);
        }
    }

    private ValueEnum getValuedEnum(int value) {
        ValueEnum[] objs = type.getEnumConstants();
        for (ValueEnum em : objs) {
            if (em.getValue() == value) {
                return em;
            }
        }
        throw new IllegalArgumentException(
            "Cannot convert " + value + " to " + type.getSimpleName() + " by value.");
    }
}