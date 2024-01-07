package top.tangyh.lamp.msg.glue;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.runtime.InvokerHelper;
import top.tangyh.lamp.msg.glue.impl.SpringGlueFactory;
import top.tangyh.lamp.msg.strategy.MsgStrategy;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * glue factory, product class/object by name
 *
 * @author zuihou
 */
public class GlueFactory {


    private final static ConcurrentMap<String, Class<?>> CLASS_CACHE = new ConcurrentHashMap<>();
    private static GlueFactory glueFactory = new SpringGlueFactory();
    /**
     * groovy class loader
     */
    private final GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    public static GlueFactory getInstance() {
        return glueFactory;
    }

    public static void refreshInstance(int type) {
        if (type == 0) {
            glueFactory = new GlueFactory();
        } else if (type == 1) {
            glueFactory = new SpringGlueFactory();
        }
    }

    /**
     * 加载groovy脚本，并实例化
     *
     * @param script groovy脚本
     * @return
     * @throws Exception
     */
    public MsgStrategy loadNewInstance(String script) throws Exception {
        if (script != null && script.trim().length() > 0) {
            Class<?> clazz = getCodeSourceClass(script);
            if (clazz != null) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                if (instance instanceof MsgStrategy inst) {
                    this.injectService(inst);
                    return inst;
                } else {
                    throw new IllegalArgumentException("glue 加载失败，"
                            + "无法将实例转换 [" + instance.getClass() + "] 为 MsgStrategy");
                }
            }
        }
        throw new IllegalArgumentException("脚本不能为空");
    }

    /**
     * 执行脚本
     *
     * @param script script
     * @param params params
     * @return java.lang.Object
     * @author tangyh
     * @date 2022/7/25 9:35 PM
     * @create [2022/7/25 9:35 PM ] [tangyh] [初始创建]
     * @update [2022/7/25 9:35 PM ] [tangyh] [变更描述]
     */
    public Object exeGroovyScript(String script, Map<String, Object> params) {
        if (script != null && script.trim().length() > 0) {
            Class<?> clazz = getCodeSourceClass(script);
            if (clazz != null) {
                return InvokerHelper.createScript(clazz, new Binding(params)).run();
            }
        }
        throw new IllegalArgumentException("脚本不能为空");
    }

    private Class<?> getCodeSourceClass(String codeSource) {
        try {
            // md5
            byte[] md5 = MessageDigest.getInstance("MD5").digest(codeSource.getBytes());
            String md5Str = new BigInteger(1, md5).toString(16);

            Class<?> clazz = CLASS_CACHE.get(md5Str);
            if (clazz == null) {
                clazz = groovyClassLoader.parseClass(codeSource);
                CLASS_CACHE.putIfAbsent(md5Str, clazz);
            }
            return clazz;
        } catch (Exception e) {
            return groovyClassLoader.parseClass(codeSource);
        }
    }

    /**
     * 注入bean字段
     *
     * @param instance
     */
    public void injectService(Object instance) {
        // do something
    }

}
