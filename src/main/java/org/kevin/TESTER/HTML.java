package org.kevin.TESTER;

import org.jsoup.nodes.Document;
import org.kevin.Utils.DocUtils;

/**
 * Created by Kevin.Z on 2017/11/16.
 */
public class HTML {
    private static String url = "https://book.douban.com/tag/%E5%B0%8F%E8%AF%B4";

    public static void main(String[] args) {
        System.out.println(getHtml("https://book.douban.com/tag/?view=cloud"));
    }

    public static String getHtml(String url){
        Document d = DocUtils.getDocument(url);
        //System.out.println(d.outerHtml());
        return d.outerHtml();
    }
}
