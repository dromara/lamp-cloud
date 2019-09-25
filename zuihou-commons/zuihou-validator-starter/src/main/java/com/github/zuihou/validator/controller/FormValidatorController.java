package com.github.zuihou.validator.controller;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.zuihou.validator.extract.IConstraintExtract;
import com.github.zuihou.validator.model.FieldValidatorDesc;
import com.github.zuihou.validator.model.ValidConstraint;
import com.github.zuihou.validator.wrapper.HttpServletRequestValidatorWrapper;

import cn.hutool.core.util.StrUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


/**
 * 统一获取校验规则入口。<br>
 * <br>
 * 加入了一个前端验证接口控制器，专门处理所有的拉取表单验证规则的请求。<br>
 * 在拉取表单验证规则的时候，有两种拉取方式<br>
 * 第一种如下（通过路径变量来传输要拉取的uri路径） ：<br>
 * A表单的保存url为       http://2.8.1.1:3/porjectName/role/save <br>
 * 那么A表单的验证url：http://2.8.1.1:3/porjectName/from<font color="red">/validator/role</font>/save <br>
 * <br>
 * 仅仅追加了<font color="red">/from/validator/</font>而已。<br>
 * <br>
 * 第二种如下是（通过参数传递uri路径的方式来拉取）：<br>
 * 表单保存url ：    <br>
 * http://2.8.1.1:3/porjectName/role/save <br>
 * 那么验证url：<br/>
 * http://2.8.1.1:3/porjectName/from/validator?fromPath=/porjectName/role/save <br>
 * <br>
 * 固定了验证uri地址，而要验证的表单地址作为参数进行传输。当然，可以一次性拿多个表单验证地址。有些界面可能同时存在多个表单需要提交。
 * <br>
 * <p>
 * <p>
 * Bean Validation 中内置的 constraint
 *
 * <p>
 *
 * @author zuihou
 * @Null 被注释的元素必须为 null
 * @NotNull 被注释的元素必须不为 null
 * @AssertTrue 被注释的元素必须为 true
 * @AssertFalse 被注释的元素必须为 false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 * </p>
 * <p>
 * Hibernate Validator 附加的 constraint
 * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 * @Email 被注释的元素必须是电子邮箱地址
 * @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 * @NotEmpty 被注释的字符串的必须非空
 * @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 * <p>
 * @date 2019-07-12 14:30
 */
@RequestMapping
public class FormValidatorController {

    private final static String FORM_VALIDATOR_URL = "/form/validator";
    final private RequestMappingHandlerMapping requestMappingHandlerMapping;
    final private IConstraintExtract constraintExtract;

    public FormValidatorController(IConstraintExtract constraintExtract, RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.constraintExtract = constraintExtract;
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    /**
     * 支持第一种拉取方式
     *  注意： 具体的方法必须在参数上面标注 @Validated 才有效
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(FORM_VALIDATOR_URL + "/**")
    @ResponseBody
    public Collection<FieldValidatorDesc> standardByPathVar(HttpServletRequest request) throws Exception {
        String requestUri = request.getRequestURI();
        String formPath = StrUtil.subAfter(requestUri, FORM_VALIDATOR_URL, false);
        return localFieldValidatorDescribe(request, formPath);
    }

    /**
     * 支持第二种拉取方式
     *
     * @param formPath
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(FORM_VALIDATOR_URL)
    @ResponseBody
    public Collection<FieldValidatorDesc> standardByQueryParam(@RequestParam(value = "formPath", required = false) String formPath, HttpServletRequest request) throws Exception {
        return localFieldValidatorDescribe(request, formPath);
    }

    private Collection<FieldValidatorDesc> localFieldValidatorDescribe(HttpServletRequest request, String formPath) throws Exception {
        HandlerExecutionChain chains = requestMappingHandlerMapping.getHandler(new HttpServletRequestValidatorWrapper(request, formPath));
        if (chains == null) {
            return Collections.emptyList();
        }
        HandlerMethod method = (HandlerMethod) chains.getHandler();
        if (method == null) {
            return Collections.emptyList();
        }
        return loadValidatorDescribe(method);
    }

    /**
     * 官方验证规则： （可能还不完整）
     * A, 普通对象形：
     * B、@RequestBody形式：
     * <p>
     * 1，类或方法或参数上有 @Validated
     * 2，参数有 @Valid
     *
     * <p>
     * C、普通参数形式：
     * 类上有 有 @Validated
     * 参数有 任意注解
     *
     * <p>
     * 步骤：
     * 1，先判断类上是否存在
     * 2，判断方法上是否存在
     * 3，判断
     *
     * @param handlerMethod
     * @return
     * @throws Exception
     */
    private Collection<FieldValidatorDesc> loadValidatorDescribe(HandlerMethod handlerMethod) throws Exception {
        Method method = handlerMethod.getMethod();
        Parameter[] methodParams = method.getParameters();

        if (methodParams == null || methodParams.length < 1) {
            return Collections.EMPTY_LIST;
        }
        ArrayList<Parameter> validateParams = new ArrayList<>(methodParams.length);
        for (Parameter methodParameter : methodParams) {
            //在参数和类上面找注解
            if (methodParameter.getAnnotation(Validated.class) != null
                    || method.getDeclaringClass().getAnnotation(Validated.class) != null) {
                validateParams.add(methodParameter);
            }
        }
        if (validateParams.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        Validated classValidated = method.getDeclaringClass().getAnnotation(Validated.class);

        List<ValidConstraint> validatorStandard = new ArrayList<>();
        for (Parameter methodParameter : validateParams) {
            Validated validate = methodParameter.getAnnotation(Validated.class);
            if (validate == null) {
                validate = classValidated;
            }
            if (validate != null) {
                validatorStandard.add(new ValidConstraint(methodParameter.getType(), validate.value()));
            } else {
                validatorStandard.add(new ValidConstraint(methodParameter.getType()));
            }
        }

        return constraintExtract.extract(validatorStandard);
    }
}
