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
// * 文件代码生成类
// *
// * @author zuihou
// * @date 2019/05/25
// */
//public class FileGenerator {
//    public static void main(String[] args) {
//        CodeGeneratorConfig build = CodeGeneratorConfig.
//                build("file", "", "zuihou",
//                        "f_",
////                        Arrays.asList("f_file"));
//                        Arrays.asList("f_.*"));
//        build.setUrl("jdbc:mysql://127.0.0.1:3306/zuihou_file_dev?useUnicode=true&useSSL=false&characterEncoding=utf8");
////        build.setPassword("root");
//        build.setProjectRootPath(System.getProperty("user.dir") + "/zuihou-backend/zuihou-file");
//
////        FileCreateConfig fileCreateConfig = new FileCreateConfig(null);
//        FileCreateConfig fileCreateConfig = new FileCreateConfig(GenerateType.OVERRIDE);
//        fileCreateConfig.setGenerateEntity(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateEnum(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateDto(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateXml(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateDao(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateServiceImpl(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateService(GenerateType.IGNORE);
//        fileCreateConfig.setGenerateController(GenerateType.OVERRIDE);
//
//        build.setFileCreateConfig(fileCreateConfig);
//
//        build.setChildPackageName("");
//        build.setSuperEntity(EntityType.ENTITY);
////        build.setSuperEntity(EntityType.SUPER_ENTITY);
//
//        //手动指定枚举类 生成的路径
//        Set<EntityFiledType> filedTypes = new HashSet<>();
//        filedTypes.addAll(Arrays.asList(
////                EntityFiledType.builder().name("httpMethod").packagePath("com.github.zuihou.common.enums.HttpMethod").gen(GenerateType.IGNORE).build()
//        ));
//        build.setFiledTypes(filedTypes);
//        CodeGenerator.run(build);
//    }
//}
