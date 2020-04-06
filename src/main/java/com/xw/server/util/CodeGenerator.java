/*
package com.xw.server.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

*/
/**
 * mybatis plus 提供的代码生成器
 * 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码
 *
 * @link https://mp.baomidou.com/guide/generator.html
 *//*

public class CodeGenerator {

    // 数据库 URL
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/myxy?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    // 数据库驱动
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    // 数据库用户名
    private static final String USERNAME = "root";
    // 数据库密码
    private static final String PASSWORD = "123";
    // @author 值
    private static final String AUTHOR = "xw.z";
    // 包的基础路径
    private static final String BASE_PACKAGE_URL = "com.xw";
    //private static final String BASE_PACKAGE_URL = "com.middle.manager.station";
    //private static final String PROJECT_GENERATOR ="/generator";

    //private static final String PROJECT_COURSE ="/manager-station";
    private static final String PROJECT_COURSE ="";

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + PROJECT_COURSE;
        globalConfig.setActiveRecord(false); //开启AR模式
        globalConfig.setOutputDir(projectPath + "/src/main/java"); //生成路径(一般都是生成在此项目的src/main/java下面)
        globalConfig.setAuthor(AUTHOR); //设置作者
        globalConfig.setFileOverride(false);//第二次生成会把第一次生成的覆盖掉
        globalConfig.setServiceName("%sService");//生成的service接口名字首字母是否为I，这样设置就没有I
        globalConfig.setBaseResultMap(true);//生成resultMap
        globalConfig.setBaseColumnList(true);//在xml中生成基础列
        globalConfig.setIdType(IdType.AUTO);//主键策略
        globalConfig.setSwagger2(true); // 实体属性 Swagger2 注解
        globalConfig.setOpen(false);
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        //packageConfig.setModuleName("gen"); //模块名
        packageConfig.setParent(BASE_PACKAGE_URL);
        generator.setPackageInfo(packageConfig);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        // 配置自定义代码模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//下划线到驼峰的命名方式
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名"));
        //strategy.setSuperEntityColumns("id"); // 写于父类中的公共字段
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_");
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("请输入" + tip + "："));
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) return ipt;
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
*/
