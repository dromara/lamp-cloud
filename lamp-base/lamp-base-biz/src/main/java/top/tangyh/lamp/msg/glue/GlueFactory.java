package top.tangyh.lamp.msg.glue;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.tangyh.basic.exception.BizException;
import top.tangyh.lamp.msg.glue.impl.SpringGlueFactory;
import top.tangyh.lamp.msg.strategy.MsgStrategy;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/**
 * glue factory, product class/object by name
 *
 * @author zuihou
 */
public class GlueFactory {

    private static final Logger log = LoggerFactory.getLogger(GlueFactory.class);

    private static final ConcurrentMap<String, Class<?>> CLASS_CACHE = new ConcurrentHashMap<>();
    private static GlueFactory glueFactory = new SpringGlueFactory();

    /**
     * 危险 Token 黑名单。
     * 脚本源码被剥离空白和字符串字面量后，如果仍包含以下任意 Token 则拒绝执行。
     * <p>
     * SecureASTCustomizer.setDisallowedReceiversClasses 对动态 Groovy 无效
     * （方法调用在运行时通过 Meta-Object Protocol 分派，AST 层拿不到 receiver 类型），
     * 因此改用源码级 Token 扫描作为主要安全屏障。
     */
    private static final Set<String> DANGEROUS_TOKENS = Set.of(
            // ---- 进程执行 ----
            "Runtime", "ProcessBuilder", "Process",
            // ---- 文件 / IO ----
            "File", "FileWriter", "FileReader", "FileInputStream", "FileOutputStream",
            "BufferedReader", "BufferedWriter", "InputStreamReader", "OutputStreamWriter",
            "RandomAccessFile", "FileDescriptor",
            // ---- 反射 ----
            "Class.forName", "getMethod", "getDeclaredMethod", "getDeclaredConstructor",
            "getDeclaredField", "getConstructor", "invoke", "newInstance",
            "setAccessible", "getDeclaredFields", "getDeclaredMethods",
            // ---- 类加载 / 脚本引擎（防止自引用逃逸）----
            "ClassLoader", "URLClassLoader", "GroovyClassLoader", "GroovyShell",
            "GroovyScriptEngine", "CompilerConfiguration", "defineClass",
            // ---- Groovy 动态绕过技术 ----
            "metaClass", "ExpandoMetaClass", "Expando",
            "@ASTTest", "@Grab", "@GrabConfig",
            // ---- 系统操作 ----
            "System.exit", "System.getenv", "System.setProperty",
            "System.getProperties", "System.load", "System.loadLibrary",
            // ---- 网络 ----
            "Socket", "ServerSocket", "DatagramSocket",
            // ---- 序列化 / 反序列化 ----
            "ObjectInputStream", "readObject", "readUnshared",
            // ---- 通用危险方法名 ----
            "exec", "execute"
    );

    /**
     * 危险调用模式（正则匹配，覆盖 Token 扫描可能遗漏的组合写法）
     */
    private static final Pattern[] DANGEROUS_CALL_PATTERNS = {
            // .execute() / .exec() 方法调用（含 Groovy GDK 扩展方法）
            Pattern.compile("\\.execute\\s*\\("),
            Pattern.compile("\\.exec\\s*\\("),
            // 字符串插值内嵌表达式（可拼接出动态类名）
            Pattern.compile("\\$\\{.*\\b(execute|exec|Runtime|Process|File|invoke|newInstance)\\b"),
            // 通过字符串动态获取类引用（绕过 Token 扫描）
            Pattern.compile("\\.forName\\s*\\("),
            // Annotation 驱动的编译期代码执行
            Pattern.compile("@ASTTest"),
            Pattern.compile("@Grab\\b")
    };

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
     * 计算SHA256哈希值（Hex格式）
     *
     * @param input 输入字符串
     * @return {@link String} SHA256哈希值（Hex格式）
     * @throws Exception
     */
    private static String sha256Hex(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(digest).toLowerCase();
    }

    /**
     * 加载groovy脚本，并实例化
     *
     * @param script groovy脚本
     * @return
     * @throws Exception
     */
    public MsgStrategy loadNewInstance(String script) throws Exception {
        if (script != null && !script.trim().isEmpty()) {
            checkScriptSafety(script);
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
        throw BizException.wrap("脚本不能为空");
    }

    /**
     * 执行脚本
     *
     * @param script script
     * @param params params
     * @return java.lang.Object
     * @author henhen
     * @date 2022/7/25 9:35 PM
     */
    public Object exeGroovyScript(String script, Map<String, Object> params) {
        if (script != null && !script.trim().isEmpty()) {
            checkScriptSafety(script);
            Class<?> clazz = getCodeSourceClass(script);
            if (clazz != null) {
                return InvokerHelper.createScript(clazz, new Binding(params)).run();
            }
        }
        throw new IllegalArgumentException("脚本不能为空");
    }

    private Class<?> getCodeSourceClass(String codeSource) {
        try {
            String hashKey = sha256Hex(codeSource);
            Class<?> clazz = CLASS_CACHE.get(hashKey);
            if (clazz == null) {
                clazz = groovyClassLoader.parseClass(codeSource);
                CLASS_CACHE.putIfAbsent(hashKey, clazz);
            }
            return clazz;
        } catch (Exception e) {
            // 编译失败直接抛异常，禁止静默回退
            throw BizException.wrap("脚本编译失败: {}", e.getMessage());
        }
    }

    /**
     * 脚本安全校验（多层防御）：
     * <ol>
     *   <li>剥离字符串字面量和空白 → Token 黑名单扫描</li>
     *   <li>危险调用模式正则匹配（覆盖字符串插值等组合写法）</li>
     * </ol>
     *
     * @param script 脚本源码
     */
    private static void checkScriptSafety(String script) {
        // -------- 第 1 层：Token 黑名单扫描（先剥离字符串字面量，防止误报） --------
        String cleaned = stripStringLiterals(script);
        for (String token : DANGEROUS_TOKENS) {
            if (cleaned.contains(token)) {
                log.warn("Groovy 脚本安全校验未通过, 检测到危险 Token: {}", token);
                throw new SecurityException("脚本包含不允许的危险操作: " + token);
            }
        }
        // -------- 第 2 层：危险调用模式正则匹配（在原始脚本上扫描） --------
        for (Pattern pattern : DANGEROUS_CALL_PATTERNS) {
            if (pattern.matcher(script).find()) {
                log.warn("Groovy 脚本安全校验未通过, 匹配到危险模式: {}", pattern.pattern());
                throw new SecurityException("脚本包含不允许的危险操作: " + pattern.pattern());
            }
        }
    }

    /**
     * 剥离 Groovy 脚本中的字符串字面量（单引号、双引号、三引号）和注释，
     * 仅保留代码结构用于 Token 扫描，避免字符串内容引起误报。
     */
    private static String stripStringLiterals(String script) {
        // 去除三引号字符串（"""...""" 和 '''...'''）
        String result = script.replaceAll("\"\"\"[\\s\\S]*?\"\"\"", " ");
        result = result.replaceAll("'''[\\s\\S]*?'''", " ");
        // 去除双引号字符串（支持转义）
        result = result.replaceAll("\"(?:[^\"\\\\]|\\\\.)*\"", " ");
        // 去除单引号字符串（支持转义）
        result = result.replaceAll("'(?:[^'\\\\]|\\\\.)*'", " ");
        // 去除行注释和块注释
        result = result.replaceAll("//[^\\n]*", " ");
        result = result.replaceAll("/\\*[\\s\\S]*?\\*/", " ");
        // 压缩空白
        return result.replaceAll("\\s+", " ");
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
