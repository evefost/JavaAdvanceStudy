package com.xie.mybatis2.sqlNode;

/**
 * Created by xieyang on 17/1/26.
 */
public class StaticTextSqlNode implements SqlNode {

    private String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    public boolean appendTo(DynamicContext context) {
        context.getSb().append(text);
        return false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
