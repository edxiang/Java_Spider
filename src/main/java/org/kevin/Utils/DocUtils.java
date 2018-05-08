package org.kevin.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
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
     * @param url the location of website
     * @return Document
     */
    public static Document getDocument(String url) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response1 = null;
        CookieStore cookieStore = new BasicCookieStore();

        try {
            httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
            HttpGet httpGet = new HttpGet(url);

            //httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            /*httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            httpGet.setHeader("Cache-Control", "no-cache");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", "ll=\"118297\"; bid=sG6P5mzxYAU; _vwo_uuid_v2=6BD6F0A7330FC0E68164107EA96E44C6|70ba1b72a9de429884e8a66b519f8a36; ap=1; gr_user_id=9cff0523-22f1-4589-8add-2ed51934bd20; __yadk_uid=wFQdMS3qYKWjvbxkDidoMtwIQUwqbNuy; __utmc=30149280; __utmc=81379588; viewed=\"26939973_5346110_25862578_3259440_1223845_1402820_1463565_26321569_26963900\"; ps=y; ue=\"229270808@qq.com\"; push_noty_num=0; push_doumail_num=0; ct=y; dbcl2=\"73662444:GUw3kD8An6I\"; _ga=GA1.2.32593363.1516094583; _gid=GA1.2.219550953.1516356465; _gat_UA-7019765-1=1; ck=oWr5; _pk_ref.100001.3ac3=%5B%22%22%2C%22%22%2C1516356468%2C%22https%3A%2F%2Fwww.douban.com%2F%22%5D; _pk_id.100001.3ac3=110f8ef29e5c7376.1516260062.6.1516356468.1516351143.; _pk_ses.100001.3ac3=*; __utma=30149280.32593363.1516094583.1516353181.1516356469.8; __utmz=30149280.1516356469.8.3.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __utmt_douban=1; __utmb=30149280.1.10.1516356469; __utma=81379588.1439248292.1516260062.1516346004.1516356469.6; __utmz=81379588.1516356469.6.2.utmcsr=douban.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __utmt=1; __utmb=81379588.1.10.1516356469");
            httpGet.setHeader("Host", "book.douban.com");
            httpGet.setHeader("Referer", "https://www.douban.com/");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");*/

            /*HttpHost proxy = new HttpHost("27.194.41.223",8118,"HTTP");
            response1 = httpclient.execute(proxy,httpGet);*/

            response1 = httpclient.execute(httpGet);

            HttpEntity entity = response1.getEntity();

            String web = EntityUtils.toString(entity, "utf-8");

            return Jsoup.parse(web);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (response1 != null) {
                    response1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get the Elements by Document
     *
     * @param document the document of HTML
     * @param pattern  the matching statement
     * @return Elements
     */
    public static Elements getElementsByDoc(Document document, String pattern) {
        try {
            return document.select(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get the Element by Document
     *
     * @param document the document of HTML
     * @param pattern  the matching statement
     * @return Element
     */
    public static Element getElementByDoc(Document document, String pattern) {
        try {
            return document.selectFirst(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get the Elements by Element
     *
     * @param element the outhtml of tag
     * @param pattern the matching statement
     * @return Elements
     */
    public static Elements getElementsByEle(Element element, String pattern) {
        try {
            return element.select(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get the Element by Element
     *
     * @param element the outhtml of tag
     * @param pattern the matching statement
     * @return Element
     */
    public static Element getElementByEle(Element element, String pattern) {
        try {
            return element.selectFirst(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
