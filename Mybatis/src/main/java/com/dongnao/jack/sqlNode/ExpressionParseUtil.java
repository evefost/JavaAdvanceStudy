/** 
 * @Project     DN-jack-mybatis 
 * @File        ExpressionParseUtil.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016年10月27日 下午10:56:33 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.sqlNode;

import java.util.Map;

/** 
 * @Description mapper中表达式的解析 
 * @ClassName   ExpressionParseUtil 
 * @Date        2016年10月27日 下午10:56:33 
 * @Author      dongnao.jack 
 */

public class ExpressionParseUtil {
    
    public static <K, V> boolean expressionParse(Object param, String test) {
        
        if (param instanceof Map) {
            Map<K, V> paramMap = (Map<K, V>)param;
            
            for (Map.Entry<K, V> entry : paramMap.entrySet()) {
                if (test.indexOf(entry.getKey().toString()) > -1) {
                    String subStr = test.substring(test.indexOf(entry.getKey()
                            .toString()));
                    
                    if (subStr.indexOf("!=") > -1
                            && subStr.indexOf("null") > -1) {
                        if (entry.getValue() != null
                                && !"".equals(entry.getValue().toString())) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    public static StringBuffer parse(StringBuffer context, Object param) {
        
        String open = "#{";
        String close = "}";
        
        String after = context.toString();
        
        int start = after.indexOf(open);
        int end = after.indexOf(close);
        
        StringBuffer newSb = new StringBuffer();
        
        while (start > -1) {
            String before = after.substring(0, start);
            String replaceParam = after.substring(start + open.length(), end);
            after = after.substring(close.length() + end);
            
            String paramValue = "";
            
            if (param instanceof Map) {
                Map paramMap = (Map)param;
                if (paramMap.containsKey(replaceParam.trim())) {
                    paramValue = paramMap.get(replaceParam.trim()).toString();
                }
            }
            
            newSb.append(before).append(paramValue);
            
            start = after.indexOf(open);
            end = after.indexOf(close);
        }
        newSb.append(after);
        
        return newSb;
    }
}
