package com.xie.mybatis2.parse;

import com.xie.mybatis2.configTemplate.Configuration;
import com.xie.mybatis2.configTemplate.Enviroment;
import com.xie.mybatis2.datasource.DataSource;
import com.xie.mybatis2.datasource.DataSourceFactory;
import com.xie.mybatis2.transation.TransationFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * 配置解释器
 * 1.解释环境配置
 * 2.解释Mapstatement(sql增删改查
 * 3.解释ResultMap(返回结果影射
 */
public class XmlConfigParser {

    public Configuration parse(InputStream is) throws Exception {
        Configuration configuration = parseConfiguration(is);
        return configuration;
    }

    private Configuration parseConfiguration(InputStream is) throws Exception {
        Configuration configuration = new Configuration();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(is);

        NodeList childNodes = document.getChildNodes();
        XMLmapperparser mapperparser = new XMLmapperparser();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if ("configuration".equals(item.getNodeName())) {
                NodeList nodeList = item.getChildNodes();
                for (int j = 0; j < nodeList.getLength(); j++) {
                    Node node = nodeList.item(j);
                    if (node.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    if ("environments".equals(node.getNodeName())) {
                        paserEnviroment(configuration, node);

                    }
                    if ("mappers".equals(node.getNodeName())) {
                        mapperparser.parse(configuration,node);
                    }
                }
            }
        }
        return configuration;

    }

    /**
     * 解释环境配置
     *
     * @param configuration
     * @param node
     */
    private void paserEnviroment(Configuration configuration, Node node) throws Exception {
        Enviroment enviroment = new Enviroment();
        configuration.setEnviroment(enviroment);
        NodeList childNodes = node.getChildNodes();
        for(int i=0;i<childNodes.getLength();i++){
            Node item = childNodes.item(i);
            if(item.getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            if("environment".equals(item.getNodeName())){
                NodeList nodeList = item.getChildNodes();
                for(int j=0;j<nodeList.getLength();j++){
                    Node childNode = nodeList.item(j);
                    if(childNode.getNodeType() != Node.ELEMENT_NODE){
                        continue;
                    }
                    if("transactionManager".equals(childNode.getNodeName())){
                        String type = XMLParseUtil.getAttrValueByName(childNode, "type");
                        TransationFactory tranFactory = (TransationFactory) XMLParseUtil.CLASS_TYPE.get(type).newInstance();
                        enviroment.setTransationFactory(tranFactory);
                    }
                    if("dataSource".equals(childNode.getNodeName())){
                        parseDataSourec(enviroment,childNode);
                    }
                }
            }
        }

    }


    private void parseDataSourec(Enviroment enviroment, Node node) throws Exception {
        String type = XMLParseUtil.getAttrValueByName(node, "type");
        DataSourceFactory factory = (DataSourceFactory) XMLParseUtil.CLASS_TYPE.get(type).newInstance();
        NodeList childNodes = node.getChildNodes();
        for(int i=0;i<childNodes.getLength();i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if("property".equals(item.getNodeName())){
                String name = XMLParseUtil.getAttrValueByName(item, "name");
                String value = XMLParseUtil.getAttrValueByName(item, "value");
                Field field = factory.getClass().getDeclaredField(name);
                field.setAccessible(true);
                field.set(factory,value);
            }
        }
        enviroment.setDataSource(factory.getDataSource());
    }
}
