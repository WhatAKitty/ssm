package com.whatakitty.generator.template;

import com.whatakitty.generator.Constants;
import java.io.Serializable;
import java.util.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 表结构描述
 *
 * @author xuqiang
 * @date 2018/03/08
 * @description
 **/
@Getter
@Setter
public class Schema implements Serializable {

    private static final long serialVersionUID = Constants.VERSION;

    private final Map<String, ColInfo> cols;
    private final List<String> keys;

    private String TABLE_SCHEMA;
    private String TABLE_NAME;
    private String PRI_NAME;

    /**
     * 创建一个空的表结构
     */
    public Schema() {
        this.keys = new ArrayList<>();
        this.cols = new TreeMap<>();
    }

    /**
     * 创建表结构
     *
     * @param cols 列信息
     */
    public Schema(SortedMap<String, ColInfo> cols) {
        this.cols = cols;
        this.keys = new ArrayList<>();
        this.cols.forEach((s, colInfo) -> {
            if (colInfo.isPrimaryKey()) {
                PRI_NAME = colInfo.getCOLUMN_NAME();
            }

            this.keys.add(colInfo.getCOLUMN_NAME());
        });
    }

    /**
     * 添加一列
     *
     * @param colInfo 列信息
     * @return 表结构
     */
    public Schema addCol(ColInfo colInfo) {
        this.keys.add(colInfo.getCOLUMN_NAME());
        this.cols.put(colInfo.getCOLUMN_NAME(), colInfo);
        if (colInfo.isPrimaryKey()) {
            PRI_NAME = colInfo.getCOLUMN_NAME();
        }
        return this;
    }

    public boolean isEmpty() {
        return this.cols.isEmpty();
    }

    /**
     * 获取列信息
     *
     * @param colName 列名
     * @return 列信息
     */
    public ColInfo get(String colName) {
        return this.cols.get(colName);
    }

    /**
     * 获取主键的列信息
     *
     * @return 主键列信息
     */
    public ColInfo getPri() {
        return get(PRI_NAME);
    }

    /**
     * 列结构描述
     *
     * @author xuqiang
     * @date 2018/03/08
     * @description
     **/
    @Getter
    @Setter
    public static class ColInfo implements Serializable {

        private static final long serialVersionUID = Constants.VERSION;

        private String TABLE_SCHEMA;

        private String TABLE_NAME;

        private String COLUMN_NAME;

        private String IS_NULLABLE;

        private String DATA_TYPE;

        private Integer CHARACTER_MAXIMUM_LENGTH;

        private String COLUMN_KEY;

        private Object COLUMN_DEFAULT;

        private Integer NUMERIC_PRECISION;

        private String COLUMN_COMMENT;

        public ColInfo() {
        }

        /**
         * 是否为主键
         *
         * @return 是否为主键
         */
        public boolean isPrimaryKey() {
            return "PRI".equalsIgnoreCase(StringUtils.trim(COLUMN_KEY));
        }

        /**
         * 是否可为空
         *
         * @return 是否可为空
         */
        public boolean isNullable() {
            return "YES".equalsIgnoreCase(StringUtils.trim(IS_NULLABLE));
        }

        /**
         * 是否为长文本类型
         *
         * @return 长文本类型
         */
        public boolean isLongText() {
            return this.DATA_TYPE.equalsIgnoreCase("text");
        }

        /**
         * 获取数据类型
         *
         * @return 数据类型
         */
        public String getDATA_TYPE() {
            switch (DATA_TYPE) {
                case "int":
                    return "Integer";
                case "bigint":
                    return "Long";
                case "varchar":
                case "text":
                case "char":
                    return "String";
                case "bit":
                    return "Boolean";
                case "datetime":
                case "date":
                case "timestamp":
                    return "Date";
                case "decimal":
                    return "BigDecimal";
                default:
                    throw new RuntimeException("not support " + DATA_TYPE);
            }
        }

        /**
         * 获取驼峰法字符（a_b -> aB）
         *
         * @return 驼峰名
         */
        public String getCamelColumnName() {
            return StringUtils.uncapitalize(StringUtils.underlineConvertCamel(getCOLUMN_NAME()));
        }

    }

}
