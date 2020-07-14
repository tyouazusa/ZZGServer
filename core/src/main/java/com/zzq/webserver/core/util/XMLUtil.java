package com.zzq.webserver.core.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * Created by ZZQ on 2020/2/1.
 */
public class XMLUtil {
    
    public static Document getDocument(InputStream in) {
        try {
            SAXReader reader = new SAXReader();
            return reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
