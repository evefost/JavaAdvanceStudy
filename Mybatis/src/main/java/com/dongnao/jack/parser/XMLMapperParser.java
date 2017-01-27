/** 
 * @Project     DN-jack-mybatis 
 * @File        XMLMapperParser.java 
 * @Package     com.dongnao.jack.parser 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����9:58:33 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dongnao.jack.configTemplate.Configuration;
import com.dongnao.jack.configTemplate.MappedStatement;
import com.dongnao.jack.configTemplate.ResultMap;
import com.dongnao.jack.configTemplate.ResultMapping;

/** 
 * @Description ���������mapperxml�ļ� 
 * @ClassName   XMLMapperParser 
 * @Date        2016��10��25�� ����9:58:33 
 * @Author      dongnao.jack 
 */

public class XMLMapperParser {
    
    public void parse(Node node, Configuration cf) throws Exception {
        
        if (node == null) {
            throw new RuntimeException("node�ڵ㲻��Ϊ�գ�");
        }
        
        if (!node.hasChildNodes()) {
            throw new RuntimeException("node����û����Ҫ������mapper��");
        }
        
        NodeList nl = node.getChildNodes();
        
        for (int i = 0; i < nl.getLength(); i++) {
            Node item = nl.item(i);
            
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            MappedStatement ms = parseMapper(item, cf);
        }
        
    }
    
    private MappedStatement parseMapper(Node node, Configuration cf)
            throws Exception {
        if (!node.hasAttributes()) {
            return new MappedStatement();
        }
        
        String resource = XMLParseUtil.getAttrValueByName(node, "resource");
        parse(resource, cf);
        return null;
    }
    
    private void parse(String url, Configuration cf) throws Exception {
        
        if (url == null) {
            throw new RuntimeException("mapper����û��resource����");
        }
        
        InputStream is = XMLMapperParser.class.getResourceAsStream(url);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document dc = builder.parse(is);
        
        NodeList nl = dc.getChildNodes();
        
        XMLStatementParser statementParser = new XMLStatementParser();
        
        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            
            if (n.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            
            String ns = XMLParseUtil.getAttrValueByName(n, "namespace");
            
            NodeList chlidNodes = n.getChildNodes();
            
            for (int j = 0; j < chlidNodes.getLength(); j++) {
                Node childNode = chlidNodes.item(j);
                
                if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                
                if ("select".equals(childNode.getNodeName().toLowerCase())
                        || "insert".equals(childNode.getNodeName()
                                .toLowerCase())
                        || "delete".equals(childNode.getNodeName()
                                .toLowerCase())
                        || "update".equals(childNode.getNodeName()
                                .toLowerCase())) {
                    statementParser.parse(childNode, cf, ns);
                }
                else if ("resultmap".equals(childNode.getNodeName()
                        .toLowerCase())) {
                    parseResultMap(childNode, cf, ns);
                }
            }
        }
        
    }
    
    private void parseResultMap(Node node, Configuration cf, String namespace)
            throws Exception {
        
        ResultMap rm = new ResultMap();
        
        String id = XMLParseUtil.getAttrValueByName(node, "id");
        
        rm.setId(id);
        
        String type = XMLParseUtil.getAttrValueByName(node, "type");
        
        rm.setType(type);
        
        rm.setTypeClass(Class.forName(type));
        
        List<ResultMapping> idrmings = new ArrayList<ResultMapping>();
        List<ResultMapping> resultrmings = new ArrayList<ResultMapping>();
        parseIdmings(idrmings, node);
        parseResultming(resultrmings, node);
        
        rm.setIdResultMappings(idrmings);
        rm.setResultMappings(resultrmings);
        
        cf.getResultMaps().put(namespace + "." + id, rm);
    }
    
    /** 
     * @Description 解析resultMap中的<id column="ID" property="id"/>元素
     * @param @param idrmings
     * @param @param node 参数 
     * @return void 返回类型  
     * @throws 
     */
    private void parseIdmings(List<ResultMapping> idrmings, Node node) {
        NodeList nodes = node.getChildNodes();
        
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            
            if (item.getNodeType() == Node.ELEMENT_NODE
                    && "id".equals(item.getNodeName().toLowerCase())) {
                ResultMapping rming = new ResultMapping();
                rming.setColumn(XMLParseUtil.getAttrValueByName(item, "column"));
                rming.setProperty(XMLParseUtil.getAttrValueByName(item,
                        "property"));
                rming.setJdbcType(XMLParseUtil.getAttrValueByName(item,
                        "jdbcType"));
                rming.setJavaType(XMLParseUtil.getAttrValueByName(item,
                        "javaType"));
                idrmings.add(rming);
            }
        }
    }
    
    /** 
     * @Description 解析resultMap中的<result column="PSPTID" property="psptId" jdbcType="VARCHAR" javaType="string" /> 
     * @param @param resultrmings
     * @param @param node 参数 
     * @return void 返回类型  
     * @throws 
     */
    
    private void parseResultming(List<ResultMapping> resultrmings, Node node) {
        NodeList nodes = node.getChildNodes();
        
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            
            if (item.getNodeType() == Node.ELEMENT_NODE
                    && "result".equals(item.getNodeName().toLowerCase())) {
                ResultMapping resultming = new ResultMapping();
                resultming.setColumn(XMLParseUtil.getAttrValueByName(item,
                        "column"));
                resultming.setProperty(XMLParseUtil.getAttrValueByName(item,
                        "property"));
                resultming.setJdbcType(XMLParseUtil.getAttrValueByName(item,
                        "jdbcType"));
                resultming.setJavaType(XMLParseUtil.getAttrValueByName(item,
                        "javaType"));
                resultrmings.add(resultming);
            }
        }
    }
}
