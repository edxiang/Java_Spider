package org.kevin.Main;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kevin.utils.DocUtils;
import org.kevin.utils.FileUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Kevin.Z on 2018/5/16.
 */
// can't access the img...
public class Watchmen {
    public static final String BASE_URL = "https://www.manhuaren.com";
    public static final String comic = "Watchmen";
    private static AtomicInteger index = new AtomicInteger(1);
    private ExecutorService executor = Executors.newFixedThreadPool(50);

    public static void main(String[] args) {
        String loc = "/m62583/";
        String url = BASE_URL + loc;
        new Watchmen().getNext(url);
    }

    public void getNext(String url) {
        Document doc = DocUtils.getDocument(url);
        Element e = DocUtils.getElementByDoc(doc, "a.readTipForm");
        System.out.println(e.attr("href"));
        getNext(BASE_URL + e.attr("href"));
    }
}
