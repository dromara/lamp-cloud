//package generator;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//import com.github.zuihoou.generator.CodeGenerator;
//import com.github.zuihoou.generator.config.CodeGeneratorConfig;
//import com.github.zuihoou.generator.config.FileCreateConfig;
//import com.github.zuihoou.generator.type.EntityFiledType;
//import com.github.zuihoou.generator.type.EntityType;
//import com.github.zuihoou.generator.type.GenerateType;
//
///**
// * 权限代码生成器
// *
// * @author zuihou
// * @date 2019/05/25
// */
//public class AuthorityGenerator {
//    public static void main(String[] args) {
//        CodeGeneratorConfig build = CodeGeneratorConfig.
//                build("authority", "", "zuihou",
////                        "c_common_",
//                        "c_core_",
////                        "c_auth_",
//                        Arrays.asList("c_core_.*"));
////                        Arrays.asList("c_common_opt_log"));
////                        Arrays.asList("c_common_.*"));
////                        Arrays.asList("c_auth_.*"));
////                        Arrays.asList("c_core_org"));
////                        Arrays.asList("c_auth_user_role", "c_auth_role_authority", "c_auth_role_org"));
//        build.setUrl("jdbc:mysql://127.0.0.1:3306/zuihou_authority_dev?useUnicode=true&useSSL=false&characterEncoding=utf8");
////        build.setPassword("root");
//        build.setProjectRootPath(System.getProperty("user.dir") + "/zuihou-backend/zuihou-authority");
//
//        FileCreateConfig fileCreateConfig = new FileCreateConfig(null);
////        FileCreateConfig fileCreateConfig = new FileCreateConfig(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateEntity(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateEnum(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateDto(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateXml(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateDao(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateServiceImpl(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateService(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateController(GenerateType.IGNORE);
//
//        build.setFileCreateConfig(fileCreateConfig);
//
////        build.setChildPackageName("auth");
//        build.setChildPackageName("core");
////        build.setChildPackageName("common");
//        build.setSuperEntity(EntityType.ENTITY);
////        build.setSuperEntity(EntityType.SUPER_ENTITY);
//
//        //手动指定枚举类 生成的路径
//        Set<EntityFiledType> filedTypes = new HashSet<>();
//        filedTypes.addAll(Arrays.asList(
//                EntityFiledType.builder().name("httpMethod").table("c_common_opt_log")
//                        .packagePath("com.github.zuihou.common.enums.HttpMethod").gen(GenerateType.IGNORE).build()
//                , EntityFiledType.builder().name("httpMethod").table("c_auth_resource")
//                        .packagePath("com.github.zuihou.common.enums.HttpMethod").gen(GenerateType.IGNORE).build()
//                , EntityFiledType.builder().name("dsType").table("c_auth_role")
//                        .packagePath("com.github.zuihou.database.mybatis.auth.DataScopeType").gen(GenerateType.IGNORE).build()
//        ));
//        build.setFiledTypes(filedTypes);
//        CodeGenerator.run(build);
//    }
//}
