package top.tangyh.lamp.generator.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import freemarker.template.Configuration;
import freemarker.template.Template;
import top.tangyh.lamp.generator.enumeration.PopupTypeEnum;
import top.tangyh.lamp.generator.enumeration.TemplateEnum;
import top.tangyh.lamp.generator.enumeration.TplEnum;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板工具类
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/4/2 7:36 PM
 * @create [2022/4/2 7:36 PM ] [tangyh] [初始创建]
 */
public class TemplateUtils {
    private static final Configuration CONFIGURATION;

    static {
        CONFIGURATION = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        CONFIGURATION.setDefaultEncoding(StandardCharsets.UTF_8.name());
        CONFIGURATION.setClassForTemplateLoading(SourceCodeUtils.class, StringPool.SLASH);
    }

    public static Template getTemplate(String templatePath) throws IOException {
        return CONFIGURATION.getTemplate(templatePath);
    }

    /**
     * 获取模板列表
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(TemplateEnum template, TplEnum tplType, PopupTypeEnum popupType) {
        List<String> templates = new ArrayList<>();
        if (TemplateEnum.BACKEND.eq(template)) {
            templates.add(GenCodeConstant.TEMPLATE_CONTROLLER);
            templates.add(GenCodeConstant.TEMPLATE_SERVICE);
            templates.add(GenCodeConstant.TEMPLATE_SERVICE_IMPL);
            templates.add(GenCodeConstant.TEMPLATE_MANAGER);
            templates.add(GenCodeConstant.TEMPLATE_MANAGER_IMPL);
            templates.add(GenCodeConstant.TEMPLATE_MAPPER);
            templates.add(GenCodeConstant.TEMPLATE_XML);
            templates.add(GenCodeConstant.TEMPLATE_ENTITY_JAVA);
            templates.add(GenCodeConstant.TEMPLATE_SAVE_VO);
            templates.add(GenCodeConstant.TEMPLATE_UPDATE_VO);
            templates.add(GenCodeConstant.TEMPLATE_RESULT_VO);
            templates.add(GenCodeConstant.TEMPLATE_PAGE_QUERY);
            templates.add(GenCodeConstant.TEMPLATE_SQL);
        } else if (TemplateEnum.WEB_PLUS.eq(template)) {
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_API);
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_MODEL);
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_LANG_EN);
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_LANG_ZH);
            templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_DATA);

            if (TplEnum.TREE.eq(tplType)) {
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_INDEX);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_EDIT);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_TREE_TREE);
            } else if (TplEnum.MAIN_SUB.eq(tplType)) {
                if (PopupTypeEnum.JUMP.eq(popupType)) {
                    templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_JUMP_EDIT);
                } else {
                    templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_EDIT);
                }
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_INDEX);
                // 从表
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_SUB_INDEX);
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_MAIN_SUB_DATA);

            } else {
                if (PopupTypeEnum.JUMP.eq(popupType)) {
                    templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_JUMP_EDIT);
                } else {
                    templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_EDIT);
                }
                templates.add(GenCodeConstant.TEMPLATE_WEB_PRO_SIMPLE_INDEX);
            }
        }
        return templates;
    }

    /**
     * 获取模板列表
     *
     * @return 模板列表
     */
    public static List<String> getSubTemplateList(TemplateEnum template) {
        List<String> templates = new ArrayList<>();
        if (TemplateEnum.BACKEND.eq(template)) {
            templates.add(GenCodeConstant.TEMPLATE_MANAGER);
            templates.add(GenCodeConstant.TEMPLATE_MANAGER_IMPL);
            templates.add(GenCodeConstant.TEMPLATE_MAPPER);
            templates.add(GenCodeConstant.TEMPLATE_XML);
            templates.add(GenCodeConstant.TEMPLATE_ENTITY_JAVA);
            templates.add(GenCodeConstant.TEMPLATE_SAVE_VO);
            templates.add(GenCodeConstant.TEMPLATE_UPDATE_VO);
        }
        return templates;
    }

}
