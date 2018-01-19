package org.kevin.MybatisUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * Created by Kevin.Z on 2018/1/19.
 */
public class C3P0DataSourceFactory extends UnpooledDataSourceFactory{
    public C3P0DataSourceFactory(){
        this.dataSource = new ComboPooledDataSource();
    }
}
