package org.kevin.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Kevin.Z on 2017/8/25.
 */
public class LogUtils {

    private final static Logger logger = LoggerFactory.getLogger(LogUtils.class);


    public static void setDebug(String msg){
        logger.debug(msg);
    }

    public static void setInfo(String msg){
        logger.info(msg);
    }

    public static void setWarn(String msg){
        logger.warn(msg);
    }

    public static void setError(String msg){
        logger.error(msg);
    }

}
