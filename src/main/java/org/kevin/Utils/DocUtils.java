package org.kevin.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Kevin.Z on 2017/11/16.
 */
public class DocUtils {

    /**
     * get the HTML document by URL
     *
     * @param url   the location of website
     * @return  Document
     */
    public static Document getDocument(String url){
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response1 = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response1 = httpclient.execute(httpGet);
            HttpEntity entity = response1.getEntity();
            String web = EntityUtils.toString(entity, "utf-8");

            return Jsoup.parse(web);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(httpclient != null){
                    httpclient.close();
                }
                if(response1 != null){
                    response1.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * get the Elements by Document
     *
     * @param document  the document of HTML
     * @param pattern   the matching statement
     * @return  Elements
     */
    public static Elements getElementsByDoc(Document document, String pattern){
        try {
            return document.select(pattern);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get the Element by Document
     *
     * @param document      the document of HTML
     * @param pattern       the matching statement
     * @return  Element
     */
    public static Element getElementByDoc(Document document, String pattern){
        try {
            return document.selectFirst(pattern);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get the Elements by Element
     *
     * @param element       the outhtml of tag
     * @param pattern       the matching statement
     * @return  Elements
     */
    public static Elements getElementsByEle(Element element, String pattern){
        try {
            return element.select(pattern);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get the Element by Element
     * @param element       the outhtml of tag
     * @param pattern       the matching statement
     * @return  Element
     */
    public static Element getElementByEle(Element element, String pattern){
        try {
            return element.selectFirst(pattern);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
