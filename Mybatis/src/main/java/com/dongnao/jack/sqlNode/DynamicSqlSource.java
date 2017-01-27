/** 
 * @Project     DN-jack-mybatis 
 * @File        DynamicSqlSource.java 
 * @Package     com.dongnao.jack.sqlNode 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����11:19:54 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.sqlNode;

import java.util.List;

/** 
 * @Description TODO 
 * @ClassName   DynamicSqlSource 
 * @Date        2016��10��25�� ����11:19:54 
 * @Author      dongnao.jack 
 */

public class DynamicSqlSource implements SqlSource {
    
    List<SqlNode> sqlNode;
    
    public DynamicSqlSource(List<SqlNode> sqlNode) {
        this.sqlNode = sqlNode;
    }
    
    public BoundSql getBoundSql(Object param) {
        DynamicContext context = new DynamicContext(new StringBuffer(), param);
        
        for (SqlNode sqlnode : sqlNode) {
            sqlnode.appendTo(context);
        }
        
        StringBuffer sql = ExpressionParseUtil.parse(context.getSb(), param);
        
        return new BoundSql(sql.toString());
    }
    
    public List<SqlNode> getSqlNode() {
        return sqlNode;
    }
    
    public void setSqlNode(List<SqlNode> sqlNode) {
        this.sqlNode = sqlNode;
    }
}
