package com.iric.mybatisPlusGenerator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.iric.mybatisPlusGenerator.BaseGeneratorTest;

import java.sql.SQLException;
import java.util.Collections;

public class FastAutoGeneratorTest extends BaseGeneratorTest {

    // private static final String URL = "jdbc:mysql://192.168.10.14:3306/yiling_dev?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";
    //
    // private static final String USERNAME = "yiling_dev";
    //
    // private static final String PASSWORD = "yiling_dev@123";

    private static final String URL = "jdbc:mysql://192.168.10.6:11066/pms?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Yiling123";

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://192.168.10.14:3306/yiling_dev?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT",
            "yiling_dev",
            "yiling_dev@123");


    public static void main(String[] args) throws SQLException {
        String srcPath = System.getProperty("user.dir") + "/src/main/java/";// 项目源码绝对路径;//项目源码绝对路径
        String resourcesPath = System.getProperty("user.dir") + "/src/main/resources";

        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("yu.xing") // 设置作者
                            .dateType(DateType.TIME_PACK)// 设置时间类型
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(srcPath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.iric.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, resourcesPath + "/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude( "pms_message_push_log") // 设置需要生成的表名
                            .addTablePrefix(); // 设置过滤表前缀
                    builder.entityBuilder() // 设置entity
//                            .enableColumnConstant()
                            .enableChainModel()
                            .enableLombok();
                            //.enableTableFieldAnnotation();
                    builder.controllerBuilder() // 设置controller
                            .enableRestStyle();
                    builder.serviceBuilder() // 设置service
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp");
                    builder.mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");

                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> {
                    builder.entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .controller("/templates/controller.java");
                })
                .execute();
    }
}
