package com.github.zuihou.executor.jobhandler;

import com.github.zuihou.authority.service.defaults.InitSystemService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 重置 演示环境的 默认库数据
 *
 * @author zuihou
 * @date 2020年01月16日14:45:58
 */
@JobHandler(value = "restBase0000JobHandler")
@Component
@Slf4j
public class RestBase0000JobHandler extends IJobHandler {
    /**
     * 内置租户
     */
    private final static String DEF_TENANT = "0000";
    /**
     * 需要初始化的sql文件在classpath中的路径
     */
    private final static String SQL_RESOURCE_PATH = "sqls/%s.sql";
    @Autowired
    private InitSystemService initSystemService;
    @Value("${zuihou.mysql.biz-database:zuihou_base}")
    private String database;

    @Override
    public ReturnT<String> execute2(String param) throws Exception {
        XxlJobLogger.log("执行参数--->param={} ", param);

        ScriptRunner runner = null;
        try {
            runner = initSystemService.getScriptRunner();

            initSystemService.useDb(DEF_TENANT, runner, database);
            String dataScript = database + "_" + DEF_TENANT;
            runner.runScript(Resources.getResourceAsReader(String.format(SQL_RESOURCE_PATH, dataScript)));


        } catch (Exception e) {
            log.error("重置数据失败", e);
            return new ReturnT<>(500, e.getMessage());
        } finally {
            try {
                if (runner != null) {
                    runner.closeConnection();
                }
            } catch (Exception e) {
                log.error("关闭失败", e);
                return new ReturnT<>(500, "提交失败");
            }
            initSystemService.resetDatabase();
        }

        return SUCCESS;
    }
}
