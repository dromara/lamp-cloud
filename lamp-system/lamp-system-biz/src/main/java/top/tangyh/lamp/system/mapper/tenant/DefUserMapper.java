package top.tangyh.lamp.system.mapper.tenant;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.vo.query.tenant.DefUserPageQuery;
import top.tangyh.lamp.system.vo.result.tenant.DefUserResultVO;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * 用户
 * </p>
 *
 * @author zuihou
 * @date 2021-10-09
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefUserMapper extends SuperMapper<DefUser> {

    /**
     * 查找同一企业下的用户
     *
     * @param pageQuery 参数
     * @param page      分页参数
     * @return
     */
    IPage<DefUserResultVO> pageUser(@Param("param") DefUserPageQuery pageQuery, IPage<DefUser> page);

    /**
     * 递增 密码错误次数
     *
     * @param id  用户id
     * @param now 当前时间
     * @return 被修改了几行数据
     * @author tangyh
     * @date 2022/10/28 4:57 PM
     * @create [2022/10/28 4:57 PM ] [tangyh] [初始创建]
     */
    int incrPasswordErrorNumById(@Param("id") Long id, @Param("now") LocalDateTime now);

    /**
     * 重置 密码错误次数
     *
     * @param id  用户id
     * @param now 当前时间
     * @return 被修改了几行数据
     */
    int resetPassErrorNum(@Param("id") Long id, @Param("now") LocalDateTime now);

}
