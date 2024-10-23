package com.coder.auto_rental;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest(classes = com.coder.AppServer.class)
public class GeneratorCode {
    private static final String AUTHOR = "zdd";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "1234";
    private static final String OUT_DIR = ".\\src\\main\\java";
    private static final String PACKAGE_NAME = "com.coder";
    private static final String MODULE_NAME = "auto_rental";
    private static final String[] TABLES = {"auto_maker", "auto_brand", "auto_info", "sys_dept", "sys_permission", "sys_role", "sys_user", "sys_role_permission", "sys_user_role", "busi_customer", "busi_maintain", "busi_violation", "busi_order", "busi_rental_type"};
    private static final String[] PREFIX = {"busi", "sys"};

    @Test
    void generatorCode() {
        FastAutoGenerator.create(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR)
                            // 开启swagger支持
                            .enableSwagger().outputDir(OUT_DIR);
                })
                // 配置包信息
                .packageConfig(builder -> {
                    builder.parent(PACKAGE_NAME).moduleName(MODULE_NAME)
                            // 配置xml文件输出路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, ".\\src\\main\\resources\\ mappser"));
                })
                // 配置生成策略，包括要包含的表、表名前缀等
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES)
                           .addTablePrefix(PREFIX)
                            // 配置实体类、控制器等的生成选
                            .entityBuilder().enableLombok().enableChainModel().controllerBuilder().enableRestStyle();
//                    builder.addInclude("busy_rental_type","sys_user_role").entityBuilder().enableLombok().controllerBuilder().enableRestStyle();
                })
                // 设置使用的模板引擎为Freemarker
                .templateEngine(new FreemarkerTemplateEngine())
                // 执行代码生成
                .execute();

    }
}
