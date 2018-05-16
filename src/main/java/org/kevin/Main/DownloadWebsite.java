package org.kevin.Main;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kevin.utils.DocUtils;
import org.kevin.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Kevin.Z on 2018/5/8.
 */
public class DownloadWebsite {
    public static final String baseUrl = "https://v3.bootcss.com/";
    private List<String> exist = new ArrayList<>();

    public static void main(String[] args) {
        new DownloadWebsite().getSourceCodeAndLink(baseUrl, "index");
    }

    private void getSourceCodeAndLink(String url, String name) {
        if (FileUtils.checkFileExist(name + ".html")) {
            System.out.println("already exist.");
            return;
        }

        Document d = DocUtils.getDocument(url);
        Elements linkTag = DocUtils.getElementsByDoc(d, "link");
        Elements scriptTag = DocUtils.getElementsByDoc(d, "script");
        Elements aTag = DocUtils.getElementsByDoc(d, "a");
        Elements imgTag = DocUtils.getElementsByDoc(d, "img");

        Map<String, String> maps = new HashMap<>();
        getHref(linkTag, "href", "link", maps);
        getHref(scriptTag, "src", "script", maps);
        getHref(imgTag, "src", "img", maps);
        getHref(aTag, "href", "a", maps);

        FileUtils.downloadPage(name + ".html", url, maps);
    }

    private void getHref(Elements elements, String mark, String tag, Map maps) {
        for (Element e : elements) {
            String href = e.attr(mark);
            String originHref = href;
            if (href.contains("#") || href.contains("http"))
                continue;
            if (href.contains("../")) {
                href = href.replaceAll("\\.\\./", "");
            }
            if (href.lastIndexOf("/") == href.length() - 1 && href.length() - 1 > 0) {
                href = href.substring(0, href.length() - 1);
            }
            if (href.equals(""))
                continue;

            if (tag.equals("a")) {
                if (exist.contains(href))
                    return;
                exist.add(href);
                if (!href.equals(originHref))
                    maps.put("=\"" + originHref, "=\"" + href + ".html");
                getSourceCodeAndLink(baseUrl + href, href);
            } else {
                if (!href.equals(originHref))
                    maps.put("=\"" + originHref, "=\"" + href);
                if (exist.contains(href))
                    return;
                exist.add(href);
                if (href.contains(".css") || href.contains(".js"))
                    FileUtils.copyFromWebsite(href, baseUrl + href);
                else
                    FileUtils.downloadSource(href, baseUrl + href);

            }
        }
    }
}
