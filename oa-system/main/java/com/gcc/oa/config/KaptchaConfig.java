package com.gcc.oa.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @className: KaptchaConfig
 * @author: 小李探花
 * @date: 2022/1/26 18:30
 * @description:
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptche() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "150");
        properties.setProperty("kaptcha.image.height", "42");
        properties.setProperty("kaptcha.textproducer.font.size", "38");
        properties.setProperty("kaptcha.textproducer.font.color", "DARK_GRAY");
        properties.setProperty("kaptcha.noise.color", "CYAN");
        properties.setProperty("kaptcha.textproducer.char.string", "uvbilmnos1234567890");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.background.clear.from", "PINK");
        properties.setProperty("kaptcha.background.clear.to", "WHITE");
        properties.setProperty("kaptcha.textproducer.char.space", "8");
        //宋体,楷体,微软雅黑
        properties.setProperty("kaptcha.textproducer.font.names", "Verdana");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
