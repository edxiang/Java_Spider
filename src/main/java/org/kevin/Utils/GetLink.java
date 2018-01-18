package org.kevin.Utils;

import org.kevin.TESTER.HTML;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kevin.Z on 2018/1/18.
 */
public class GetLink {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("href=\".+?\"");
        Matcher m = p.matcher(HTML.getHtml());
        while(m.find()){
            System.out.println(m.group());
        }
    }
}
