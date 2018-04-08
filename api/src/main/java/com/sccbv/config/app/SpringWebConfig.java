package com.sccbv.config.app;

import static freemarker.template.Configuration.VERSION_2_3_27;

import com.whatakitty.ssm.argumentresolvers.PageableHandlerMethodArgumentResolver;
import com.whatakitty.ssm.argumentresolvers.SortHandlerMethodArgumentResolver;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import kr.pe.kwonnam.freemarker.inheritance.BlockDirective;
import kr.pe.kwonnam.freemarker.inheritance.ExtendsDirective;
import kr.pe.kwonnam.freemarker.inheritance.PutDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/04/05
 * @description
 **/
@Configuration
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ServletContext context;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SortHandlerMethodArgumentResolver());
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/node_modules/**").addResourceLocations("/node_modules/");
    }

    @Bean
    public Map<String, TemplateModel> freemarkerLayoutDirectives() {
        Map<String, TemplateModel> freemarkerLayoutDirectives = new HashMap<>(3);
        freemarkerLayoutDirectives.put("extends", new ExtendsDirective());
        freemarkerLayoutDirectives.put("block", new BlockDirective());
        freemarkerLayoutDirectives.put("put", new PutDirective());

        return freemarkerLayoutDirectives;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws TemplateModelException {
        // FreeMarker配置
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(VERSION_2_3_27);
        configuration.setTemplateLoader(new WebappTemplateLoader(context, "/WEB-INF/freemarker/"));
        configuration.setTagSyntax(freemarker.template.Configuration.SQUARE_BRACKET_TAG_SYNTAX);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setOutputEncoding("UTF-8");
        configuration.setLocale(Locale.SIMPLIFIED_CHINESE);
        configuration.setURLEscapingCharset("UTF-8");
        configuration.setDateFormat("yyyy年MM月dd日");
        configuration.setTimeFormat("HH:mm:ss");
        configuration.setDateTimeFormat("yyyy年MM月dd日 HH:mm:ss");
        configuration.setNumberFormat("#");
        configuration.setSharedVariable("root", context.getContextPath());
        configuration.setSharedVariable("layout", freemarkerLayoutDirectives());

        // FreeMarker配置器
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setConfiguration(configuration);
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        return freeMarkerConfigurer;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setCache(false);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
