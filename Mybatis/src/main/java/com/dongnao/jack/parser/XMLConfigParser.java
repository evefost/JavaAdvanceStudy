/** 
 * @Project     DN-jack-mybatis 
 * @File        XMLConfigParser.java 
 * @Package     com.dongnao.jack 
 * @Version     V1.0 
 * @Date        2016��10��25�� ����9:42:16 
 * @Author      dongnao.jack 
 */

package com.dongnao.jack.parser;

import java.io.InputStream;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dongnao.jack.configTemplate.Configuration;
import com.dongnao.jack.configTemplate.Environment;
import com.dongnao.jack.datasource.DataSource;
import com.dongnao.jack.datasource.DataSourceFactory;
import com.dongnao.jack.transation.TransationFactory;

/** 
 * @Description configuration������ 
 * @ClassName   XMLConfigParser 
 * @Date        2016��10��25�� ����9:42:16 
 * @Author      dongnao.jack 
 */

public class XMLConfigParser {
    
    public Configuration parse(InputStream is) throws Exception {
        
        Configuration config = parseConfiguration(is);
        
        return config;
    }
    
    private Configuration parseConfiguration(InputStream is) throws Exception {
        
        Configuration config = new Configuration();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document dc = builder.parse(is);
        
        NodeList nl = dc.getChildNodes();
        
        XMLMapperParser mapperParser = new XMLMapperParser();
        
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            
            if ("configuration".equals(node.getNodeName())) {
                NodeList childNodes = node.getChildNodes();
                
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node chlid = childNodes.item(j);
                    if (chlid.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    
                    if ("mappers".equals(chlid.getNodeName())) {
                        mapperParser.parse(chlid, config);
                    }
                    else if ("environments".equals(chlid.getNodeName())) {
                        parseEnvironments(chlid, config);
                    }
                }
                
            }
        }
        
        return config;
    }
    
    private void parseEnvironments(Node node, Configuration cf)
            throws Exception {
        Environment em = new Environment();
        
        NodeList nodes = node.getChildNodes();
        
        for (int j = 0; j < nodes.getLength(); j++) {
            Node chlid = nodes.item(j);
            if (chlid.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            
            if ("environment".equals(chlid.getNodeName().toLowerCase())) {
                NodeList childitems = chlid.getChildNodes();
                
                for (int i = 0; i < childitems.getLength(); i++) {
                    Node item = childitems.item(i);
                    
                    if (item.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    
                    if ("transactionmanager".equals(item.getNodeName()
                            .toLowerCase())) {
                        String type = XMLParseUtil.getAttrValueByName(item,
                                "type");
                        TransationFactory factory = (TransationFactory)XMLParseUtil.CLASS_TYPE.get(type)
                                .newInstance();
                        em.setTransationFactory(factory);
                    }
                    if ("datasource".equals(item.getNodeName().toLowerCase())) {
                        String type = XMLParseUtil.getAttrValueByName(item,
                                "type");
                        DataSourceFactory factory = (DataSourceFactory)XMLParseUtil.CLASS_TYPE.get(type)
                                .newInstance();
                        
                        NodeList propsNode = item.getChildNodes();
                        
                        for (int x = 0; x < propsNode.getLength(); x++) {
                            Node prop = propsNode.item(x);
                            
                            if (prop.getNodeType() != Node.ELEMENT_NODE) {
                                continue;
                            }
                            
                            String name = XMLParseUtil.getAttrValueByName(prop,
                                    "name");
                            String value = XMLParseUtil.getAttrValueByName(prop,
                                    "value");
                            Method setMethod = factory.getClass()
                                    .getMethod("set"
                                            + name.substring(0, 1)
                                                    .toUpperCase()
                                            + name.substring(1),
                                            String.class);
                            
                            setMethod.invoke(factory, value);
                        }
                        
                        DataSource dataSource = factory.getDataSource();
                        em.setDataSource(dataSource);
                    }
                }
            }
        }
        cf.setEnvironment(em);
    }
}
