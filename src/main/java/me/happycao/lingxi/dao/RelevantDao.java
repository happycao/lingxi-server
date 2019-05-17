package me.happycao.lingxi.dao;

import me.happycao.lingxi.model.Relevant;
import me.happycao.lingxi.vo.RelevantVO;
import me.happycao.lingxi.vo.UserIdVO;

import java.util.List;

public interface RelevantDao {

    Integer relevantTotal(UserIdVO userIdVO);

    List<Relevant> pageRelevant(RelevantVO relevantVO);

    Integer mineReplyTotal(UserIdVO userIdVO);

    List<Relevant> pageMineReply(RelevantVO relevantVO);
}