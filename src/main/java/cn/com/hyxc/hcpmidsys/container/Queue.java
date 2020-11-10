package cn.com.hyxc.hcpmidsys.container;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Queue{
    //排队号
    private String pdh;
    //人员类别
    private Integer rylb;
    //取号人姓名
    private String qhrxm;

    private Queue(Builder builder) {
        this.pdh = builder.pdh;
        this.rylb = builder.rylb;
        this.qhrxm = builder.qhrxm;
    }

    public String getPdh() {
        return pdh;
    }

    public Integer getRylb() {
        return rylb;
    }

    public String getQhrxm() {
        return qhrxm;
    }

    public void setPdh(String pdh) {
        this.pdh = pdh;
    }

    public void setRylb(Integer rylb) {
        this.rylb = rylb;
    }

    public void setQhrxm(String qhrxm) {
        this.qhrxm = qhrxm;
    }

    public static class Builder {
        //排队号
        private String pdh;
        //人员类别
        private Integer rylb;
        //取号人姓名
        private String qhrxm;

        public Builder setPdh(String pdh) {
            this.pdh = pdh;
            return this;
        }

        public Builder setRylb(Integer rylb) {
            this.rylb = rylb;
            return this;
        }

        public Builder setQhrxm(String qhrxm) {
            this.qhrxm = qhrxm;
            return this;
        }

        public Queue build() {
            return new Queue(this);
        }
    }

    @Override
    public String toString() {
        return "Queue{" +
                "pdh='" + pdh + '\'' +
                ", rylb=" + rylb +
                ", qhrxm='" + qhrxm + '\'' +
                '}';
    }
}
