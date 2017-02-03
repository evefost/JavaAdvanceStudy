/**
 * @Project DN-jack-mybatis
 * @File TypeHandler.java
 * @Package com.dongnao.jack.typeHandler
 * @Version V1.0
 * @Date 2016年10月31日 下午9:35:30
 * @Author dongnao.jack
 */

package com.xie.mybatis2.typeHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description 负责java数据类型和jdbc数据类型之间的映射和转换
 * @ClassName TypeHandler
 * @Date 2016年10月31日 下午9:35:30
 * @Author dongnao.jack
 */

public interface TypeHandler {
    Object getResult(ResultSet rs, String columnName) throws SQLException;
}
