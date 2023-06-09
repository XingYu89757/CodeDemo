//package com.iric;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.Collections;
//
//public class FastAutoGeneratorTest extends BaseGeneratorTest {
//
//    /**
//     * 数据源配置
//     */
//    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
//            .Builder("jdbc:mysql://192.168.100.245:3307/andun_hospital_service?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT", "andunroot", "andun12180680#");
//
//
//    public static void main(String[] args) throws SQLException {
//        String srcPath = System.getProperty("user.dir") + "/src/main/java/";//项目源码绝对路径;//项目源码绝对路径
//        FastAutoGenerator.create("jdbc:mysql://192.168.100.245:3307/andun_hospital_service?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT", "andunroot", "andun12180680#")
//                .globalConfig(builder -> {
//                    builder.author("xingyu") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(srcPath); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.mybatisplus.generator") // 设置父包名
//                            .moduleName("system") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml,srcPath)); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("t_doctor_dictionary") // 设置需要生成的表名
//                            .addTablePrefix(""); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//}
