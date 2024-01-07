package top.tangyh.lamp.datascope.interceptor;

import lombok.SneakyThrows;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import org.junit.jupiter.api.Test;
import top.tangyh.lamp.datascope.model.DataFieldProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author zuihou
 * @date 2022/1/9 11:30
 */
public class DataScopeInnerInterceptorTest {

    final DataScopeInnerInterceptor interceptor = new DataScopeInnerInterceptor();

    public static void main(String[] args) throws Exception {
//        String originalSql = "with t as ( select * from consumer ) select * from t ";  // 支持
//        String originalSql = " select id, name , ddd from user where id = 1 and ddd in ( select dd from sss ) "; // 不支持
//        String originalSql = " select id, name , (select name from xxx where xxx.id = user.user_id ) as ddd from user where id = 1 "; // 不支持
        String originalSql = " select * from user UNION select * from employee ";  // 支持
        Statement statement = CCJSqlParserUtil.parse(originalSql);
        DataScopeInnerInterceptor dataScopeInnerInterceptor = new DataScopeInnerInterceptor();

        List<DataFieldProperty> dataFieldProperties = new ArrayList<>();
        dataFieldProperties.add(DataFieldProperty.builder().alias("consumer").field("org_id").values(Arrays.asList(1L, 2L)).build());

        dataScopeInnerInterceptor.processSelect((Select) statement, dataFieldProperties);

        System.out.println(statement.toString());

    }

    @Test
    void selectSingle() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("entity").field("tenant_id").values(Collections.singletonList(1L)).build());
        // 单表
        assertSql("select * from entity where id = ?",
                "SELECT * FROM entity WHERE id = ? AND entity.tenant_id = 1", list);

        assertSql("select * from entity where id = ? or name = ?",
                "SELECT * FROM entity WHERE (id = ? OR name = ?) AND entity.tenant_id = 1", list);

        assertSql("SELECT * FROM entity WHERE (id = ? OR name = ?)",
                "SELECT * FROM entity WHERE (id = ? OR name = ?) AND entity.tenant_id = 1", list);

        /* not */
        assertSql("SELECT * FROM entity WHERE not (id = ? OR name = ?)",
                "SELECT * FROM entity WHERE NOT (id = ? OR name = ?) AND entity.tenant_id = 1", list);
    }


    @Test
    void selectSubSelectIn() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        /* in */
        assertSql("SELECT * FROM entity e WHERE e.id IN (select e1.id from entity1 e1 where e1.id = ?)",
                "SELECT * FROM entity e WHERE e.id IN (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.tenant_id = 1", list);
        // 在最前
        assertSql("SELECT * FROM entity e WHERE e.id IN " +
                        "(select e1.id from entity1 e1 where e1.id = ?) and e.id = ?",
                "SELECT * FROM entity e WHERE e.id IN " +
                        "(SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.id = ? AND e.tenant_id = 1", list);
        // 在最后
        assertSql("SELECT * FROM entity e WHERE e.id = ? and e.id IN " +
                        "(select e1.id from entity1 e1 where e1.id = ?)",
                "SELECT * FROM entity e WHERE e.id = ? AND e.id IN " +
                        "(SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.tenant_id = 1", list);
        // 在中间
        assertSql("SELECT * FROM entity e WHERE e.id = ? and e.id IN " +
                        "(select e1.id from entity1 e1 where e1.id = ?) and e.id = ?",
                "SELECT * FROM entity e WHERE e.id = ? AND e.id IN " +
                        "(SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.id = ? AND e.tenant_id = 1", list);
    }

    @Test
    void selectSubSelectEq() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        /* = */
        assertSql("SELECT * FROM entity e WHERE e.id = (select e1.id from entity1 e1 where e1.id = ?)",
                "SELECT * FROM entity e WHERE e.id = (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.tenant_id = 1", list);
    }

    @Test
    void selectSubSelectInnerNotEq() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        /* inner not = */
        assertSql("SELECT * FROM entity e WHERE not (e.id = (select e1.id from entity1 e1 where e1.id = ?))",
                "SELECT * FROM entity e WHERE NOT (e.id = (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?)) AND e.tenant_id = 1", list);

        assertSql("SELECT * FROM entity e WHERE not (e.id = (select e1.id from entity1 e1 where e1.id = ?) and e.id = ?)",
                "SELECT * FROM entity e WHERE NOT (e.id = (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.id = ?) AND e.tenant_id = 1", list);
    }

    @Test
    void selectSubSelectExists() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        /* EXISTS */
        assertSql("SELECT * FROM entity e WHERE EXISTS (select e1.id from entity1 e1 where e1.id = ?)",
                "SELECT * FROM entity e WHERE EXISTS (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.tenant_id = 1", list);


        /* NOT EXISTS */
        assertSql("SELECT * FROM entity e WHERE NOT EXISTS (select e1.id from entity1 e1 where e1.id = ?)",
                "SELECT * FROM entity e WHERE NOT EXISTS (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.tenant_id = 1", list);
    }

    @Test
    void selectSubSelect() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        /* >= */
        assertSql("SELECT * FROM entity e WHERE e.id >= (select e1.id from entity1 e1 where e1.id = ?)",
                "SELECT * FROM entity e WHERE e.id >= (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.tenant_id = 1", list);

        /* <= */
        assertSql("SELECT * FROM entity e WHERE e.id <= (select e1.id from entity1 e1 where e1.id = ?)",
                "SELECT * FROM entity e WHERE e.id <= (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.tenant_id = 1", list);

        /* <> */
        assertSql("SELECT * FROM entity e WHERE e.id <> (select e1.id from entity1 e1 where e1.id = ?)",
                "SELECT * FROM entity e WHERE e.id <> (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?) AND e.tenant_id = 1", list);
    }

    @Test
    void selectFromSelect() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql("SELECT * FROM (select e.id from entity e WHERE e.id = (select e1.id from entity1 e1 where e1.id = ?)) e",
                "SELECT * FROM (SELECT e.id FROM entity e WHERE e.id = (SELECT e1.id FROM entity1 e1 WHERE e1.id = ?)) e WHERE e.tenant_id = 1", list);
    }

    @Test
    void selectBodySubSelect() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("t1").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql("select t1.col1,(select t2.col2 from t2 t2 where t1.col1=t2.col1) from t1 t1",
                "SELECT t1.col1, (SELECT t2.col2 FROM t2 t2 WHERE t1.col1 = t2.col1) FROM t1 t1 WHERE t1.tenant_id = 1", list);
    }

    @Test
    void selectLeftJoin() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        // left join
        assertSql("SELECT * FROM entity e " +
                        "left join entity1 e1 on e1.id = e.id " +
                        "WHERE e.id = ? OR e.name = ?",
                "SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 ON e1.id = e.id " +
                        "WHERE (e.id = ? OR e.name = ?) AND e.tenant_id = 1", list);

        assertSql("SELECT * FROM entity e " +
                        "left join entity1 e1 on e1.id = e.id " +
                        "WHERE (e.id = ? OR e.name = ?)",
                "SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 ON e1.id = e.id " +
                        "WHERE (e.id = ? OR e.name = ?) AND e.tenant_id = 1", list);

        assertSql("SELECT * FROM entity e " +
                        "left join entity1 e1 on e1.id = e.id " +
                        "left join entity2 e2 on e1.id = e2.id",
                "SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 ON e1.id = e.id " +
                        "LEFT JOIN entity2 e2 ON e1.id = e2.id " +
                        "WHERE e.tenant_id = 1", list);
    }

    @Test
    void selectRightJoin() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e1").field("tenant_id").values(Collections.singletonList(1L)).build());
        // right join
        assertSql("SELECT * FROM entity e " +
                        "right join entity1 e1 on e1.id = e.id",
                "SELECT * FROM entity e " +
                        "RIGHT JOIN entity1 e1 ON e1.id = e.id " +
                        "WHERE e1.tenant_id = 1", list);

        assertSql("SELECT * FROM with_as_1 e " +
                        "right join entity1 e1 on e1.id = e.id",
                "SELECT * FROM with_as_1 e " +
                        "RIGHT JOIN entity1 e1 ON e1.id = e.id " +
                        "WHERE e1.tenant_id = 1", list);

        assertSql("SELECT * FROM entity e " +
                        "right join entity1 e1 on e1.id = e.id " +
                        "WHERE e.id = ? OR e.name = ?",
                "SELECT * FROM entity e " +
                        "RIGHT JOIN entity1 e1 ON e1.id = e.id " +
                        "WHERE (e.id = ? OR e.name = ?) AND e1.tenant_id = 1", list);

        List<DataFieldProperty> list2 = Collections.singletonList(DataFieldProperty.builder().alias("e2").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql("SELECT * FROM entity e " +
                        "right join entity1 e1 on e1.id = e.id " +
                        "right join entity2 e2 on e1.id = e2.id ",
                "SELECT * FROM entity e " +
                        "RIGHT JOIN entity1 e1 ON e1.id = e.id " +
                        "RIGHT JOIN entity2 e2 ON e1.id = e2.id " +
                        "WHERE e2.tenant_id = 1", list2);
    }

    @Test
    void selectMixJoin() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e1").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql("SELECT * FROM entity e " +
                        "right join entity1 e1 on e1.id = e.id " +
                        "left join entity2 e2 on e1.id = e2.id",
                "SELECT * FROM entity e " +
                        "RIGHT JOIN entity1 e1 ON e1.id = e.id " +
                        "LEFT JOIN entity2 e2 ON e1.id = e2.id " +
                        "WHERE e1.tenant_id = 1", list);

        List<DataFieldProperty> list2 = Collections.singletonList(DataFieldProperty.builder().alias("e1").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql("SELECT * FROM entity e " +
                        "left join entity1 e1 on e1.id = e.id " +
                        "right join entity2 e2 on e1.id = e2.id",
                "SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 ON e1.id = e.id " +
                        "RIGHT JOIN entity2 e2 ON e1.id = e2.id " +
                        "WHERE e2.tenant_id = 1", list2);

        assertSql("SELECT * FROM entity e " +
                        "left join entity1 e1 on e1.id = e.id " +
                        "inner join entity2 e2 on e1.id = e2.id",
                "SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 ON e1.id = e.id " +
                        "INNER JOIN entity2 e2 ON e1.id = e2.id WHERE e2.tenant_id = 1", list2);
    }


    @Test
    void selectJoinSubSelect() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e2").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql("select * from (select * from entity) e1 " +
                        "left join entity2 e2 on e1.id = e2.id",
                "SELECT * FROM (SELECT * FROM entity) e1 " +
                        "LEFT JOIN entity2 e2 ON e1.id = e2.id WHERE e2.tenant_id = 1", list);

        List<DataFieldProperty> list2 = Collections.singletonList(DataFieldProperty.builder().alias("e1").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql("select * from entity1 e1 " +
                        "left join (select * from entity2) e2 " +
                        "on e1.id = e2.id",
                "SELECT * FROM entity1 e1 " +
                        "LEFT JOIN (SELECT * FROM entity2) e2 " +
                        "ON e1.id = e2.id " +
                        "WHERE e1.tenant_id = 1", list2);
    }

    @Test
    void selectSubJoin() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        List<DataFieldProperty> list2 = Collections.singletonList(DataFieldProperty.builder().alias("e2").field("tenant_id").values(Collections.singletonList(1L)).build());
        List<DataFieldProperty> list1 = Collections.singletonList(DataFieldProperty.builder().alias("e1").field("tenant_id").values(Collections.singletonList(1L)).build());
        List<DataFieldProperty> list3 = Collections.singletonList(DataFieldProperty.builder().alias("e3").field("tenant_id").values(Collections.singletonList(1L)).build());

        assertSql("select * FROM " +
                        "(entity1 e1 right JOIN entity2 e2 ON e1.id = e2.id)",
                "SELECT * FROM " +
                        "(entity1 e1 RIGHT JOIN entity2 e2 ON e1.id = e2.id) " +
                        "WHERE e2.tenant_id = 1", list2);

        assertSql("select * FROM " +
                        "(entity1 e1 LEFT JOIN entity2 e2 ON e1.id = e2.id)",
                "SELECT * FROM " +
                        "(entity1 e1 LEFT JOIN entity2 e2 ON e1.id = e2.id) " +
                        "WHERE e1.tenant_id = 1", list1);


        assertSql("select * FROM " +
                        "(entity1 e1 LEFT JOIN entity2 e2 ON e1.id = e2.id) " +
                        "right join entity3 e3 on e1.id = e3.id",
                "SELECT * FROM " +
                        "(entity1 e1 LEFT JOIN entity2 e2 ON e1.id = e2.id) " +
                        "RIGHT JOIN entity3 e3 ON e1.id = e3.id " +
                        "WHERE e3.tenant_id = 1", list3);


        assertSql("select * FROM entity e " +
                        "LEFT JOIN (entity1 e1 right join entity2 e2 ON e1.id = e2.id) " +
                        "on e.id = e2.id",
                "SELECT * FROM entity e " +
                        "LEFT JOIN (entity1 e1 RIGHT JOIN entity2 e2 ON e1.id = e2.id) " +
                        "ON e.id = e2.id " +
                        "WHERE e.tenant_id = 1", list);

        assertSql("select * FROM entity e " +
                        "LEFT JOIN (entity1 e1 left join entity2 e2 ON e1.id = e2.id) " +
                        "on e.id = e2.id",
                "SELECT * FROM entity e " +
                        "LEFT JOIN (entity1 e1 LEFT JOIN entity2 e2 ON e1.id = e2.id) " +
                        "ON e.id = e2.id " +
                        "WHERE e.tenant_id = 1", list);

        assertSql("select * FROM entity e " +
                        "RIGHT JOIN (entity1 e1 left join entity2 e2 ON e1.id = e2.id) " +
                        "on e.id = e2.id",
                "SELECT * FROM entity e " +
                        "RIGHT JOIN (entity1 e1 LEFT JOIN entity2 e2 ON e1.id = e2.id) " +
                        "ON e.id = e2.id " +
                        "WHERE e1.tenant_id = 1", list1);
    }


    @Test
    void selectLeftJoinMultipleTrailingOn() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        // 多个 on 尾缀的
        assertSql("SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 " +
                        "LEFT JOIN entity2 e2 ON e2.id = e1.id " +
                        "ON e1.id = e.id " +
                        "WHERE (e.id = ? OR e.NAME = ?)",
                "SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 " +
                        "LEFT JOIN entity2 e2 ON e2.id = e1.id " +
                        "ON e1.id = e.id AND " +
                        "WHERE (e.id = ? OR e.NAME = ?) AND e.tenant_id = 1", list);

        assertSql("SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 " +
                        "LEFT JOIN with_as_A e2 ON e2.id = e1.id " +
                        "ON e1.id = e.id " +
                        "WHERE (e.id = ? OR e.NAME = ?)",
                "SELECT * FROM entity e " +
                        "LEFT JOIN entity1 e1 " +
                        "LEFT JOIN with_as_A e2 ON e2.id = e1.id " +
                        "ON e1.id = e.id " +
                        "WHERE (e.id = ? OR e.NAME = ?) AND e.tenant_id = 1", list);
    }

    @Test
    void selectInnerJoin() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("e").field("tenant_id").values(Collections.singletonList(1L)).build());
        List<DataFieldProperty> listEntity = Arrays.asList(
                DataFieldProperty.builder().alias("entity").field("tenant_id").values(Collections.singletonList(1L)).build(),
                DataFieldProperty.builder().alias("entity1").field("tenant_id").values(Collections.singletonList(1L)).build()
        );
        // inner join
        assertSql("SELECT * FROM entity e " +
                        "inner join entity1 e1 on e1.id = e.id " +
                        "WHERE e.id = ? OR e.name = ?",
                "SELECT * FROM entity e " +
                        "INNER JOIN entity1 e1 ON e1.id = e.id " +
                        "WHERE (e.id = ? OR e.name = ?) AND e.tenant_id = 1", list);

        assertSql("SELECT * FROM entity e " +
                        "inner join entity1 e1 on e1.id = e.id " +
                        "WHERE (e.id = ? OR e.name = ?)",
                "SELECT * FROM entity e " +
                        "INNER JOIN entity1 e1 ON e1.id = e.id " +
                        "WHERE (e.id = ? OR e.name = ?) AND e.tenant_id = 1", list);

        // 隐式内连接
        assertSql("SELECT * FROM entity,entity1 " +
                        "WHERE entity.id = entity1.id",
                "SELECT * FROM entity, entity1 " +
                        "WHERE entity.id = entity1.id AND entity.tenant_id = 1 AND entity1.tenant_id = 1", listEntity);

        List<DataFieldProperty> listA = Collections.singletonList(DataFieldProperty.builder().alias("a").field("tenant_id").values(Collections.singletonList(1L)).build());
        // 隐式内连接
        assertSql("SELECT * FROM entity a, with_as_entity1 b " +
                        "WHERE a.id = b.id",
                "SELECT * FROM entity a, with_as_entity1 b " +
                        "WHERE a.id = b.id AND a.tenant_id = 1", listA);

        assertSql("SELECT * FROM with_as_entity a, with_as_entity1 b " +
                        "WHERE a.id = b.id",
                "SELECT * FROM with_as_entity a, with_as_entity1 b " +
                        "WHERE a.id = b.id AND a.tenant_id = 1", listA);

        // SubJoin with 隐式内连接
        assertSql("SELECT * FROM (entity,entity1) " +
                        "WHERE entity.id = entity1.id",
                "SELECT * FROM (entity, entity1) " +
                        "WHERE entity.id = entity1.id " +
                        "AND entity.tenant_id = 1 AND entity1.tenant_id = 1", listEntity);

        assertSql("SELECT * FROM ((entity,entity1),entity2) " +
                        "WHERE entity.id = entity1.id and entity.id = entity2.id",
                "SELECT * FROM ((entity, entity1), entity2) " +
                        "WHERE entity.id = entity1.id AND entity.id = entity2.id " +
                        "AND entity.tenant_id = 1 AND entity1.tenant_id = 1", listEntity);

        assertSql("SELECT * FROM (entity,(entity1,entity2)) " +
                        "WHERE entity.id = entity1.id and entity.id = entity2.id",
                "SELECT * FROM (entity, (entity1, entity2)) " +
                        "WHERE entity.id = entity1.id AND entity.id = entity2.id " +
                        "AND entity.tenant_id = 1 AND entity1.tenant_id = 1", listEntity);

        // 沙雕的括号写法
        assertSql("SELECT * FROM (((entity,entity1))) " +
                        "WHERE entity.id = entity1.id",
                "SELECT * FROM (((entity, entity1))) " +
                        "WHERE entity.id = entity1.id " +
                        "AND entity.tenant_id = 1 AND entity1.tenant_id = 1", listEntity);

    }


    @Test
    void selectWithAs() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("entity").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql("with with_as_A as (select * from entity) select * from with_as_A",
                "WITH with_as_A AS (SELECT * FROM entity) SELECT * FROM with_as_A WHERE entity.tenant_id = 1", list);
    }


    @Test
    void selectIgnoreTable() {
        List<DataFieldProperty> list = Collections.singletonList(DataFieldProperty.builder().alias("item").field("tenant_id").values(Collections.singletonList(1L)).build());
        assertSql(" SELECT dict.dict_code, item.item_text AS \"text\", item.item_value AS \"value\" FROM sys_dict_item item INNER JOIN sys_dict dict ON dict.id = item.dict_id WHERE dict.dict_code IN (1, 2, 3) AND item.item_value IN (1, 2, 3)",
                "SELECT dict.dict_code, item.item_text AS \"text\", item.item_value AS \"value\" FROM sys_dict_item item INNER JOIN sys_dict dict ON dict.id = item.dict_id WHERE dict.dict_code IN (1, 2, 3) AND item.item_value IN (1, 2, 3) AND item.tenant_id = 1", list);
    }

    @SneakyThrows
    void assertSql(String sql, String targetSql, List<DataFieldProperty> dataFieldPropertyList) {
        Statement statement = CCJSqlParserUtil.parse(sql);
        interceptor.processSelect((Select) statement, dataFieldPropertyList);
        String newSql = statement.toString();
        assertThat(newSql).isEqualTo(targetSql);
    }
}
