package cn.lu.vblog;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VBlogApplicationTests {


    @Test
    void contextLoads() {
        String path = System.getProperty("user.dir");
        //设置数据源
        DataSourceConfig.Builder dsc = new DataSourceConfig.Builder(
                "jdbc:mysql://localhost:3306/vblog?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8",
                "root",
                "Lu6218959?");
        GlobalConfig gc = new GlobalConfig.Builder()
                .outputDir(path + "/src/main/java")
                .author("lkxBruce")
                .enableSwagger()
                .fileOverride()
                .dateType(DateType.ONLY_DATE)
                .build();
        PackageConfig pc = new PackageConfig.Builder()
                .moduleName("vblog")
                .parent("cn.lu")
                .entity("entity")
                .mapper("mapper")
                .service("service")
                .controller("controller").build();

        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addInclude("content")
                .entityBuilder()
                .versionColumnName("version")
                .enableLombok()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .addTableFills(new Column("gmt_create", FieldFill.INSERT))
                .addTableFills(new Column("gmt_modified", FieldFill.INSERT_UPDATE))
                .logicDeleteColumnName("delete")
                .versionColumnName("version")
                .controllerBuilder()
                .enableRestStyle()
                .enableHyphenStyle()
                .build();

        AutoGenerator generator = new AutoGenerator(dsc.build());
        generator.strategy(strategyConfig)
                .global(gc)
                .packageInfo(pc);
        generator.execute();
    }
}
