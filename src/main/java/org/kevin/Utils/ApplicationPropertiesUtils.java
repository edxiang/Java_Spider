package org.kevin.Utils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * Created by Kevin.Z on 2017/11/18.
 */
public class ApplicationPropertiesUtils {
    private static Properties props = new Properties();

    static {
        try {
            String basePath = ApplicationPropertiesUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            basePath = URLDecoder.decode(basePath, "utf-8");
            String localFile = basePath + "\\" + "application.properties";
            InputStreamReader inputStream = new InputStreamReader(new FileInputStream(localFile), "UTF-8");
            props.load(inputStream);
            inputStream.close();

            LogUtils.setInfo("初始化配置文件");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}
