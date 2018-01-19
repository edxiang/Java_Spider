package org.kevin.Utils;

import org.kevin.TESTER.HTML;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kevin.Z on 2018/1/18.
 */
public class GetLink {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        Pattern p = Pattern.compile("href=\".+?\"");
        Matcher m = p.matcher(HTML.getHtml("https://book.douban.com/tag/?view=cloud"));
        while(m.find()){
            System.out.println(m.group());
        }
        System.err.println(System.currentTimeMillis() - now);
    }
}
