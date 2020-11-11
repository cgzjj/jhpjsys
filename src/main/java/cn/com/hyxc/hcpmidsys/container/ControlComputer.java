package cn.com.hyxc.hcpmidsys.container;

import cn.com.hyxc.hcpmidsys.util.CommonUtil;

import java.util.UUID;

public class ControlComputer {
    /*
     * 设备控制计算机编号
     */
    private String sbkzjsjbh;
    /*
     * 窗口编号
     */
    private String ckbh;
    /*
     * 计算机IP
     */
    private String jsjip;
    /*
     * 计算机类别
     */
    private String jsjlb;
    /*
     * 可办业务类别
     */
    private String kbywlb;
    /*
     * 计算机序列号
     */
    private String uuid;
    /*
     * 取号信息序列号
     */
    private String qhxxxlh;
    /*
     * 窗口状态
     */
    private String status;


    private ControlComputer(Builder builder) {
        this.ckbh = builder.ckbh;
        this.jsjip = builder.jsjip;
        this.jsjlb = builder.jsjlb;
        this.sbkzjsjbh = builder.sbkzjsjbh;
        this.kbywlb = builder.kbywlb;

        this.uuid = CommonUtil.getNewUUID();
        this.status = "0";
    }

    public String getCkbh() {
        return ckbh;
    }

    public String getJsjip() {
        return jsjip;
    }

    public String getJsjlb() {
        return jsjlb;
    }

    public String getQhxxxlh() {
        return qhxxxlh;
    }

    public String getUuid() {
        return uuid;
    }

    public void setQhxxxlh(String qhxxxlh) {
        this.qhxxxlh = qhxxxlh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSbkzjsjbh() {
        return sbkzjsjbh;
    }

    public void setSbkzjsjbh(String sbkzjsjbh) {
        this.sbkzjsjbh = sbkzjsjbh;
    }

    public void setCkbh(String ckbh) {
        this.ckbh = ckbh;
    }

    public void setJsjip(String jsjip) {
        this.jsjip = jsjip;
    }

    public void setJsjlb(String jsjlb) {
        this.jsjlb = jsjlb;
    }

    public String getKbywlb() {
        return kbywlb;
    }

    public void setKbywlb(String kbywlb) {
        this.kbywlb = kbywlb;
    }

    @Override
    public String toString() {
        return "ControlComputer{" +
                "sbkzjsjbh='" + sbkzjsjbh + '\'' +
                ", ckbh='" + ckbh + '\'' +
                ", jsjip='" + jsjip + '\'' +
                ", jsjlb='" + jsjlb + '\'' +
                ", kbywlb='" + kbywlb + '\'' +
                ", uuid='" + uuid + '\'' +
                ", qhxxxlh='" + qhxxxlh + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * 建造者
     */
    public static class Builder {
        private String sbkzjsjbh;
        private String ckbh;
        private String jsjip;
        private String jsjlb;
        private String kbywlb;

        public Builder setCkbh(String ckbh) {
            this.ckbh = ckbh;
            return this;
        }

        public Builder setJsjip(String jsjip) {
            this.jsjip = jsjip;
            return this;
        }

        public Builder setJsjlb(String jsjlb) {
            this.jsjlb = jsjlb;
            return this;
        }

        public Builder setSbkzjsjbh(String sbkzjsjbh) {
            this.sbkzjsjbh = sbkzjsjbh;
            return this;
        }

        public Builder setKbywlb(String kbywlb) {
            this.kbywlb = kbywlb;
            return this;
        }

        public ControlComputer build() {
            return new ControlComputer(this);
        }
    }
}
