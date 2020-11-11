package cn.com.hyxc.hcpmidsys.container;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * 排队信息
 */
public class Queue {
    /*
     * 设备控制计算机ip
     */
    private String sbkzjsjip;
    /*
     * 取号信息序列号
     */
    private String qhxxxlh;
    /*
     * 排队号
     */
    private String pdh;
    /*
     * 业务类别
     */
    private String ywlb;
    /*
     * 身份证名号码
     */
    private String sfzmhm;
    /*
     *  代理人身份证明号码
     */
    private String dlrsfzmhm;
    /*
     *  取号人姓名
     */
    private String qhrxm;
    /*
     * 取号时间
     */
    private String qhsj;
    /*
     * 人员类别
     */
    private String rylb;
    /*
     * 人像比对结果
     */
    private String rxbdjg;
    /*
     * 人像比对相似度
     */
    private String rxbdxsd;
    /*
     * 基准照片来源
     */
    private String jzzply;
    /*
     * 基准比对照片
     */
    private String jzbdzp;
    /*
     * 现场照片
     */
    private String xczp;


    private Queue(Builder builder) {
        this.dlrsfzmhm = builder.dlrsfzmhm;
        this.jzbdzp = builder.jzbdzp;
        this.jzzply = builder.jzzply;
        this.ywlb = builder.ywlb;
        this.pdh = builder.pdh;
        this.qhrxm = builder.qhrxm;
        this.qhsj = builder.qhsj;
        this.qhxxxlh = builder.qhxxxlh;
        this.rxbdjg = builder.rxbdjg;
        this.rxbdxsd = builder.rxbdxsd;
        this.rylb = builder.rylb;
        this.sbkzjsjip = builder.sbkzjsjip;
        this.sfzmhm = builder.sfzmhm;
        this.xczp = builder.xczp;
    }

    public String getSbkzjsjip() {
        return sbkzjsjip;
    }

    public void setSbkzjsjip(String sbkzjsjip) {
        this.sbkzjsjip = sbkzjsjip;
    }

    public String getQhxxxlh() {
        return qhxxxlh;
    }

    public void setQhxxxlh(String qhxxxlh) {
        this.qhxxxlh = qhxxxlh;
    }

    public String getPdh() {
        return pdh;
    }

    public void setPdh(String pdh) {
        this.pdh = pdh;
    }

    public String getYwlb() {
        return ywlb;
    }

    public void setYwlb(String ywlb) {
        this.ywlb = ywlb;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getDlrsfzmhm() {
        return dlrsfzmhm;
    }

    public void setDlrsfzmhm(String dlrsfzmhm) {
        this.dlrsfzmhm = dlrsfzmhm;
    }

    public String getQhrxm() {
        return qhrxm;
    }

    public void setQhrxm(String qhrxm) {
        this.qhrxm = qhrxm;
    }

    public String getQhsj() {
        return qhsj;
    }

    public void setQhsj(String qhsj) {
        this.qhsj = qhsj;
    }

    public String getRylb() {
        return rylb;
    }

    public void setRylb(String rylb) {
        this.rylb = rylb;
    }

    public String getRxbdjg() {
        return rxbdjg;
    }

    public void setRxbdjg(String rxbdjg) {
        this.rxbdjg = rxbdjg;
    }

    public String getRxbdxsd() {
        return rxbdxsd;
    }

    public void setRxbdxsd(String rxbdxsd) {
        this.rxbdxsd = rxbdxsd;
    }

    public String getJzzply() {
        return jzzply;
    }

    public void setJzzply(String jzzply) {
        this.jzzply = jzzply;
    }

    public String getJzbdzp() {
        return jzbdzp;
    }

    public void setJzbdzp(String jzbdzp) {
        this.jzbdzp = jzbdzp;
    }

    public String getXczp() {
        return xczp;
    }

    public void setXczp(String xczp) {
        this.xczp = xczp;
    }

    public static class Builder {
        /*
         * 设备控制计算机ip
         */
        private String sbkzjsjip;
        /*
         * 取号信息序列号
         */
        private String qhxxxlh;
        /*
         * 排队号
         */
        private String pdh;
        /*
         * 业务类别
         */
        private String ywlb;
        /*
         * 身份证名号码
         */
        private String sfzmhm;
        /*
         *  代理人身份证明号码
         */
        private String dlrsfzmhm;
        /*
         *  取号人姓名
         */
        private String qhrxm;
        /*
         * 取号时间
         */
        private String qhsj;
        /*
         * 人员类别
         */
        private String rylb;
        /*
         * 人像比对结果
         */
        private String rxbdjg;
        /*
         * 人像比对相似度
         */
        private String rxbdxsd;
        /*
         * 基准照片来源
         */
        private String jzzply;
        /*
         * 基准比对照片
         */
        private String jzbdzp;
        /*
         * 现场照片
         */
        private String xczp;

        public Builder setSbkzjsjip(String sbkzjsjip) {
            this.sbkzjsjip = sbkzjsjip;
            return this;
        }

        public Builder setQhxxxlh(String qhxxxlh) {
            this.qhxxxlh = qhxxxlh;
            return this;
        }

        public Builder setPdh(String pdh) {
            this.pdh = pdh;
            return this;

        }

        public Builder setYwlb(String ywlb) {
            this.ywlb = ywlb;
            return this;
        }

        public Builder setSfzmhm(String sfzmhm) {
            this.sfzmhm = sfzmhm;
            return this;
        }

        public Builder setDlrsfzmhm(String dlrsfzmhm) {
            this.dlrsfzmhm = dlrsfzmhm;
            return this;
        }

        public Builder setQhrxm(String qhrxm) {
            this.qhrxm = qhrxm;
            return this;
        }

        public Builder setQhsj(String qhsj) {
            this.qhsj = qhsj;
            return this;
        }

        public Builder setRylb(String rylb) {
            this.rylb = rylb;
            return this;
        }

        public Builder setRxbdjg(String rxbdjg) {
            this.rxbdjg = rxbdjg;
            return this;
        }

        public Builder setRxbdxsd(String rxbdxsd) {
            this.rxbdxsd = rxbdxsd;
            return this;
        }

        public Builder setJzzply(String jzzply) {
            this.jzzply = jzzply;
            return this;
        }

        public Builder setJzbdzp(String jzbdzp) {
            this.jzbdzp = jzbdzp;
            return this;
        }

        public Builder setXczp(String xczp) {
            this.xczp = xczp;
            return this;
        }


        public Queue build() {
            return new Queue(this);
        }
    }

    @Override
    public String toString() {
        return "Queue{" +
                "sbkzjsjip='" + sbkzjsjip + '\'' +
                ", qhxxxlh='" + qhxxxlh + '\'' +
                ", pdh='" + pdh + '\'' +
                ", ywlb='" + ywlb + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", dlrsfzmhm='" + dlrsfzmhm + '\'' +
                ", qhrxm='" + qhrxm + '\'' +
                ", qhsj='" + qhsj + '\'' +
                ", rylb='" + rylb + '\'' +
                ", rxbdjg='" + rxbdjg + '\'' +
                ", rxbdxsd='" + rxbdxsd + '\'' +
                ", jzzply='" + jzzply + '\'' +
                ", jzbdzp='" + jzbdzp + '\'' +
                ", xczp='" + xczp + '\'' +
                '}';
    }
}
