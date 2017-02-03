package com.xie.mybatis2.typeHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerTypeHandler implements TypeHandler {

    public Object getResult(ResultSet rs, String columnName)
            throws SQLException {
        return rs.getInt(columnName);
    }

}
