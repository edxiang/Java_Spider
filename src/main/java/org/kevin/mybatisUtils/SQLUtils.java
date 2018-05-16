package org.kevin.mybatisUtils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.kevin.entity.Books;

import java.io.InputStream;

/**
 * Created by Kevin.Z on 2018/1/19.
 */
public class SQLUtils {
    private static String booksInsert = "mapper.booksMapper.insert";
    private static String booksDelete = "mapper.booksMapper.delete";
    private static String booksCount = "mapper.booksMapper.count";
    private static String booksFind = "mapper.booksMapper.find";

    private static SqlSession getInstance(){
        String resource = "conf.xml";
        InputStream is = SQLUtils.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        return ssf.openSession();
    }

    public static int insert(Books b){
        SqlSession session = getInstance();
        int result = session.insert(booksInsert, b);
        session.commit();
        session.close();
        return result;
    }
}
