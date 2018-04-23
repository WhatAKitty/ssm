package com.whatakitty.ssm.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.whatakitty.ssm.db.mybatis.SsmVFS;
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
import tk.mybatis.mapper.code.Style;
import tk.mybatis.mapper.entity.Config;
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
    basePackages = "com.sccbv",
    markerInterface = MyMapper.class,
    properties = {
        "simpleTypes=com.whatakitty.ssm.enums.ValueEnum"
    },
    sqlSessionFactoryRef = "sqlSessionFactory"
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
        Config config = new Config();
        config.setNotEmpty(true);
        config.setIDENTITY("MYSQL");
        config.setStyle(Style.camelhumpAndLowercase);
        config.setEnableMethodAnnotation(true);
        config.setEnumAsSimpleType(true);
        config.setCheckExampleEntityClass(true);
        config.setSafeDelete(true);
        config.setSafeUpdate(true);
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setConfig(config);

        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        configuration.setMapperHelper(mapperHelper);

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
        sessionFactory.setVfs(SsmVFS.class);
        return sessionFactory.getObject();
    }

}
