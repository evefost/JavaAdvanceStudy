/** 
 * @Project     DN-jack-mybatis 
 * @File        StringTypeHandler.java 
 * @Package     com.dongnao.jack.typeHandler 
 * @Version     V1.0 
 * @Date        2016年10月31日 下午9:36:31 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.typeHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/** 
 * @Description TODO 
 * @ClassName   StringTypeHandler 
 * @Date        2016年10月31日 下午9:36:31 
 * @Author      dongnao.jack 
 */

public class StringTypeHandler implements TypeHandler {
    
    @Override
    public Object getResult(ResultSet rs, String columnName)
            throws SQLException {
        return rs.getString(columnName);
    }
    
}
