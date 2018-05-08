package org.kevin.TESTER;

import org.jsoup.nodes.Document;
import org.kevin.Utils.DocUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kevin.Z on 2017/11/16.
 */
public class HTML {
    private static String url = "https://book.douban.com/tag/%E5%B0%8F%E8%AF%B4";

    public static void main(String[] args) {
        try {
            URL url = new URL("https://v3.bootcss.com/");
            URLConnection c = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String s;
            while((s = br.readLine()) != null){
                sb.append(s + "\n");
            }
            System.out.println(sb.toString());
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(getHtml("https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"));
    }

    public static String getHtml(String url){
        Document d = DocUtils.getDocument(url);
        //System.out.println(d.outerHtml());
        return d.html();
//        return d.outerHtml();
    }
}
