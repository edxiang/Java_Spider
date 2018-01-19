package org.kevin.MybatisUtils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by Kevin.Z on 2018/1/19.
 */
public class SQLUtils {
    public static SqlSession getInstance(){
        String resource = "conf.xml";
        InputStream is = SQLUtils.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        return ssf.openSession();
    }


}
