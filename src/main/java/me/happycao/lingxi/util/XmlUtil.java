package me.happycao.lingxi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

/**
 * @author : happyc
 * e-mail  : bafs.jy@live.com
 * time    : 2019/04/20
 * desc    : xml util
 * version : 1.0
 */
public class XmlUtil {

    /**
     * 解析xml转换为JSON对象
     *
     * @param xml xml字符串
     * @return json字符串
     */
    public static JSONObject parseIncXml2Json(String xml) throws Exception {
        String[] listProperty = {"page", "pagecount", "pagesize", "recordcount"};
        String[] videoProperty1 = {"id", "last", "tid", "name", "type", "pic",
                "lang", "area", "year", "note", "actor", "director", "des"};
        String[] videoProperty2 = {"dt", "note", "last", "name", "id", "type", "tid"};

        JSONObject rssJson = new JSONObject();
        JSONArray videoJsonArray = new JSONArray();

        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        Element list = root.element("list");
        if (list != null) {
            for (String property : listProperty) {
                rssJson.put(property, list.attributeValue(property));
            }
            Iterator<Element> videoIterator = list.elementIterator("video");
            while (videoIterator.hasNext()) {
                Element video = videoIterator.next();
                JSONObject videoJson = new JSONObject();
                Element dt = video.element("dt");
                boolean pass = false;
                if (dt != null) {
                    rssJson.put("flag", 2);
                    for (String property : videoProperty2) {
                        videoJson.put(property, video.elementTextTrim(property));
                    }
                }
                Element dl = video.element("dl");
                if (dl != null) {
                    rssJson.put("flag", 1);
                    for (String property : videoProperty1) {
                        if ("type".equals(property)) {
                            if ("伦理".equals(video.elementTextTrim(property))) {
                                pass = true;
                            }
                        }
                        videoJson.put(property, video.elementTextTrim(property));
                    }
                    Iterator<Element> ddIterator = dl.elementIterator("dd");
                    JSONArray sourceJsonArray = new JSONArray();
                    while (ddIterator.hasNext()) {
                        Element dd = ddIterator.next();
                        JSONObject sourceJson = new JSONObject();
                        String flag = dd.attributeValue("flag");
                        String stringValue = dd.getStringValue();
                        String[] split = stringValue.split("#");
                        JSONArray episodeJsonArray = new JSONArray();
                        for (String v : split) {
                            String[] s = v.split("\\$");
                            JSONObject episodeJson = new JSONObject();
                            episodeJson.put("title", s[0]);
                            episodeJson.put("url", s[1]);
                            episodeJsonArray.add(episodeJson);
                        }
                        sourceJson.put("flag", flag);
                        sourceJson.put("episode", episodeJsonArray);
                        sourceJsonArray.add(sourceJson);
                    }
                    videoJson.put("source", sourceJsonArray);
                }
                if (!pass) {
                    videoJsonArray.add(videoJson);
                }
            }
            rssJson.put("video", videoJsonArray);
        }
        return rssJson;
    }

    /**
     * 将xml转换为JSON对象
     *
     * @param xml xml字符串
     * @return json字符串
     * @throws Exception 异常
     */
    public static JSONObject xml2Json(String xml) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        iterateNodes(root, jsonObject);
        return jsonObject;
    }

    /**
     * 遍历元素
     *
     * @param node 元素
     * @param json 将元素遍历完成之后放的JSON对象
     */
    private static void iterateNodes(Element node, JSONObject json) {
        String nodeName = node.getName();

        // 判断已遍历的JSON中是否已经有了该元素的名称
        if (json.containsKey(nodeName)) {
            // 该元素在同级下有多个处理
            Object object = json.get(nodeName);
            JSONArray array;
            if (object instanceof JSONArray) {
                array = (JSONArray) object;
            } else {
                array = new JSONArray();
                array.add(object);
            }
            // 子元素
            List<Element> listElement = node.elements();
            if (listElement.isEmpty()) {
                String nodeValue = node.getTextTrim();
                // 有无Attribute
                JSONObject jsonObject = new JSONObject();
                Iterator<Attribute> attributeIterator = node.attributeIterator();
                if (attributeIterator.hasNext()) {
                    Attribute attribute = attributeIterator.next();
                    jsonObject.put(attribute.getName(), attribute.getStringValue());
                }
                if (jsonObject.isEmpty()) {
                    array.add(nodeValue);
                } else {
                    jsonObject.put(nodeName, nodeValue);
                    array.add(jsonObject);
                }
                json.put(nodeName, array);
                return;
            }
            // 有子元素
            JSONObject newJson = new JSONObject();
            for (Element e : listElement) {
                iterateNodes(e, newJson);
            }
            array.add(newJson);
            json.put(nodeName, array);
            return;
        }

        // 元素同级第一次遍历
        List<Element> listElement = node.elements();
        if (listElement.isEmpty()) {
            String nodeValue = node.getTextTrim();
            // Attribute
            JSONObject jsonObject = new JSONObject();
            Iterator<Attribute> attributeIterator = node.attributeIterator();
            if (attributeIterator.hasNext()) {
                Attribute attribute = attributeIterator.next();
                jsonObject.put(attribute.getName(), attribute.getStringValue());
            }
            if (jsonObject.isEmpty()) {
                json.put(nodeName, nodeValue);
            } else {
                jsonObject.put(nodeName, nodeValue);
                json.put(nodeName, jsonObject);
            }
            return;
        }
        // 有子节点，新建JSONObject来存储
        JSONObject object = new JSONObject();
        for (Element e : listElement) {
            iterateNodes(e, object);
        }
        json.put(nodeName, object);
        // Attribute
        Iterator<Attribute> attributeIterator = node.attributeIterator();
        while (attributeIterator.hasNext()) {
            Attribute next = attributeIterator.next();
            json.put(next.getName(), next.getValue());
        }
    }
}
