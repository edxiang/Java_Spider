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

        /*String loc = "/m62583/";
        String url = BASE_URL + loc;
        new Watchmen().getNext(url);*/
    }

    public void getNext(String url) {
        if(index.get() == 3) {
            executor.shutdown();
            return;
        }
        Document doc = DocUtils.getDocument(url);
        Element readForm = DocUtils.getElementByDoc(doc, "div.readForm");
        System.out.println(readForm.outerHtml());
        Elements imgs = DocUtils.getElementsByEle(readForm, "img");
        /*for (Element img : imgs) {
            String src = img.attr("src");
            int index1 = src.lastIndexOf("/");
            int index2 = src.lastIndexOf("?");
            String imgName = src.substring(index1 + 1,index2);
            String path = comic + "/" + index.get() + "/" + imgName;
            String targetSource = src;
            System.out.println("begin to download: " + targetSource);
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    FileUtils.downloadSource(path,targetSource);
                }
            });
        }*/
        index.incrementAndGet();
        Element e = DocUtils.getElementByDoc(doc, "a.readTipForm");
        if (e == null) {
            executor.shutdown();
            return;
        }
        System.out.println(e.attr("href"));
        getNext(BASE_URL + e.attr("href"));
    }
}
