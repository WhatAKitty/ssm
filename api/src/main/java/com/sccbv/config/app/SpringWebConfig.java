package com.sccbv.config.app;

import com.whatakitty.ssm.argumentresolvers.PageableHandlerMethodArgumentResolver;
import com.whatakitty.ssm.argumentresolvers.SortHandlerMethodArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/04/05
 * @description
 **/
@Order(1)
@Configuration
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SortHandlerMethodArgumentResolver());
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
