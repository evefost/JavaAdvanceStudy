package com.xie.mybatis2.parse;

import com.xie.mybatis2.configTemplate.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * Created by xieyang on 17/1/26.
 */
public class XMLmapperparser {

    /**
     *   <mapper resource="/com/dongnao/jack/config/BlogMapper.xml"/>
     * @param configuration
     * @param node
     */
    public void parse(Configuration configuration, Node node) throws Exception {
        if(node == null){
            throw new RuntimeException("节点为空");
        }
        if(!node.hasChildNodes()){
            throw new RuntimeException("mappers没有子节");
        }

        NodeList childNodes = node.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if ("mapper".equals(item.getNodeName())) {

                parseMapper(configuration,item);
            }
        }
    }

    private void parseMapper(Configuration configuration, Node node) throws Exception {
        if(!node.hasAttributes()){
            throw  new RuntimeException("mapper节点没有属性");
        }
        String path = XMLParseUtil.getAttrValueByName(node, "resource");
        if(path == null){
            throw  new RuntimeException("mapper节点没有mapper.xml路径");
        }
        parse(configuration,path);

    }

    /**
     * 开始解释 select|update|insert|query语句
     * @param path
     * @throws Exception
     */
    private void parse(Configuration configuration,String path) throws Exception {

        InputStream is = XMLmapperparser.class.getClassLoader().getResourceAsStream(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);

        NodeList childNodes = document.getChildNodes();

        XMLStatementParser stateMentParser = new XMLStatementParser();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if ("mapper".equals(item.getNodeName())) {
                String namespace = XMLParseUtil.getAttrValueByName(item, "namespace");

                NodeList nodeList = item.getChildNodes();
                for (int j = 0; j < nodeList.getLength(); j++) {
                    Node node = nodeList.item(j);
                    if (node.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    String nodeName = node.getNodeName().toLowerCase();
                    if ("select".equals(nodeName)
                            ||"update".equals(nodeName)
                            ||"delete".equals(nodeName)
                            ||"insert".equals(nodeName)) {
                        //获取增删改查语句
                        stateMentParser.parse(configuration,namespace,node);

                    }else if("resultMap".equals(nodeName)){
                        //解释resultMap
                        parseResultMap(configuration,namespace,node);
                    }
                }
            }
        }
    }

    private void parseResultMap(Configuration configuration, String namespace, Node node) {

    }
}
