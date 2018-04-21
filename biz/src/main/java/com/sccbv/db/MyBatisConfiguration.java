package com.sccbv.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.whatakitty.ssm.db.DruidDataSourcePool;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 数据库配置
 *
 * @author xuqiang
 * @date 2018/04/05
 * @description
 **/
@Configuration
@MapperScan(
    basePackages = {"com.sccbv.demo", "com.sccbv.system", "com.sccbv.business"},
    properties = {
        "notEmpty=true",
        "IDENTITY=MYSQL",
        "style=camelhumpAndLowercase",
        "enableMethodAnnotation=true",
        "useSimpleType=true",
        "usePrimitiveType=true",
        "enumAsSimpleType=true",
        "simpleTypes=com.whatakitty.ssm.enums.ValueEnum",
        "checkExampleEntityClass=true",
        "safeDelete=true",
        "safeUpdate=true"
    }
)
public class MyBatisConfiguration {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() throws SQLException {
        return DruidDataSourcePool.create(dataSourceProperties);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        configuration.setMapperHelper(new MapperHelper());

        Properties properties = new Properties();
        properties.setProperty("dialect", "");
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);

        // 查找mapper文件夹下的所有xml文件
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources("classpath:mapper/**/*.xml");

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setPlugins(new Interceptor[] {pageInterceptor});
        sessionFactory.setMapperLocations(resources);
        return sessionFactory.getObject();
    }

}
