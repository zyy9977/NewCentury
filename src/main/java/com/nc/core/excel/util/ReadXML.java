package com.nc.core.excel.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadXML {

    private static final Logger logger = LogManager.getLogger(ReadXML.class);

    // private Resource resource = new FileSystemResource(this.getClass().getResource("/config/dbf-param.xml").getPath());
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Map<String, Object> recordConfig() throws IOException, ParserConfigurationException, SAXException {
        logger.debug("开始解析dbf表结构配置...");
        InputStream inputStream = resource.getInputStream();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element element = document.getDocumentElement();
        NodeList nodeListDbf = element.getElementsByTagName("dbf");
        Map<String, Object> dbfMap = new HashMap<>();
        List<Map<String, String>> dbfList = new ArrayList<>();
        dbfMap.put("dbfList", dbfList);
        for (int i = 0, length = nodeListDbf.getLength(); i < length; i++) {
            Element nodeDbf = (Element) nodeListDbf.item(i);
            dbfMap.put("id", nodeDbf.getAttribute("id"));
            dbfMap.put("name", nodeDbf.getAttribute("name"));
            logger.info("id = " + nodeDbf.getAttribute("id") + "  name = " + nodeDbf.getAttribute("name"));
            NodeList nodeListCol = nodeDbf.getElementsByTagName("col");
            for (int j = 0, size = nodeListCol.getLength(); j < size; j ++) {
                Map<String, String> colMap = new HashMap<>();
                Element nodeCol = (Element) nodeListCol.item(j);
                colMap.put("field", nodeCol.getAttribute("field"));
                colMap.put("type", nodeCol.getAttribute("type"));
                colMap.put("index", nodeCol.getAttribute("index"));
                colMap.put("width", nodeCol.getAttribute("width"));
                dbfList.add(colMap);
                logger.info("field = " + nodeCol.getAttribute("field") + "  type = " + nodeCol.getAttribute("type"));
            }
        }
        logger.debug("解析dbf表结构配置完成！");
        return dbfMap;
    }
}
