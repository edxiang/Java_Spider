package org.kevin.Utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Kevin.Z on 2017/11/19.
 */
public class FileUtils {
    private static String basePath = ApplicationPropertiesUtils.getProperty("base.path");
    //private static String albumTitle = ApplicationPropertiesUtils.getProperty("album.title");
    private static String albumName = ApplicationPropertiesUtils.getProperty("album.name");
    private static String fileConnect = "/";

    public static File createFile(String title, String name){
        try {
            File file = new File(basePath + fileConnect + albumName + fileConnect + title + fileConnect + name);
            if(!file.exists()){
                if(!file.getParentFile().exists()){
                    if(file.getParentFile().mkdirs())
                        if(file.createNewFile())
                            return file;
                }else{
                    if(file.createNewFile())
                        return file;
                }
            }
        } catch(Exception e){
            LogUtils.setError("create file failed");
            e.printStackTrace();
        }
        return null;
    }

    public static String getName(String s, String c) {
        if (s.contains(c)) {
            return s.substring(s.lastIndexOf(c) + 1);
        } else
            return null;
    }
}
