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
//public class MsgsGenerator {
//    public static void main(String[] args) {
//        CodeGeneratorConfig build = CodeGeneratorConfig.
//                build("msgs", "msgs", "zuihou",
//                        "",
//                        Arrays.asList("msgs_.*"));
////                        Arrays.asList("c_auth_user_role", "c_auth_role_authority", "c_auth_role_org"));
//        build.setUrl("jdbc:mysql://127.0.0.1:3306/zuihou_msgs_dev?useUnicode=true&useSSL=false&characterEncoding=utf8");
////        build.setPassword("root");
//        build.setProjectRootPath(System.getProperty("user.dir") + "/zuihou-backend/zuihou-msgs");
//
//        FileCreateConfig fileCreateConfig = new FileCreateConfig(null);
////        FileCreateConfig fileCreateConfig = new FileCreateConfig(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateEntity(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateEnum(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateDto(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateXml(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateDao(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateServiceImpl(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateService(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateController(GenerateType.IGNORE);
//
//        build.setFileCreateConfig(fileCreateConfig);
//
//        build.setChildPackageName("");
//        build.setSuperEntity(EntityType.ENTITY);
////        build.setSuperEntity(EntityType.SUPER_ENTITY);
//
//        //手动指定枚举类 生成的路径
//        Set<EntityFiledType> filedTypes = new HashSet<>();
////        filedTypes.addAll(Arrays.asList());
//        build.setFiledTypes(filedTypes);
//        CodeGenerator.run(build);
//    }
//}
