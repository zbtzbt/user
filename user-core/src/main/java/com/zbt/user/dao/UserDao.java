package com.zbt.user.dao;

import com.zbt.user.datasource.UserDataSourceRouter;
import com.zbt.user.model.User;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.DataSourceShardBy;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.TableShardBy;
import org.jfaster.mango.partition.IntegerModTenTablePartition;

/**
 * @author Frank Zhang
 * @Time 16/5/6.
 */

@DB(
    table = "user",
    dataSourceRouter = UserDataSourceRouter.class,
    tablePartition = IntegerModTenTablePartition.class
)
public interface UserDao {
    String COLUMNS = "user_id, name";

    @SQL("insert into #table(" + COLUMNS + ") values(:1, :2)")
    int addUser(@DataSourceShardBy @TableShardBy int userId, String name);

    @SQL("select user_id, name, GMT_CREATE, GMT_UPDATE from #table where user_id = :1")
    User getUser(@DataSourceShardBy @TableShardBy int userId);

    @SQL("update #table set name=:2 where user_id = :1")
    boolean updateUser(@DataSourceShardBy @TableShardBy int userId, String name);

    @SQL("delete from #table where user_id = :1")
    boolean deleteUser(@DataSourceShardBy @TableShardBy int userId);
}