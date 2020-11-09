package me.happycao.lingxi.model;

/**
 * @author : happyc
 * time    : 2020/11/09
 * desc    :
 * version : 1.0
 */
public class Topic {

    private String id;
    private String topicName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", topicName='" + topicName + '\'' +
                '}';
    }
}
