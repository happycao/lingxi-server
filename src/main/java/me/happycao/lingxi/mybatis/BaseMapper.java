package me.happycao.lingxi.mybatis;

import org.springframework.data.repository.NoRepositoryBean;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/04/15
 * desc   : BaseMapper
 * version: 1.0
 */
@NoRepositoryBean
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
