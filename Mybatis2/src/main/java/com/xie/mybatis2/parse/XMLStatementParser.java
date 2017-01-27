package com.xie.mybatis2.parse;

import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.configTemplate.MappedStatement;
import com.xie.mybatis2.sqlNode.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyang on 17/1/26.
 */
public class XMLStatementParser {

    public void parse(Configuration configuration, String namespace, Node node) {
        if (!node.hasAttributes()) {
            throw new RuntimeException("sql 节点没有属性");
        }

        MappedStatement ms = new MappedStatement();
        String id = XMLParseUtil.getAttrValueByName(node, "id");
        ms.setId(id);
        ms.setParameterType(XMLParseUtil.getAttrValueByName(node, "parameterType"));
        ms.setResultType(XMLParseUtil.getAttrValueByName(node, "resultType"));
        //添加namespace区分
        ms.setResultMapRef(namespace + "." + XMLParseUtil.getAttrValueByName(node, "resultMap"));
        //解释动态语句标签:<if> ,<trim> 等组合成的语句
        List<SqlNode> sqlNodes = parseDynamicSql(node);
        SqlSource sqlSource = new DynamicSqlSource(sqlNodes);
        ms.setSqlSource(sqlSource);

        //保存statement语句
        configuration.getStatementMap().put(namespace + "." + id, ms);

    }

    /**
     * 1,静态text 解释
     * 2.动态语句解释
     *
     * @param node
     * @return
     */
    private List<SqlNode> parseDynamicSql(Node node) {

        NodeList childNodes = node.getChildNodes();
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == Node.TEXT_NODE || item.getNodeType() == Node.CDATA_SECTION_NODE) {
                //静态text节点,直接拼接
                sqlNodes.add(new StaticTextSqlNode(item.getTextContent()));
            } else {
                //根据不同tag进行相当的处理
                NodeHandler handler = nodeHandlerMap.get(item.getNodeName());
                handler.handleNode(item,sqlNodes);
            }
        }
        return sqlNodes;
    }


    private Map<String, NodeHandler> nodeHandlerMap = new HashMap<String, NodeHandler>() {
        {
            put("if", new IfNodeHandler());
        }
    };

    private interface NodeHandler {
        //不同的实现进行不同的处理
        void handleNode(Node node, List<SqlNode> sqlnodes);
    }

    private class IfNodeHandler implements NodeHandler {

        public void handleNode(Node node, List<SqlNode> sqlnodes) {
            //解释node下的 <if>标签处理
            List<SqlNode> ifNodeList = parseDynamicSql(node);
            SqlNode ifSqlNode = new IfSqlNode(XMLParseUtil.getAttrValueByName(node,"test"),ifNodeList);
            sqlnodes.add(ifSqlNode);
        }
    }

}
