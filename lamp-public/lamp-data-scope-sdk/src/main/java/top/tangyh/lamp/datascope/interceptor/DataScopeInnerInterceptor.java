package top.tangyh.lamp.datascope.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import top.tangyh.lamp.datascope.provider.DataScopeContext;
import top.tangyh.lamp.datascope.service.DataScopeService;

/**
 * 企业版 拥有此插件的全部源码
 * <p>
 *
 * @author zuihou
 * @date 2020/9/27 10:00 上午
 */
@Slf4j
public class DataScopeInnerInterceptor implements InnerInterceptor {

    @Autowired
    @Lazy
    private DataScopeService dataScopeService;
    @Autowired
    @Lazy
    private DataScopeContext dataScopeContext;

    /**
     * 企业版 拥有此插件的全部源码
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        // 企业版 拥有数据权限插件的全部源码
    }


}
