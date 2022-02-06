package com.gcc.oa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @className: MultipartResolver
 * @author: 小李探花
 * @date: 2022/1/26 18:20
 * @description:
 */
@Configuration
public class MultipartResolverConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(1024000000000L);
        return multipartResolver;
    }
}
