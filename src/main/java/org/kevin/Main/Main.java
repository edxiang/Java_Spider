package org.kevin.Main;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Kevin.Z on 2017/11/10.
 */
public class Main {
    /*private String path = "image";
    private String url = "http://sc.chinaz.com/tubiao/";
    private String temp = "http://sc.chinaz.com/tubiao/1344243472.htm : 银灰apple系列图标";

    private ExecutorService picPage = Executors.newFixedThreadPool(50);
//    ExecutorService imgPage = Executors.newFixedThreadPool(50);

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        new Main().mainPage();
        long cost = System.currentTimeMillis() - begin;
        System.out.println(cost / 1000);
    }

    private Document getDocument(String url){
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response1 = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            response1 = httpclient.execute(httpGet);
            HttpEntity entity = response1.getEntity();
            String web = EntityUtils.toString(entity, "utf-8");

            return Jsoup.parse(web);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(httpclient != null){
                    httpclient.close();
                }
                if(response1 != null){
                    response1.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    *//**
     * 主界面，
     *//*
    private void mainPage() {
        try {
            String sufix = "index.html";

            while(true) {
                Document document = getDocument(url + sufix);
                Element ul = document.select("ul.pngblock").first();
                if(ul == null){
                    continue;
                }

                Document docLink = Jsoup.parse(ul.outerHtml());
                Elements lis = docLink.select("li");
                for (Element e : lis) {
                    Element tagA = e.selectFirst("a");
                    final String href = tagA.attr("href");
                    final String title = tagA.attr("alt");
                    System.out.println(href + " : " + title);
                    picPage.execute(new Runnable() {
                        public void run() {
                            getImageUrl(href, title);
                        }
                    });
                }

                Element nxtPage = document.selectFirst("a.nextpage");
                sufix = nxtPage.attr("href");
                if(sufix.equals("")){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getImageUrl(String href, final String title){
        try {
            Document document = getDocument(href);
            Element div = document.selectFirst("div.png_sl");
            if(div == null){
                return;
            }
            Document docPng = Jsoup.parse(div.outerHtml());
            Elements divs = docPng.select("div.png_pic");
            for(Element e:divs){
                Element img = e.selectFirst("img");
                final String imgUrl = img.attr("src");
                *//*imgPage.execute(new Runnable() {
                    public void run() {
                        saveImage(imgUrl, title);
                    }
                });*//*
                saveImage(imgUrl, title);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void saveImage(String imageUrl, String title){
        imageUrl = imageUrl.replaceAll(" ", "%20");
        CloseableHttpClient httpClient = null;
        InputStream ipt = null;
        FileOutputStream fileOutputStream = null;
        try {
            httpClient = HttpClients.createDefault();
            ipt = httpClient.execute(new HttpGet(imageUrl)).getEntity().getContent();
            String name = imageUrl.substring(imageUrl.lastIndexOf("/"));
            name = name.replaceAll("%20", "_");
            File file = new File(path + "/" + title + "/" + name);
            if(!file.exists()){
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int length = 0;
            while((length = ipt.read(bytes)) != -1){
                fileOutputStream.write(bytes, 0 ,length);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            ipt.close();
            httpClient.close();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(httpClient != null){
                    httpClient.close();
                }
                if(ipt != null){
                    ipt.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }*/
}
