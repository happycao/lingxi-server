package me.happycao.lingxi.mapper;

import me.happycao.lingxi.entity.TFeedAction;
import me.happycao.lingxi.mybatis.BaseMapper;

public interface TFeedActionMapper extends BaseMapper<TFeedAction> {

    /**
     * 批量插入，Oralce需要设置useGeneratedKeys=false，不然报错
     *  Oracle批量插入：  insert all into table(...) values(...) into table(...) values(...) select * from dual
     *  Mysql批量插入：   insert into table(...) values(...),(...)
     * @param accounts
     * @return
     */
}