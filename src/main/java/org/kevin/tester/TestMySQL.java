package org.kevin.tester;

import org.junit.Test;


/**
 * Created by Kevin.Z on 2018/1/19.
 */
public class TestMySQL {
    @Test
    public void testSQL(){
        //SqlSession session = SQLUtils.getInstance();
        String insert = "mapper.booksMapper.insert";
        String delete = "mapper.booksMapper.delete";
        String count = "mapper.booksMapper.count";
        String fint = "mapper.booksMapper.find";

        /*Books b = new Books();
        b.setName("name");
        b.setAuthor("author");
        b.setPublisher("publisher");
        b.setPublishDate("Date");
        b.setPageNumber(3);
        b.setPrice(3.33);
        b.setIsbn("ISBN");
        b.setStar(9.9);
        b.setStarMan(9);
        b.setContent("this is the content;");
        int result = session.insert(statement,b);
        session.commit();
        */
        //System.out.print(session.selectOne(count));

    }
}
