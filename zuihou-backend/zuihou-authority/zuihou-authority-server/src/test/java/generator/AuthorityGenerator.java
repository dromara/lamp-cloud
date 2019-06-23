package generator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.github.zuihoou.generator.CodeGenerator;
import com.github.zuihoou.generator.config.CodeGeneratorConfig;
import com.github.zuihoou.generator.config.FileCreateConfig;
import com.github.zuihoou.generator.type.EntityFiledType;
import com.github.zuihoou.generator.type.EntityType;
import com.github.zuihoou.generator.type.GenerateType;

/**
 * This is a Description
 *
 * @author tangyh
 * @date 2019/05/25
 */
public class AuthorityGenerator {
    public static void main(String[] args) {
        CodeGeneratorConfig build = CodeGeneratorConfig.
                build("authority", "", "zuihou",
                        "c_auth_",
//                        "c_common_",
//                build("msgs", "sms", "tangyh", "",
//                        Arrays.asList("c_auth_.*"));
                        Arrays.asList("c_auth_menu"));
//                        Arrays.asList("c_common_.*"));
//                        Arrays.asList("c_auth_user_role", "c_auth_role_resource", "c_auth_authorized_api_resource"));
        build.setUrl("jdbc:mysql://127.0.0.1:3306/zuihou_authority_dev?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        build.setPassword("root");
        build.setProjectRootPath("/Users/tangyh/githubspace/zuihou-admin-cloud/zuihou-backend/zuihou-authority");
//        build.setProjectRootPath(System.getProperty("user.dir") + "/codeByCloud_V3.0/backend/cloud-authority-new");

//        FileCreateConfig fileCreateConfig = new FileCreateConfig(null);
        FileCreateConfig fileCreateConfig = new FileCreateConfig(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateEntity(GenerateType.OVERRIDE);
        fileCreateConfig.setGenerateEnum(GenerateType.IGNORE);
        fileCreateConfig.setGenerateDto(GenerateType.IGNORE);
        fileCreateConfig.setGenerateXml(GenerateType.IGNORE);
        fileCreateConfig.setGenerateDao(GenerateType.IGNORE);
        fileCreateConfig.setGenerateServiceImpl(GenerateType.IGNORE);
        fileCreateConfig.setGenerateService(GenerateType.IGNORE);
        fileCreateConfig.setGenerateController(GenerateType.IGNORE);

        build.setFileCreateConfig(fileCreateConfig);

        build.setChildPackageName("auth");
//        build.setChildPackageName("common");
        build.setSuperEntity(EntityType.ENTITY);
//        build.setSuperEntity(EntityType.SUPER_ENTITY);

        //手动指定枚举类 生成的路径
        Set<EntityFiledType> filedTypes = new HashSet<>();
        filedTypes.addAll(Arrays.asList(
                EntityFiledType.builder().name("httpMethod").packagePath("com.github.zuihou.common.enums.HttpMethod").gen(GenerateType.IGNORE).build()
        ));
        build.setFiledTypes(filedTypes);
        CodeGenerator.main(build);
    }
}
