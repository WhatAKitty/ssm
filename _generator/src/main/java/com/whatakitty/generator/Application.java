package com.whatakitty.generator;

import com.whatakitty.generator.core.Commandor;
import com.whatakitty.generator.storage.JSONLocalStorage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 启动类
 *
 * @author xuqiang
 * @date 2018/03/07
 * @description
 **/
@Configuration
@ComponentScan(basePackages = "com.whatakitty.generator")
public class Application {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        
        JSONLocalStorage jsonLocalStorage = context.getBean(JSONLocalStorage.class);
        new Commandor(jsonLocalStorage).execute();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
