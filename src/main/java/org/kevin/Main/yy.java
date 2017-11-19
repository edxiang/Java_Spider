package org.kevin.Main;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kevin.Utils.ApplicationPropertiesUtils;
import org.kevin.Utils.DocUtils;
import org.kevin.Utils.FileUtils;
import org.kevin.Utils.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kevin.Z on 2017/11/10.
 */
public class yy {
    private String baseUrl = ApplicationPropertiesUtils.getProperty("base.url");

    private ExecutorService picPage = Executors.newFixedThreadPool(50);
    //private ExecutorService picPage = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //new yy().yyPage();
        new yy().detailPage("https://www.nvshens.com/girl/20440/album/");
    }

    private void yyPage() {
        String url = "https://www.nvshens.com/tag/huya/";
        int indexPage = 1;
        while (true) {
            LogUtils.setInfo("begin to get the " + indexPage + " page");
            String targetUrl = url + indexPage + ".html";
            Document d = DocUtils.getDocument(targetUrl);
            Element e = DocUtils.getElementByDoc(d, "div.listdiv");
            Elements es = DocUtils.getElementsByEle(e, "div.b-tmb");
            for (Element ee : es) {
                Element tempE = ee.selectFirst("a");
                detailPage(baseUrl + tempE.attr("href"));
            }
            LogUtils.setInfo("there are " + es.size() + " girl in this page");
            String s = d.outerHtml();
            if (s.contains("下一页")) {
                indexPage++;
            } else break;
        }
        picPage.shutdown();
    }

    private void detailPage(String detailUrl) {
        try {
            Document dd = DocUtils.getDocument(detailUrl);
            Elements egs = DocUtils.getElementsByDoc(dd, "div.igalleryli_div");
            if (egs == null || egs.size() == 0) {
                egs = DocUtils.getElementsByEle(dd, "div.p-tmb");
                if (egs == null || egs.size() == 0) {
                    LogUtils.setError("the url:" + detailUrl + " has not image link.");
                    return;
                }
            }
            for (Element eg : egs) {
                Element ega = DocUtils.getElementByEle(eg, "a");
                final String sufix = ega.attr("href");
                Element imgTitle = DocUtils.getElementByEle(eg, "img");
                final String title = imgTitle.attr("title");
                picPage.execute(new Runnable() {
                    public void run() {
                        getImageUrl(baseUrl + sufix, title);
                    }
                });
                System.err.println(baseUrl + sufix + "===" + title);
            }
            LogUtils.setInfo("there are " + egs.size() + " album in this girl");
        } catch (Exception e) {
            System.err.println("in detail page:" + detailUrl);
            LogUtils.setError("got wrong in detail page");
            e.printStackTrace();
        } finally {
            picPage.shutdown();
        }
    }

    private void getImageUrl(String href, final String title) {
        String pagesHref = href;
        Document document = DocUtils.getDocument(pagesHref);
        try {
            Elements inners = DocUtils.getElementsByDoc(document, "div.inner");
            if (inners == null || inners.size() == 0) {
                while (true) {
                    document = DocUtils.getDocument(pagesHref);
                    Element ul = DocUtils.getElementByDoc(document, "ul#hgallery");
                    if (ul == null) {
                        LogUtils.setError("there is not ul tag that name 'hgallery' in the ImagePage");
                        return;
                    } else {
                        Elements imgLinks = DocUtils.getElementsByEle(ul, "img");
                        for (Element imgLink : imgLinks) {
                            String imgUrl = imgLink.attr("src");
                            prepareDownload(imgUrl, title);
                        }

                        Element divPage = DocUtils.getElementByDoc(document, "div#pages");
                        Elements as = DocUtils.getElementsByEle(divPage, "a");
                        Element nxtPage = as.get(as.size() - 1);
                        String nxtHref = nxtPage.attr("href");
                        if (nxtHref.contains(".html"))
                            pagesHref = baseUrl + nxtHref;
                        else
                            break;
                    }
                }
                LogUtils.setInfo(title + "_album is finished.");
            } else {
                for (Element e : inners) {
                    String imgUrl = DocUtils.getElementByEle(e, "a").attr("href");
                    prepareDownload(imgUrl, title);
                }
                LogUtils.setInfo(title + " :album is finished.");
            }
        } catch (Exception e) {
            System.err.println("in getImageUrl:" + pagesHref);
            LogUtils.setError("got wrong in Image Page");
            e.printStackTrace();
        }
    }

    private void prepareDownload(String imgUrl, String title) {
        String fileName = FileUtils.getName(imgUrl, "/");
        if (fileName != null) {
            final File downloadFile = FileUtils.createFile(title, fileName);
            if (downloadFile != null)
                saveImage(imgUrl, downloadFile);
        }
    }

    private void saveImage(String imageUrl, File newFile) {
        imageUrl = imageUrl.replaceAll(" ", "%20");
        CloseableHttpClient httpClient = null;
        InputStream ipt = null;
        FileOutputStream fileOutputStream = null;
        try {
            httpClient = HttpClients.createDefault();
            ipt = httpClient.execute(new HttpGet(imageUrl)).getEntity().getContent();
            fileOutputStream = new FileOutputStream(newFile);

            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = ipt.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.flush();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            LogUtils.setError("read or connection time out. sleep for 1 second");
            e.printStackTrace();
            System.out.println(imageUrl);
            try {
                Thread.sleep(1000);
                saveImage(imageUrl, newFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            LogUtils.setError("got wrong in save image. url:" + imageUrl);
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (ipt != null) {
                    ipt.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
