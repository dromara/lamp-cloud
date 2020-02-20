package com.github.zuihou.database.mybatis.typehandler;

import cn.hutool.core.util.StrUtil;
import com.github.zuihou.model.RemoteData;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 仅仅用于like查询
 *
 * @author zuihou
 */
public class BaseLikeTypeHandler extends BaseTypeHandler<CharSequence> {

    private static final String LIKE = "%";
    private final boolean leftLike;
    private final boolean rightLike;

    public BaseLikeTypeHandler(boolean leftLike, boolean rightLike) {
        this.leftLike = leftLike;
        this.rightLike = rightLike;
    }

    /**
     * mybatis plus like查询转换
     */
    public static String likeConvert(String value) {
        if (StrUtil.isNotBlank(value)) {
            value = value.replaceAll("%", "\\\\%");
            value = value.replaceAll("_", "\\\\_");
            return value;
        } else {
            return "";
        }
    }

    public static String likeConvertProcess(String value) {
        if (StrUtil.isNotBlank(value)) {
            value = value.replaceAll("%", "\\\\%");
            value = value.replaceAll("_", "\\\\_");
            return "%" + value + "%";
        } else {
            return "";
        }
    }

    public static Object likeConvert(Object value) {
        if (value instanceof String) {
            return likeConvert(String.valueOf(value));
        }
        if (value instanceof RemoteData) {
            if (((RemoteData) value).getKey() != null) {
                ((RemoteData) value).setKey(likeConvert(String.valueOf(((RemoteData) value).getKey())));
            }
            return value;
        }
        return value;
    }

    private String convert(String value) {
        value = value.replaceAll("\\%", "\\\\%");
        value = value.replaceAll("\\_", "\\\\_");
        return value;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CharSequence parameter, JdbcType jdbcType)
            throws SQLException {
        if (parameter == null) {
            ps.setString(i, null);
        } else {
            ps.setString(i, like(parameter.toString()));
        }
    }

    private String like(String parameter) {
        String result = convert(parameter);
        if (this.leftLike) {
            result = LIKE + result;
        }
        if (this.rightLike) {
            result += LIKE;
        }
        return result;
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }

}
