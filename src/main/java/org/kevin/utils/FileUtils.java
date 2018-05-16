package org.kevin.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by Kevin.Z on 2017/11/19.
 */
public class FileUtils {
    private static String basePath = ApplicationPropertiesUtils.getProperty("base.path");
    //private static String albumTitle = ApplicationPropertiesUtils.getProperty("album.title");
    private static String albumName = ApplicationPropertiesUtils.getProperty("album.name");
    private static String fileConnect = "/";

    public static File createFile(String title, String name) {
        try {
            //File file = new File(basePath + fileConnect + albumName + fileConnect + title + fileConnect + name);
            File file = new File(title);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    if (file.getParentFile().mkdirs())
                        if (file.createNewFile())
                            return file;
                } else {
                    if (file.createNewFile())
                        return file;
                }
            }
        } catch (Exception e) {
            LogUtils.setError("create file failed");
            e.printStackTrace();
        }
        return null;
    }

    public static String readContent(String path){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            StringBuilder sb = new StringBuilder();
            String s = "";
            while((s = br.readLine()) != null){
                sb.append(s + "\n");
            }
            return sb.toString();
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static boolean checkFileExist(String relativePath) {
        int split = relativePath.lastIndexOf("/") + 1;
        String folders = relativePath.substring(0, split);
        String name = relativePath.substring(split, relativePath.length());
        File file = new File(basePath + fileConnect + folders + fileConnect + name);
        return file.exists();
    }

    public static String getName(String s, String c) {
        if (s.contains(c)) {
            return s.substring(s.lastIndexOf(c) + 1);
        } else
            return null;
    }

    // works in css&js
    public static File newFile(String relativePath) {
        int split = relativePath.lastIndexOf("/") + 1;
        String folders = relativePath.substring(0, split);
        String name = relativePath.substring(split, relativePath.length());
        try {
            File file = new File(basePath + fileConnect + folders + fileConnect + name);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    if (file.getParentFile().mkdirs())
                        if (file.createNewFile())
                            return file;
                } else {
                    if (file.createNewFile())
                        return file;
                }
            }
        } catch (Exception e) {
            LogUtils.setError("create file failed" + relativePath);
            e.printStackTrace();
        }
        return null;
    }

    // works in css&js
    public static void copyFromWebsite(String relativePath, String targetUrl) {
        File targetFile = newFile(relativePath);
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            URL url = new URL(targetUrl);
            URLConnection c = url.openConnection();
            br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
            String s;
            while ((s = br.readLine()) != null) {
                bw.write(s + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (bw != null)
                    bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadPage(String relativePath, String targetUrl, Map<String, String> maps) {
        File targetFile = newFile(relativePath);
        if(targetFile == null) {
            LogUtils.setError("the file is null");
            return;
        }
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            URL url = new URL(targetUrl);
            URLConnection c = url.openConnection();
            br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile)));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                /*bw.write(s + "\n");*/
                sb.append(s + "\n");
            }
            s = sb.toString();
            for (String key : maps.keySet()) {
                if (s.contains(key)) {
                    s = s.replaceAll(key, maps.get(key));
                }
            }
            bw.write(s);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (bw != null)
                    bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadSource(String relativePath, String targetUrl) {
        File targetFile = createFile(relativePath,"hello");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            URL url = new URL(targetUrl);
            URLConnection c = url.openConnection();
            bis = new BufferedInputStream(c.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(targetFile));
            int length = -1;
            byte[] bs = new byte[4 * 1024];
            while ((length = bis.read(bs)) != -1) {
                bos.write(bs, 0, length);
            }
            System.out.println("finished: " + relativePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
