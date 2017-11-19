package org.kevin.TESTER;

import org.jsoup.nodes.Document;
import org.kevin.Utils.DocUtils;

/**
 * Created by Kevin.Z on 2017/11/16.
 */
public class HTML {
    public static void main(String[] args) {
        Document d = DocUtils.getDocument("https://www.nvshens.com/girl/21501/album/");
        System.out.println(d.outerHtml());
    }
}
