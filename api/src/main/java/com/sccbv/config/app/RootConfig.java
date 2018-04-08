package com.sccbv.config.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/04/07
 * @description
 **/
@Configuration
@ComponentScan(basePackages = {"com.whatakitty", "com.sccbv"})
public class RootConfig {
}
