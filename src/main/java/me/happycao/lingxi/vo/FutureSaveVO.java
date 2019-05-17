package me.happycao.lingxi.vo;

/**
 * @author : happyc
 * e-mail : bafs.jy@live.com
 * time   : 2018/07/16
 * desc   : 写给未来参数
 * version: 1.0
 */
public class FutureSaveVO {

    private Integer type;
    private String mail;
    private String futureInfo;
    private Integer startNum;
    private Integer endNum;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFutureInfo() {
        return futureInfo;
    }

    public void setFutureInfo(String futureInfo) {
        this.futureInfo = futureInfo;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getEndNum() {
        return endNum;
    }

    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }

    @Override
    public String toString() {
        return "FutureSaveVO{" +
                "type=" + type +
                ", mail='" + mail + '\'' +
                ", futureInfo='" + futureInfo + '\'' +
                ", startNum=" + startNum +
                ", endNum=" + endNum +
                '}';
    }
}
