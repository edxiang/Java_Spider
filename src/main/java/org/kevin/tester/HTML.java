package org.kevin.tester;

import org.jsoup.nodes.Document;
import org.kevin.utils.DocUtils;

/**
 * Created by Kevin.Z on 2017/11/16.
 */
public class HTML {
    private static String url = "https://book.douban.com/tag/%E5%B0%8F%E8%AF%B4";

    public static void main(String[] args) {
        /*try {
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
        }*/
        System.out.println(getHtml("https://www.manhuaren.com/m62583/"));
    }

    public static String getHtml(String url){
        Document d = DocUtils.getDocument(url);
        //System.out.println(d.outerHtml());
        return d.html();
//        return d.outerHtml();
    }
}
