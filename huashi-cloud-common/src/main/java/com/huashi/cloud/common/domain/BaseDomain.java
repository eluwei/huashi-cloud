package com.huashi.cloud.common.domain;

import com.huashi.cloud.common.exception.FieldAccessException;
import com.huashi.cloud.common.exception.result.FieldAccessExceptionResult;
import com.huashi.cloud.common.result.ResultState;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 2010-6-30
 * zhangglenn edit
 * 增加缓存机制
 * 取field 时能够取基类的
 */
public class BaseDomain {

    String id;
    String table;
    Class<?> idType;
    ColumnField[] columnFields;

    // 增加缓存机制
    static Map<Class<?>, BaseDomain> map;

    static {
        if (map == null)
            map = new HashMap();
    }

    public static BaseDomain getBaseDomain(Class<?> clasz) {
        BaseDomain baseDomain = map.get(clasz);
        if (baseDomain == null) {
            baseDomain = new BaseDomain();
            map.put(clasz, baseDomain);
        }

        Table t = clasz.getAnnotation(Table.class);
        if (t != null && baseDomain.table == null) {
            baseDomain.table = t.name();
        }

        List<ColumnField> columnFields = new ArrayList<ColumnField>();

        for (Field f : clasz.getDeclaredFields()) {
            Id id = f.getAnnotation(Id.class);
            if (id != null) {
                baseDomain.id = f.getName();
                baseDomain.idType = f.getType();
            }

            Column column = f.getAnnotation(Column.class);
            if (column != null) {
                ColumnField field = new ColumnField();
                field.setName(f.getName());
                field.setType(f.getType());
                columnFields.add(field);
            }
        }

        baseDomain.columnFields = columnFields.toArray(new ColumnField[columnFields.size()]);
        return baseDomain;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Class<?> getIdType() {
        return idType;
    }

    public void setIdType(Class<?> idType) {
        this.idType = idType;
    }

    public ColumnField[] getColumnFields() {
        return columnFields;
    }

    public void setColumnFields(ColumnField[] columnFields) {
        this.columnFields = columnFields;
    }

    public static Object getFieldValue(Class<?> clasz, String field, Object obj) throws FieldAccessException {
        try {
            Field f = clasz.getDeclaredField(field);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            String err = "get field " + field + " value error.";
            throw new FieldAccessException(err, new FieldAccessExceptionResult(ResultState.FIELDACCESS_ERROR.name(), err));
        }
    }

    public static void setFieldValue(Class<?> clasz, String field, Object obj, Object value) throws FieldAccessException {
        try {
            Field f = clasz.getDeclaredField(field);
            f.setAccessible(true);
            f.set(obj, value);
        } catch (Exception e) {
            String err = "assign field " + field + " to " + value + " error.";
            throw new FieldAccessException(err, new FieldAccessExceptionResult(ResultState.FIELDACCESS_ERROR.name(), err));
        }
    }


    public static class ColumnField {
        private String name;
        private Class<?> type;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Class<?> getType() {
            return type;
        }
        public void setType(Class<?> type) {
            this.type = type;
        }
    }
}


