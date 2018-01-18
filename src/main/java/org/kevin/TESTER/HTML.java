package org.kevin.TESTER;

import org.jsoup.nodes.Document;
import org.kevin.Utils.DocUtils;

/**
 * Created by Kevin.Z on 2017/11/16.
 */
public class HTML {
    public static void main(String[] args) {

    }

    public static String getHtml(){
        Document d = DocUtils.getDocument("https://www.zhihu.com/explore");
        //System.out.println(d.outerHtml());
        return d.outerHtml();
    }
}
