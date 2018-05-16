package org.kevin.utils;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kevin.Z on 2018/1/22.
 */
public class RegTag {
    private RegTag(){}

    /**
     * delete the HTML tag
     *
     * @param cnt       the html page source
     * @return          the content without the Html tag.
     */
    public static String HTMLTag(String cnt){
        Pattern p = Pattern.compile("<.+?>");
        Matcher m = p.matcher(cnt);
        return m.replaceAll("").trim();
    }

    /**
     * delete the whitespace character if there are 3 or more times
     *
     * @param cnt       the html page source
     * @return          the content without the whitespace character
     */
    public static String blank(String cnt){
        Pattern p = Pattern.compile("[\\s]{2,}");
        Matcher m = p.matcher(cnt);
        return m.replaceAll("").replaceAll("&nbsp;","").trim();
    }

    /**
     * get the number from the Html page source
     *
     * @param cnt       the html page source
     * @return          the content of number
     */
    public static String numbers(String cnt){
        Pattern p = Pattern.compile("[\\D]");
        Matcher m = p.matcher(cnt);
        return m.replaceAll("").trim();
    }
    
    public static String ChangeToUnicode(String s){
        try {
            s = URLEncoder.encode(s,"UTF-8");
        } catch(Exception e){
            e.printStackTrace();
        }

        s = s.replaceAll("%3A",":");
        s = s.replaceAll("%2F","/");
        s = s.replaceAll("%3F","?");
        s = s.replaceAll("%3D","=");
        s = s.replaceAll("%26","&");
        return s;
    }
}
