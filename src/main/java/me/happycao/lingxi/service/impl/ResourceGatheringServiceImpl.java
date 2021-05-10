package me.happycao.lingxi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.happycao.lingxi.service.ResourceGatheringService;
import me.happycao.lingxi.util.RestUtil;
import me.happycao.lingxi.util.XmlUtil;
import me.happycao.lingxi.vo.IncVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author : happyc
 * e-mail  : bafs.jy@live.com
 * time    : 2019/04/20
 * desc    : 资源采集
 * version : 1.0
 */
@Service
public class ResourceGatheringServiceImpl implements ResourceGatheringService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${inc-cnf.apiUrl}")
    private String incUrl;

    @Override
    public JSONObject directApi(IncVO incVO) {
        return getData(incVO, 1);
    }

    @Override
    public JSONObject parseApi(IncVO incVO) {
        return getData(incVO, 2);
    }

    private JSONObject getData(IncVO incVO, Integer flag) {
        if (incVO == null) {
            incVO = new IncVO();
            incVO.setT("4");
            incVO.setH("24");
            incVO.setAc("videolist");
        }

        String content = RestUtil.doGet()
                .url(incUrl)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0")
                .addUrlParam("ac", incVO.getAc())
                .addUrlParam("h", incVO.getH())
                .addUrlParam("pg", incVO.getPg())
                .addUrlParam("page", incVO.getPage())
                .addUrlParam("t", incVO.getT())
                .addUrlParam("type", incVO.getType())
                .addUrlParam("ids", incVO.getIds())
                .addUrlParam("mid", incVO.getMid())
                .addUrlParam("limit", incVO.getLimit())
                .addUrlParam("wd", incVO.getWd())
                .addUrlParam("param", incVO.getParam())
                .exchange(String.class);

        try {
            if (flag == 1) {
                return XmlUtil.xml2Json(content);
            } else {
                JSONObject jsonObject = XmlUtil.parseIncXml2Json(content);
                String rssFlag = jsonObject.getString("flag");
                if ("2".equals(rssFlag)) {
                    JSONArray videoArray = jsonObject.getJSONArray("video");
                    if (!videoArray.isEmpty()) {
                        StringBuilder ids = new StringBuilder();
                        for (int i = 0, size = videoArray.size(); i < size; i++) {
                            JSONObject video = videoArray.getJSONObject(i);
                            String id = video.getString("id");
                            ids.append(",").append(id);
                        }
                        ids.deleteCharAt(0);
                        incVO = new IncVO();
                        incVO.setAc("videolist");
                        incVO.setIds(ids.toString());
                        return getData(incVO, 2);
                    }
                }
                return jsonObject;
            }
        } catch (Exception e) {
            return new JSONObject();
        }
    }
}
