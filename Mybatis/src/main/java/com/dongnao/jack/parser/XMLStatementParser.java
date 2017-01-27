/** 
 * @Project     DN-jack-mybatis 
 * @File        XMLStatementParser.java 
 * @Package     com.dongnao.jack.parser 
 * @Version     V1.0 
 * @Date        2016年10月25日 下午10:33:51 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dongnao.jack.configTemplate.Configuration;
import com.dongnao.jack.configTemplate.MappedStatement;
import com.dongnao.jack.sqlNode.DynamicSqlSource;
import com.dongnao.jack.sqlNode.IfSqlNode;
import com.dongnao.jack.sqlNode.SqlNode;
import com.dongnao.jack.sqlNode.SqlSource;
import com.dongnao.jack.sqlNode.StaticTextSqlNode;

/** 
 * @Description 解析我们的select、update、delete、insert 
 * @ClassName   XMLStatementParser 
 * @Date        2016年10月25日 下午10:33:51 
 * @Author      dongnao.jack 
 */

public class XMLStatementParser {
    
    public void parse(Node node, Configuration cf, String ns) {
        
        MappedStatement ms = new MappedStatement();
        
        String id = XMLParseUtil.getAttrValueByName(node, "id");
        
        ms.setId(id);
        
        String parameterType = XMLParseUtil.getAttrValueByName(node,
                "parameterType");
        
        ms.setParameterTypeClass(parameterType == null ? null
                : XMLParseUtil.PARAM_TYPE.get(parameterType.toLowerCase()));
        
        ms.setResultType(XMLParseUtil.getAttrValueByName(node, "resultType"));
        
        ms.setResultMapRef(ns + "."
                + XMLParseUtil.getAttrValueByName(node, "resultMap"));
        
        List<SqlNode> sqlnodes = parseDynamicTags(node);
        
        SqlSource sqlSource = new DynamicSqlSource(sqlnodes);
        
        ms.setSqlSource(sqlSource);
        
        cf.getMappedStatements().put(ns + "." + id, ms);
        
    }
    
    private List<SqlNode> parseDynamicTags(Node node) {
        
        NodeList nl = node.getChildNodes();
        
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            
            if (n.getNodeType() == Node.TEXT_NODE
                    || n.getNodeType() == Node.CDATA_SECTION_NODE) {
                sqlNodes.add(new StaticTextSqlNode(n.getTextContent()));
            }
            else {
                NodeHandler nodeHandler = nodeHandlerMap.get(n.getNodeName()
                        .toLowerCase());
                nodeHandler.handleNode(n, sqlNodes);
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
        public void handleNode(Node node, List<SqlNode> sqlnodes);
    }
    
    private class IfNodeHandler implements NodeHandler {
        
        public void handleNode(Node node, List<SqlNode> sqlnodes) {
            List<SqlNode> contexts = parseDynamicTags(node);
            SqlNode ifSqlNode = new IfSqlNode(contexts,
                    XMLParseUtil.getAttrValueByName(node, "test"));
            
            sqlnodes.add(ifSqlNode);
        }
    }
}
