package org.kevin.Main;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kevin.Utils.DocUtils;


/**
 * Created by Kevin.Z on 2018/5/8.
 */
public class DownloadWebsite {
    public static final String baseUrl = "https://v3.bootcss.com/";

    public static void main(String[] args) {
        new DownloadWebsite().getSourceCodeAndLink("");
    }

    private void getSourceCodeAndLink(String name) {
        Document d = DocUtils.getDocument(baseUrl);
        Elements linkTag = DocUtils.getElementsByDoc(d, "link");
        Elements scriptTag = DocUtils.getElementsByDoc(d, "script");
        Elements aTag = DocUtils.getElementsByDoc(d, "a");
/*
        System.out.println("here is the link:");
        getHref(linkTag, "href");
        System.out.println("here is the script:");
        getHref(scriptTag, "src");*/
        System.out.println("here is the a:");
        getHref(aTag, "href");
    }

    private void getHref(Elements elements, String mark) {
        for (Element e : elements) {
            String href = e.attr(mark);
            if (href.contains("#"))
                continue;
            if (href.contains("http") && !mark.equals("src"))
                continue;
            System.out.println(e.outerHtml());
            if(href.contains("../"))
                href = href.replaceAll("\\.\\./","");
            if(href.lastIndexOf("/") == href.length() - 1 && href.length() - 1 > 0){
                href = href.substring(0,href.length() - 1) + ".html";
            }
            System.out.println(" ---: " + href);
        }
        System.out.println("____________");
    }
}
