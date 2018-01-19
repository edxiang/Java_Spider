package org.kevin.Main;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kevin.Utils.ApplicationPropertiesUtils;
import org.kevin.Utils.DocUtils;

/**
 * Created by Kevin.Z on 2018/1/19.
 */
public class BookOfDouban {
    private final String url = ApplicationPropertiesUtils.getProperty("douban.book.url");
    private final String baseUrl = ApplicationPropertiesUtils.getProperty("douban.book.base.url");

    public static void main(String[] args) {
        new BookOfDouban().source();
    }

    private void source() {
        Document d = DocUtils.getDocument(url);
        Element e = DocUtils.getElementByDoc(d, "table.tagCol");
        Elements es = DocUtils.getElementsByEle(e, "td");
        int x = 0;
        for (Element ee : es) {
            String targetPage = ee.selectFirst("a").attr("href");
            System.err.println(listPage(baseUrl + targetPage,0));
            /*x++;
            if (x > 10)*/
                break;
        }
    }

    private int listPage(String targetPage,int index){
        int sum = 0;
        while(true) {
            Document d = DocUtils.getDocument(targetPage);
            Element e = DocUtils.getElementByDoc(d, "ul.subject-list");
            if(e == null)
                return 0 ;
            Elements es = DocUtils.getElementsByEle(e, "li");
            if(es == null)
                return 0;
            for (Element ee : es) {
                String detailPage = ee.selectFirst("a").attr("href");
                sum++;
            }
            String s = d.outerHtml();
            if(s.contains("后页")){
                System.out.println(++index);
                Element ex = DocUtils.getElementByDoc(d,"span.next");
                String nextPage = ex.selectFirst("a").attr("href");
                try {
                    Thread.sleep(1000);
                    sum += listPage(baseUrl + nextPage,index);
                } catch(Exception exx){
                    exx.printStackTrace();
                }

            }else{
                break;
            }
        }
        return sum;
    }
}
