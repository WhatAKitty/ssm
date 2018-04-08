package com.whatakitty.ssm.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

/**
 * 主键生成器
 * USECASE:
 * private IdGenerate generate;
 * generate.getNextValue(); user the return value for int PK
 *
 * @author cwl
 * @date 2013-10-17 下午5:27:10
 */
@Component
public class IdGenerate {

    private final String VALUE_SQL = "SELECT LAST_INSERT_ID()";

    private DataSource dataSource;

    public IdGenerate() {
    }

    public IdGenerate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long getNextValue() {
        /*
         * Need to use straight JDBC code because we need to make sure that the insert and select are
         * performed on the same connection (otherwise we can't be sure that last_insert_id() returned
         * the correct value)
         */
        Connection con = DataSourceUtils.getConnection(getDataSource());
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            DataSourceUtils.applyTransactionTimeout(stmt, getDataSource());
            stmt.executeUpdate("REPLACE INTO sys_id (stub) VALUES ('y')");
            ResultSet rs = stmt.executeQuery(VALUE_SQL);
            try {
                if (!rs.next()) {
                    throw new DataAccessResourceFailureException(
                        "last_insert_id() failed after executing an update");
                }
                return rs.getLong(1);
            } finally {
                JdbcUtils.closeResultSet(rs);
            }
        } catch (SQLException ex) {
            throw new DataAccessResourceFailureException("Could not obtain last_insert_id()", ex);
        } finally {
            JdbcUtils.closeStatement(stmt);
            DataSourceUtils.releaseConnection(con, getDataSource());
        }
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}