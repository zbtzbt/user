package com.zbt.user.datasource;

import org.jfaster.mango.partition.DataSourceRouter;

/**
 * @author Frank Zhang
 * @Time 16/5/6.
 */
public class UserDataSourceRouter implements DataSourceRouter<Integer> {

    public String getDataSourceName(Integer shardParam, int i) {
        return "dataSource" + (shardParam /100) % 10;
    }
}
