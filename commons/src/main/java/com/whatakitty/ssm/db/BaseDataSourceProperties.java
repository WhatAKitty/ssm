package com.whatakitty.ssm.db;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author xuqiang
 * @date 2018/01/13
 */
@Data
public class BaseDataSourceProperties {

    /**
     * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
     */
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    /**
     * JDBC url of the database.
     */
    @Value("${jdbc.url}")
    private String url;

    /**
     * Login user of the database.
     */
    @Value("${jdbc.username}")
    private String username;

    /**
     * Login password of the database.
     */
    @Value("${jdbc.password}")
    private String password;

}