package top.tangyh.lamp.tenant.strategy.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.utils.DbPlusUtil;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.tenant.dao.InitDbMapper;
import top.tangyh.lamp.tenant.dto.TenantConnectDTO;
import top.tangyh.lamp.tenant.strategy.InitSystemStrategy;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;

/**
 * 初始化系统
 * <p>
 * 初始化规则：
 * 表结构： lamp-tenant-server/src/main/resources/schema/{数据库类型}/{数据库前缀}.sql
 * 租户初始数据： lamp-tenant-server/src/main/resources/data/{数据库类型}/{数据库前缀}.sql
 * <p>
 * 不支持ORACLE！
 *
 * @author zuihou
 * @date 2019/10/25
 */
@Service("SCHEMA")
@Slf4j
@RequiredArgsConstructor
public class SchemaInitSystemStrategy implements InitSystemStrategy {
    /**
     * 需要初始化的sql文件在classpath中的路径
     */
    private static final String SCHEMA_PATH = "schema/{}/{}.sql";
    private static final String DATA_PATH = "data/{}/{}.sql";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final DataSource dataSource;
    private final InitDbMapper initDbMapper;
    private final DatabaseProperties databaseProperties;


    private String getDefDatabase() {
        return DbPlusUtil.getDatabase(dataSource);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initConnect(TenantConnectDTO tenantConnect) {
        String tenant = tenantConnect.getTenant();
        this.initDatabase(tenant);
        ScriptRunner runner = this.getScriptRunner();
        this.initSchema(runner, tenant);
        this.initData(runner, tenant);
        // 切换为默认数据源
        this.resetDatabase(runner);
        return true;
    }

    @Override
    public boolean reset(String tenant) {
        ScriptRunner runner = this.getScriptRunner();
        this.initSchema(runner, tenant);
        this.initData(runner, tenant);
        // 切换为默认数据源
        this.resetDatabase(runner);
        return true;
    }


    public void initDatabase(String tenant) {
        databaseProperties.getInitDatabasePrefix().forEach(database -> this.initDbMapper.createDatabase(StrUtil.join(StrUtil.UNDERLINE, database, tenant)));
    }

    public void initSchema(ScriptRunner runner, String tenant) {
        runScript(runner, tenant, SCHEMA_PATH);
    }

    /**
     * 角色表
     * 菜单表
     * 资源表
     *
     * @param tenant 租户编码
     */
    public void initData(ScriptRunner runner, String tenant) {
        runScript(runner, tenant, DATA_PATH);
    }

    private void runScript(ScriptRunner runner, String tenant, String path) {
        try {
            for (String database : databaseProperties.getInitDatabasePrefix()) {
                String useDb = StrUtil.format("use {};", StrUtil.join(StrUtil.UNDERLINE, database, tenant));

                StringBuilder script = new StringBuilder();
                script.append(useDb);
                script.append(LINE_SEPARATOR);

                Reader reader = Resources.getResourceAsReader(StrUtil.format(path, getDbType().getDb(), database));
                BufferedReader lineReader = new BufferedReader(reader);
                String line;
                while ((line = lineReader.readLine()) != null) {
                    script.append(line);
                    script.append(LINE_SEPARATOR);
                }

                runner.runScript(new StringReader(script.toString()));
            }
        } catch (Exception e) {
            log.error("初始化数据失败", e);
            throw new BizException("初始化数据失败", e);
        }
    }

    private DbType getDbType() {
        return DbPlusUtil.getDbType(dataSource);
    }


    public void resetDatabase(ScriptRunner runner) {
        try {
            runner.runScript(new StringReader(StrUtil.format("use {};", getDefDatabase())));
        } catch (Exception e) {
            log.error("切换为默认数据源失败", e);
            throw new BizException("切换为默认数据源失败", e);
        }
    }

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public ScriptRunner getScriptRunner() {
        try {
            Connection connection = this.dataSource.getConnection();
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setAutoCommit(false);
            //遇见错误是否停止
            runner.setStopOnError(true);
            /*
             * 按照那种方式执行 方式一：true则获取整个脚本并执行； 方式二：false则按照自定义的分隔符每行执行；
             */
            runner.setSendFullScript(true);
            // 设置是否输出日志，null不输出日志，不设置自动将日志输出到控制台
            // runner.setLogWriter(null);

            Resources.setCharset(StandardCharsets.UTF_8);
//            设置分隔符
//            runner.setDelimiter(databaseProperties.getDelimiter());
            runner.setFullLineDelimiter(false);
            return runner;
        } catch (Exception ex) {
            throw new BizException("获取连接失败", ex);
        }
    }


    @Override
    public boolean delete(List<Long> ids, List<String> tenantCodeList) {
        if (tenantCodeList.isEmpty()) {
            return true;
        }

        databaseProperties.getInitDatabasePrefix().forEach(prefix -> tenantCodeList.forEach(tenant -> {
            String database = prefix + StrPool.UNDERSCORE + tenant;
            initDbMapper.dropDatabase(database);
        }));

        return true;
    }
}
