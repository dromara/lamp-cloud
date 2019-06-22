package com.github.zuihou.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 仅仅用于like查询
 *
 * @author Administrator
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
        if (StringUtils.isNotBlank(value)) {
            value = value.replaceAll("%", "\\\\%");
            value = value.replaceAll("_", "\\\\_");
            return value;
        } else {
            return "";
        }
    }

    public static String likeConvertProcess(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replaceAll("%", "\\\\%");
            value = value.replaceAll("_", "\\\\_");
            return "%" + value + "%";
        } else {
            return "";
        }
    }

    public static String likeConvert(Object value) {
        if (value instanceof String) {
            return likeConvert(String.valueOf(value));
        }
        return "";
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
