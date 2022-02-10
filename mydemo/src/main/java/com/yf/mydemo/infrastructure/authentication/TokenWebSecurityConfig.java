package com.yf.mydemo.infrastructure.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthIgnoreConfig authIgnoreConfig;
    /**
     * 密码管理工具类
     */
    @Autowired
    private DefaultPasswordEncoder defaultPasswordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permitAll = authIgnoreConfig.getIgnoreUrls();
        permitAll.add("/druid/*");
        permitAll.add("/v2/api-docs");
        permitAll.add("/swagger-resources/configuration/ui");
        permitAll.add("/swagger-resources");
        permitAll.add("/swagger-ui.html");
        permitAll.add("/swagger-resources/configuration/security");
        permitAll.add("/webjars/**");
        permitAll.add("/error");
        permitAll.add("/v2/**");
        String[] urls = permitAll.stream().distinct().toArray(String[]::new);
        http.exceptionHandling()
                //未授权处理
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().authorizeRequests()
                .antMatchers(urls).permitAll()
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .logout().logoutUrl("/logout")
                .and()
                //.addLogoutHandler(new TokenLogoutHandler(tokenManager))
                .addFilter(new TokenLoginFilter(authenticationManager()))
                .addFilter(new TokenAuthenticationFilter(authenticationManager())).httpBasic();
    }
    /**
     * 密码处理
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //用于构建验证逻辑
        //userDetailsService默认采用自带的DaoAuthenticationProvider
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
}
