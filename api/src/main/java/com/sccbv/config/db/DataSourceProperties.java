package com.sccbv.config.db;

import com.whatakitty.ssm.db.BaseDataSourceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/04/05
 * @description
 **/
@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceProperties extends BaseDataSourceProperties {
}
