package com.gmail.aray.chou.mybatis.enumeration.type.bug.demo;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhouhongyang@zbj.com on 12/19/2017.
 */
public class CodedEnumHandler<E> extends BaseTypeHandler<E> {

    private final Class<E> type;
    private final Method methodGetCode;
    private Method methodValueOf;

    public CodedEnumHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        if (!type.isEnum()) {
            throw new IllegalArgumentException("Type argument should be an enum");
        }
        this.type = type;
        try {
            methodValueOf = type.getMethod("valueOf", Integer.class);
            methodGetCode = type.getMethod("getCode");
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Type argument should have static method: valueOf(Integer)");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {

        try {
            Integer value = (Integer) methodGetCode.invoke(parameter);
            ps.setInt(i, value);
        } catch (Exception e) {
            throw new RuntimeException("Wrong Enum Handler used.", e);
        }

    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return convertIntegerToEnum(i);
        }
    }

    private E convertIntegerToEnum(int i) {
        try {
            return (E) this.methodValueOf.invoke(null, i);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + ".", ex);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return convertIntegerToEnum(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return convertIntegerToEnum(i);
        }
    }

}
