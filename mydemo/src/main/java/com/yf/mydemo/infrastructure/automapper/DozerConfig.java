package com.yf.mydemo.infrastructure.automapper;

import com.alibaba.druid.pool.DruidDataSource;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration

public class DozerConfig {
    private String url;
    @Bean
    @ConfigurationProperties(prefix = "dozer")
    public DozerBeanMapperFactoryBean getMapperFactory(){
        return new DozerBeanMapperFactoryBean();
    }
    /*    @Bean
    public Mapper getDozerMapper(@Value("classpath*:mappings/*.xml") Resource[] resources) {
        List<String> fileNameList = Arrays.stream(resources).map(resource -> "mappings/"+resource.getFilename()).collect(Collectors.toList());
        Mapper mapper = DozerBeanMapperBuilder.create().withMappingFiles(fileNameList).build();
        return mapper;
    }*/
}
