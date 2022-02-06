package com.gcc.oa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 小李探花
 * 程序入口
 */
@SpringBootApplication
@MapperScan("com.gcc.oa.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.err.println("项目部署成功");
    }

}
