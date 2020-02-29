package com.github.zuihou;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.TableNameParser;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.Collection;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/08/12
 */
public class NoBootTest {
    @Test
    public void testCopy() throws Exception {
        File src = new File("/projects/uploadfile/file/2019/08/4b4c5f2f3d945db915d4645e26189d6e.mp4");
        File target = new File("/projects/uploadfile/file/2019/08/appache/1.mp4");
        File target2 = new File("/projects/uploadfile/file/2019/08/hutool/1.mp4");

        FileUtils.copyFile(src, target);

        FileUtil.copy(src, target2, true);

    }

    @Test
    public void testSqlTablesParse() {
        String sql = "select * from aa a left join bb b on aa.id = bb.id";

        sql = "select zuihou.c from c,zuihou,d where zuihou.c=s";

        //c_auth_account_role c_auth_role_resource  c_auth_role c_auth_resource c_auth_application
        sql = " select\n" +
                "        *\n" +
                "        from c_auth_application\n" +
                "        where code_ in (\n" +
                "        SELECT reso.app_code\n" +
                "        FROM\n" +
                "        c_auth_resource reso\n" +
                "        JOIN c_auth_role_resource power ON reso.id = power.resource_id\n" +
                "        JOIN c_auth_role ON power.role_id = c_auth_role.id\n" +
                "        JOIN c_auth_account_role ac_role ON c_auth_role.id = ac_role.role_id\n" +
                "        WHERE\n" +
                "        reso.enable_is = 1\n" +
                "        AND c_auth_role.enable_is = 1\n" +
                "        AND ac_role.account_id = 1\n" +
                "\n" +
                "        UNION ALL\n" +
                "\n" +
                "        SELECT\n" +
                "        reso.app_code\n" +
                "        FROM\n" +
                "        c_auth_resource reso\n" +
                "        where reso.enable_is=1 and public_is = 1\n" +
                "        ) and enable_is = 1";

        //dd_user_info dd_dept_info dd_attendance_record
        sql = "select a.user_id userId,\n" +
                "a.user_name userName,\n" +
                "d.dept_id deptId,\n" +
                "d.dept_name deptName,\n" +
                "a.work_date workDate,\n" +
                "GROUP_CONCAT(onDutyTime) onDutyTime,\n" +
                "GROUP_CONCAT(offDutyTime) offDutyTime\n" +
                "from (select DISTINCT t1.user_id,\n" +
                "t1.user_name,\n" +
                "t.work_date,\n" +
                "t.check_type,\n" +
                "(select r.user_check_time from dd_attendance_record r where r.id = t.id and r.check_type = 'onDuty') 'onDutyTime',\n" +
                "(select r.user_check_time from dd_attendance_record r where r.id = t.id and r.check_type = 'offDuty') 'offDutyTime'\n" +
                "from dd_attendance_record t\n" +
                "inner join dd_user_info t1 on t1.user_id = t.user_id) a\n" +
                "inner join dd_user_info u on u.user_id = a.user_id\n" +
                "inner join dd_dept_info d on u.dept_id = d.dept_id\n" +
                "where 1=1\n" +
                "and d.dept_id = #{dto.deptId}\n" +
                "and a.user_name like #{dto.userName,typeHandler=fullLike}\n" +
                "and a.work_date = #{dto.workDate}\n" +
                "group by a.user_name,a.work_date,d.dept_name\n" +
                "order by a.work_date desc,d.order_";

        // b_sdzzw_mh_day_publish_column
        // b_sdzzw_tz_day_user
        // b_sdzzw_mh_day_push_cb
        // b_sdzzw_kc_day_user_sponsoring
        sql = "SELECT\n" +
                "        case  when kcNum is null then 0 else kcNum end AS kcNum,\n" +
                "        case  when tsNum is null then 0 else tsNum end AS tsNum,\n" +
                "        case  when fbNum is null then 0 else fbNum end AS fbNum,\n" +
                "        case  when cbNum is null then 0 else cbNum end AS cbNum,\n" +
                "        case  when tzNum is null then 0 else tzNum end AS tzNum,\n" +
                "        case  when tzMore5DayNum is null then 0 else tzMore5DayNum end AS tzMore5DayNum\n" +
                "        FROM  (\n" +
                "        SELECT SUM(theNum) AS kcNum  FROM b_sdzzw_kc_day_user_sponsoring WHERE orgId=#{orgId} and theMonth=#{theMonth}\n" +
                "        ) a,\n" +
                "        (\n" +
                "        SELECT SUM(theNum) AS fbNum FROM b_sdzzw_mh_day_publish_column  WHERE orgId=#{orgId} and theMonth=#{theMonth}\n" +
                "        ) b,\n" +
                "        (\n" +
                "        SELECT SUM(theNum) AS tsNum FROM b_sdzzw_mh_day_push_cb  WHERE theNumType='1' and orgId=#{orgId} and theMonth=#{theMonth}\n" +
                "        ) c,\n" +
                "        (\n" +
                "        SELECT SUM(theNum) AS cbNum FROM  b_sdzzw_mh_day_push_cb WHERE theNumType='2' AND orgId=#{orgId} and theMonth=#{theMonth}\n" +
                "        ) d,\n" +
                "        (\n" +
                "        SELECT SUM(theNum) AS tzNum FROM b_sdzzw_tz_day_user WHERE orgId=#{orgId}  and theMonth=#{theMonth}\n" +
                "        ) e,\n" +
                "        (\n" +
                "        SELECT count(*) AS tzMore5DayNum FROM (\n" +
                "            SELECT userId FROM b_sdzzw_tz_day_user\n" +
                "            WHERE orgId=#{orgId}  and theMonth=#{theMonth}\n" +
                "          GROUP BY userId HAVING COUNT(*) >= 5\n" +
                "          ) ss\n" +
                "        ) f";

        //govdoc_exchange_detail
        //org_unit
        //ctp_affair
        //org_member
        //col_summary
        //edoc_summary
        sql = " SELECT  *  from ((SELECT\n" +
                "        COUNT(*) AS fawenshu\n" +
                "        FROM\n" +
                "        edoc_summary s,\n" +
                "        ctp_affair cf\n" +
                "        WHERE\n" +
                "        s.edoc_type = 0\n" +
                "        and cf.node_policy ='niwen'\n" +
                "        and cf.state=2\n" +
                "        and s.id = cf.object_id\n" +
                "        and date_format(s.create_time,'%Y-%m')=date_format(now(),'%Y-%m')\n" +
                "        and s.org_account_id ='-3631140605324605617') a,\n" +
                "        (SELECT count(*) shiwufaqi FROM col_summary\n" +
                "        WHERE govdoc_type IS NULL\n" +
                "        and CASE_ID is not null\n" +
                "        and org_account_id ='-3631140605324605617'\n" +
                "        and date_format(create_date, '%Y-%m') = date_format(now(), '%Y-%m') ) b,\n" +
                "        (SELECT\n" +
                "            count(*) laiwenshu\n" +
                "        FROM\n" +
                "            govdoc_exchange_detail ged\n" +
                "        WHERE\n" +
                "            ged.REC_ORG_ID IN (\n" +
                "                SELECT\n" +
                "                    u.id\n" +
                "                FROM\n" +
                "                    org_unit u\n" +
                "                WHERE\n" +
                "                    u.org_account_id = '-3631140605324605617'\n" +
                "                AND u.IS_ENABLE = 1\n" +
                "                AND u.is_deleted = 0\n" +
                "            )\n" +
                "        and ged. STATUS IN (1, 2, 3, 4, 5, 13)\n" +
                "        AND ged.REC_ORG_ID != ged.SEND_ACCOUNT_ID\n" +
                "        and date_format(ged.create_time, '%Y-%m') = date_format(now(), '%Y-%m') )c,\n" +
                "         (SELECT COUNT(1) zaixianbanwen FROM govdoc_exchange_detail\n" +
                "        WHERE rec_summary_id in(\n" +
                "        SELECT DISTINCT object_id FROM(\n" +
                "        SELECT COUNT(1) num,object_id FROM ctp_affair\n" +
                "        WHERE  member_id in(SELECT id FROM org_member WHERE ORG_ACCOUNT_ID ='-3631140605324605617')\n" +
                "        GROUP BY object_id,node_policy\n" +
                "        HAVING num>=3) a)\n" +
                "        and STATUS IN (1, 2, 3, 4, 5, 13)\n" +
                "        and date_format(REC_TIME,'%Y-%m')=date_format(now(), '%Y-%m'))d,\n" +
                "       (SELECT  count(1) as quanliuchengfawen\n" +
                "        FROM edoc_summary e,\n" +
                "          (SELECT A.object_id, count(1) AS num FROM\n" +
                "              ( SELECT a.object_id,a.activity_id FROM ctp_affair a WHERE a.object_Id IN\n" +
                "                  ( SELECT s.id FROM edoc_summary s, ctp_affair c\n" +
                "                    WHERE s.govdoc_type = 1\n" +
                "                    AND s.complete_time IS NOT NULL\n" +
                "                    AND c.node_policy = 'niwen'\n" +
                "                    AND c.state NOT IN (1, 5)\n" +
                "                    AND s.id = c.object_id\n" +
                "                    and date_format(create_time,'%Y-%m')=date_format(now(), '%Y-%m')\n" +
                "                    and s.org_account_id = '-3631140605324605617'\n" +
                "                  )\n" +
                "                AND a.STATE NOT IN (5, 6, 7, 8)\n" +
                "                AND a.activity_id IS NOT NULL\n" +
                "                GROUP BY\n" +
                "                  a.object_id,\n" +
                "                  a.activity_id\n" +
                "              ) A\n" +
                "         group by A.object_id ) B\n" +
                "        where e.id = B.object_id and B.num>1\n" +
                "        ) e)";


        Collection<String> tables = new TableNameParser(sql).tables();

        System.out.println(tables.size());
        tables.stream().forEach(System.out::println);
    }

}
