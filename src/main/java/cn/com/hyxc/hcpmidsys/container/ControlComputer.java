package cn.com.hyxc.hcpmidsys.container;

public class ControlComputer {
    private String ckbh;
    private String jsjip;
    private String jsjlb;

    private ControlComputer(Builder builder) {
        this.ckbh = builder.ckbh;
        this.jsjip = builder.jsjip;
        this.jsjlb = builder.jsjlb;
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

    @Override
    public String toString() {
        return "ControlComputer{" +
                "ckbh='" + ckbh + '\'' +
                ", jsjip='" + jsjip + '\'' +
                ", jsjlb='" + jsjlb + '\'' +
                '}';
    }

    public static class Builder {
        private String ckbh;
        private String jsjip;
        private String jsjlb;

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

        public ControlComputer build() {
            return new ControlComputer(this);
        }
    }
}
