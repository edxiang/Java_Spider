package org.kevin.tester;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.kevin.utils.DocUtils;
import org.kevin.utils.FileUtils;
import org.kevin.utils.RegTag;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Kevin.Z on 2018/5/16.
 * Ok, failed.
 */
public class Tester {

    @Test
    public void testString(){
        String s = "https://manhua1023-61-174-50-98.cdndm5.com/7/6152/169650/1_6523.jpg?cid=169650&key=b0b8c7d92bf6326303166e47f834977c&type=1";
        int index1 = s.lastIndexOf("?");
        int index2 = s.lastIndexOf("/");
        String s1 = s.substring(0,index1);
        String s2 = s.substring(index2,index1);
        System.out.println(s1);
        System.out.println(s2);
    }
}
