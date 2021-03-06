package cn.lu.vblog;

import cn.lu.vblog.entity.Content;
import cn.lu.vblog.mapper.AdminUserMapper;
import cn.lu.vblog.mapper.ContentMapper;
import cn.lu.vblog.service.ContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VBlogApplicationTests {


//    @Test
//    void contextLoads() {
//        String path = System.getProperty("user.dir");
//        //设置数据源
//        DataSourceConfig.Builder dsc = new DataSourceConfig.Builder(
//                "jdbc:mysql://localhost:3306/vblog?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8",
//                "root",
//                "Lu6218959?");
//        //连接数据库
//        GlobalConfig gc = new GlobalConfig.Builder()
//                .outputDir(path + "/src/main/java")
//                .author("lkxBruce")
//                .enableSwagger()
//                .fileOverride()
//                .dateType(DateType.ONLY_DATE)
//                .build();
//        //全局配置
//        PackageConfig pc = new PackageConfig.Builder()
//                .moduleName("vblog")
//                .parent("cn.lu")
//                .entity("entity")
//                .mapper("mapper")
//                .service("service")
//                .controller("controller").build();
//        //创建包配置
//        StrategyConfig strategyConfig = new StrategyConfig.Builder()
//                .addInclude("content")
//                //创建的数据库
//                .entityBuilder()
//                //实体配置
//                .versionColumnName("version")
//                //乐观锁列名
//                .enableLombok()
//                //配置Lombok注解
//                .naming(NamingStrategy.underline_to_camel)
//                //驼峰标识命名
//                .columnNaming(NamingStrategy.underline_to_camel)
//                .addTableFills(new Column("gmt_create", FieldFill.INSERT))
//                .addTableFills(new Column("gmt_modified", FieldFill.INSERT_UPDATE))
//                //自动填充
//                .logicDeleteColumnName("delete")
//                //软删除
//                .controllerBuilder()
//                //控制层配置
//                .enableRestStyle()
//                .enableHyphenStyle()
//                .build();
//
//        AutoGenerator generator = new AutoGenerator(dsc.build());
//        generator.strategy(strategyConfig)
//                .global(gc)
//                .packageInfo(pc);
//        generator.execute();
//    }

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private ContentService contentService;

    @Test
    void contextLoads() {
//        AdminUser user = adminUserMapper.selectById(2L);
//        user.setUsername("kx");
////        user.setPassword("123456");
////        user.setActive(1);
////        user.setAvatarUrl("");
////        user.setHomeUrl("");
////        user.setVersion(1);
////        user.setEmail("1262241648@qq.com");
////        adminUserMapper.insert(user);
//        adminUserMapper.updateById(user);
        PageHelper.startPage(1,15);
        List<Content> contents = contentMapper.selectList(null);
        PageInfo<Content> contentPageInfo = new PageInfo<>(contents);
        contentPageInfo.getNextPage();
    }
}
