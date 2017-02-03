/**
 * @Project DN-jack-mybatis
 * @File TypeHandlerConfig.java
 * @Package com.dongnao.jack.typeHandler
 * @Version V1.0
 * @Date 2016年10月31日 下午9:37:53
 * @Author dongnao.jack
 */

package com.xie.mybatis2.typeHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @ClassName TypeHandlerConfig
 * @Date 2016年10月31日 下午9:37:53
 * @Author dongnao.jack
 */

public class TypeHandlerConfig {

    public static Map<String, TypeHandler> JDBC_TYPE_HANDLER = new HashMap<String, TypeHandler>();

    static {
        JDBC_TYPE_HANDLER.put("VARCHAR", new StringTypeHandler());
        JDBC_TYPE_HANDLER.put("DECIMAL", new IntegerTypeHandler());
    }
}
