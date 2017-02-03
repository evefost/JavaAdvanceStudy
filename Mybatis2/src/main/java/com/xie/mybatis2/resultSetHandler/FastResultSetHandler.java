package com.xie.mybatis2.resultSetHandler;



import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.configTemplate.MappedStatement;
import com.xie.mybatis2.configTemplate.ResultMap;
import com.xie.mybatis2.configTemplate.ResultMapping;
import com.xie.mybatis2.typeHandler.TypeHandler;
import com.xie.mybatis2.typeHandler.TypeHandlerConfig;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FastResultSetHandler implements ResultSetHandler{
    private Statement stmt;

    private Configuration config;

    private MappedStatement ms;

    public FastResultSetHandler(Statement stmt, Configuration config,
                                MappedStatement ms) {
        this.stmt = stmt;
        this.config = config;
        this.ms = ms;
    }

    public <T> List<T> resultSetHandle(Statement stmt) {
        ResultMap resultMap = config.getResultMaps().get(ms.getResultMapRef());

        //List<ResultMapping> idrmpings = resultMap.getIdResultMappings();
        List<ResultMapping> resultpings = resultMap.getResultMappings();

        Class<?> type = resultMap.getTypeClass();

        List<T> result = new ArrayList<T>();

        try {
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                T instance = (T) type.newInstance();
                //idresultMappings(result, instance, rs, idrmpings);
                resultMappings(result, instance, rs, resultpings);
                result.add(instance);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }


    private <T> void resultMappings(List<T> result, Object instances,
                                    ResultSet rs, List<ResultMapping> idrmpings) {

        if (idrmpings == null || idrmpings.size() == 0) {
            return;
        }

        for (ResultMapping rm : idrmpings) {
            String column = rm.getColumn();
            String jdbcType = rm.getJdbcType();
            String javaType = rm.getJavaType();
            String property = rm.getProperty();

            TypeHandler typeHandler = TypeHandlerConfig.JDBC_TYPE_HANDLER.get(jdbcType);

            try {
                Object value = typeHandler.getResult(rs, column);
                Field field = instances.getClass().getDeclaredField(property);
                field.setAccessible(true);
                field.set(instances, value);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
