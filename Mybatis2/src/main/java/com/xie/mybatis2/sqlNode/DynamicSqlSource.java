package com.xie.mybatis2.sqlNode;

import com.dongnao.jack.sqlNode.ExpressionParseUtil;

import java.util.List;

/**
 * Created by xieyang on 17/1/26.
 */
public class DynamicSqlSource implements SqlSource {

    private List<SqlNode> sqlNodes;

    public DynamicSqlSource(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    /**
     * 为什么这样子做
     *
     * @param param
     * @return
     */
    public BoundSql getBoundSql(Object param) {

        DynamicContext context = new DynamicContext(new StringBuffer(), param);

        for (SqlNode sqlnode : sqlNodes) {
            sqlnode.appendTo(context);
        }

        StringBuffer sql = ExpressionParseUtil.parse(context.getSb(), param);

        return new BoundSql(sql.toString());
    }
}
