package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.Relevant;
import me.happycao.lingxi.vo.RelevantVO;
import me.happycao.lingxi.vo.UserIdVO;

import java.util.List;

/**
 * author : Bafs
 * e-mail : bafs.jy@live.com
 * time   : 2018/02/05
 * desc   : 与我相关
 * version: 1.0
 */
public interface RelevantDao {

    Integer relevantTotal(UserIdVO userIdVO);

    List<Relevant> pageRelevant(RelevantVO relevantVO);

    Integer mineReplyTotal(UserIdVO userIdVO);

    List<Relevant> pageMineReply(RelevantVO relevantVO);
}