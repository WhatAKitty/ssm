package com.whatakitty.generator.template;

import com.alibaba.druid.pool.DruidDataSource;
import com.whatakitty.generator.storage.LocalStorage;
import java.sql.*;
import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/03/08
 * @description
 **/
class DatabaseKit {

    private final LocalStorage localStorage;

    DatabaseKit(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    Schema schema(String dbName, String tableName) throws SQLException {
        final Schema schema = new Schema();

        String table = null;
        String tableSchema = null;

        final Connection connection = getConnection();
        try {
            final PreparedStatement ps = connection.prepareStatement(
                "select "
                    + "TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, IS_NULLABLE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, COLUMN_KEY, COLUMN_DEFAULT, NUMERIC_PRECISION, COLUMN_COMMENT "
                    + "from "
                    + "information_schema.columns "
                    + "where table_name = ? and TABLE_SCHEMA = ?"
            );
            ps.setString(1, tableName);
            ps.setString(2, dbName);
            final ResultSet rs = ps.executeQuery();
            final ResultSetMetaData rsm = rs.getMetaData();
            while (rs.next()) {
                Schema.ColInfo colInfo = new Schema.ColInfo();
                colInfo.setTABLE_SCHEMA(rs.getString("TABLE_SCHEMA"));
                colInfo.setTABLE_NAME(rs.getString("TABLE_NAME"));
                colInfo.setCOLUMN_NAME(rs.getString("COLUMN_NAME"));
                colInfo.setIS_NULLABLE(rs.getString("IS_NULLABLE"));
                colInfo.setDATA_TYPE(rs.getString("DATA_TYPE"));
                colInfo.setCHARACTER_MAXIMUM_LENGTH(rs.getInt("CHARACTER_MAXIMUM_LENGTH"));
                colInfo.setCOLUMN_KEY(rs.getString("COLUMN_KEY"));
                colInfo.setCOLUMN_DEFAULT(rs.getObject("COLUMN_DEFAULT"));
                colInfo.setNUMERIC_PRECISION(rs.getInt("NUMERIC_PRECISION"));
                colInfo.setCOLUMN_COMMENT(rs.getString("COLUMN_COMMENT"));

                table = rs.getString("TABLE_NAME");
                tableSchema = rs.getString("TABLE_SCHEMA");

                schema.addCol(colInfo);
            }

            if (!schema.isEmpty()) {
                schema.setTABLE_SCHEMA(tableSchema);
                schema.setTABLE_NAME(table);
            }

            return schema;
        } finally {
            closeConnection(connection);
        }
    }

    void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    private Connection getConnection() throws SQLException {
        DataSource dataSource = create();
        return dataSource.getConnection();
    }

    private DruidDataSource create() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(localStorage.get("jdbc"));
        dataSource.setUsername(localStorage.get("jdbc_username"));
        dataSource.setPassword(localStorage.get("jdbc_password"));

        // 初始化大小，最小，最大
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);
        // 配置获取连接等待超时的时间
        dataSource.setMaxWait(60000);
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("select 1");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        // 打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        // 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        dataSource.addFilters("stat,wall,log4j");
        // 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");

        return dataSource;
    }

}
