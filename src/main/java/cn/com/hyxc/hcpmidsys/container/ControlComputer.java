package cn.com.hyxc.hcpmidsys.container;

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

    private ControlComputer(Builder builder) {
        this.ckbh = builder.ckbh;
        this.jsjip = builder.jsjip;
        this.jsjlb = builder.jsjlb;
        this.sbkzjsjbh = builder.sbkzjsjbh;
        this.kbywlb = builder.kbywlb;
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
