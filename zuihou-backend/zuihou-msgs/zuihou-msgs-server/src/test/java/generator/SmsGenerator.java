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
//public class SmsGenerator {
//    public static void main(String[] args) {
//        CodeGeneratorConfig build = CodeGeneratorConfig.
//                build("msgs", "sms", "zuihou",
//                        "",
////                        Arrays.asList("sms_.*"));
//                        Arrays.asList("sms_task"));
//        build.setUrl("jdbc:mysql://127.0.0.1:3306/zuihou_msgs_dev?useUnicode=true&useSSL=false&characterEncoding=utf8");
////        build.setPassword("root");
//        build.setProjectRootPath(System.getProperty("user.dir") + "/zuihou-backend/zuihou-msgs");
//
//        FileCreateConfig fileCreateConfig = new FileCreateConfig(null);
////        FileCreateConfig fileCreateConfig = new FileCreateConfig(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateEntity(GenerateType.ADD);
//        fileCreateConfig.setGenerateEnum(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateDto(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateXml(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateDao(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateServiceImpl(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateService(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateController(GenerateType.ADD);
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
