package com.sccbv.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

/**
 * @author xuqiang
 * @date 2018/01/13
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySQLUserDetailService mySQLUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 原auth.userDetailsService(mySQLUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
        auth.userDetailsService(mySQLUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers()
            // 允许iframe加载同域网页
            .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
            .and()
            .authorizeRequests()
            // 允许/resources以及/node_modules路径匹配下的请求访问
            .antMatchers("/resources/**", "/node_modules/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().defaultSuccessUrl("/").loginPage("/auth/login").permitAll()
            .and()
            .logout().logoutSuccessUrl("/auth/login?logout");
    }

    @Override
    @Bean(name = "authenticationManagerBean")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
