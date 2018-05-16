package org.kevin.Main;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kevin.utils.ApplicationPropertiesUtils;
import org.kevin.utils.DocUtils;
import org.kevin.utils.RegTag;
import org.kevin.entity.Books;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Kevin.Z on 2018/1/19.
 */
public class BookOfDouban {
    private final String url = ApplicationPropertiesUtils.getProperty("douban.book.url");
    private final String baseUrl = ApplicationPropertiesUtils.getProperty("douban.book.base.url");

    ExecutorService toDatabase = Executors.newFixedThreadPool(50);
    ExecutorService getPage = Executors.newFixedThreadPool(50);

    public static void main(String[] args) {
        new BookOfDouban().source();
    }

    /**
     * the tag of books.
     * such as: 小说 文学 散文 杂文……
     */
    private void source() {
        Document d = DocUtils.getDocument(url);
        Element e = DocUtils.getElementByDoc(d, "table.tagCol");
        Elements es = DocUtils.getElementsByEle(e, "td");
        int x = 0;
        for (Element ee : es) {
            final String targetPage = ee.selectFirst("a").attr("href");
            /*getPage.submit(new Runnable() {
                @Override
                public void run() {
                    listPage(baseUrl + targetPage);
                }
            });*/
            listPage(baseUrl + targetPage);
            x++;
            if(x > 10)
                break;
        }
        getPage.shutdown();
    }

    private void listPage(String targetPage) {
        Document d = DocUtils.getDocument(targetPage);
        Element e = DocUtils.getElementByDoc(d, "ul.subject-list");
        if (e == null)
            return;
        Elements es = DocUtils.getElementsByEle(e, "li");
        if (es == null)
            return;
        for (Element ee : es) {
            final String detailPage = ee.selectFirst("a").attr("href");
            toDatabase.submit(new Runnable() {
                @Override
                public void run() {
                    exeDetailPage(detailPage);
                }
            });
        }

        String s = d.outerHtml();
        if (s.contains("后页")) {
            Element ex = DocUtils.getElementByDoc(d, "span.next");
            String nextPage = ex.selectFirst("a").attr("href");
            try {
                Thread.sleep(1000);
                System.out.println("this is the next page:"+nextPage);
                //listPage(baseUrl + nextPage);
            } catch (Exception exx) {
                exx.printStackTrace();
            }
        }
        toDatabase.shutdown();
    }

    private void exeDetailPage(String targetPage) {
        try {
            Thread.sleep(900);
        } catch(Exception e){
            e.printStackTrace();
        }
        Books books = new Books();
        String realName = "";

        Document d = DocUtils.getDocument(targetPage);
        String name = DocUtils.getElementByDoc(d, "h1").text();
        books.setName(name);

        Element e = DocUtils.getElementByDoc(d, "div#info");
        String[] contents = e.outerHtml().split("<br>");
        for (String s : contents) {
            String tar = RegTag.HTMLTag(s);
            if (tar.contains(":")) {
                String[] ss = tar.split(":");
                String get = RegTag.blank(ss[1]);
                switch (ss[0]) {
                    case "作者":
                        books.setAuthor(get);
                        break;
                    case "出版社":
                        books.setPublisher(get);
                        break;
                    case "原作名":
                        realName = get;
                        break;
                    case "译者":
                        books.setTranslator(get);
                        break;
                    case "出版年":
                        books.setPublishDate(get);
                        break;
                    case "页数":
                        books.setPageNumber(Integer.parseInt(get));
                        break;
                    case "定价":
                        String price = RegTag.numbers(get);
                        books.setPrice(Double.parseDouble(price) / 100);
                        break;
                    case "装帧":
                        break;
                    case "丛书":
                        break;
                    case "ISBN":
                        books.setIsbn(get);
                        break;
                    default:
                        break;
                }
            }
        }
        if (!realName.equals(""))
            books.setName(books.getName() + " &_" + realName);

        // the introduction of this book
        Element eContentIntro = DocUtils.getElementByDoc(d, "div.intro");
        String introContent = eContentIntro.text();
        books.setContent(introContent);

        // the comments's number
        Element comments = DocUtils.getElementByDoc(d, "div.mod-hd");
        String numbers = RegTag.numbers(comments.text());
        books.setCommentNumber(Integer.parseInt(numbers));

        // the books' star and star people
        Element eStarDiv = DocUtils.getElementByDoc(d, "div.rating_self");
        String eStar = DocUtils.getElementByEle(eStarDiv, "strong").text().trim();
        books.setStar(Double.parseDouble(eStar));
        String eStarMan = DocUtils.getElementByEle(eStarDiv, "a").text().trim();
        eStarMan = RegTag.numbers(eStarMan);
        books.setStarMan(Integer.parseInt(eStarMan));

        System.out.println(books);
        //SQLUtils.insert(books);
    }
}
