package cn.com.hyxc.hcpmidsys.modulehttp.entity;

/**
 * @Description: TODO
 * @author: scott
 * @date: 2020年11月12日 15:33
 */
public class LicencePlate {
    //流水号
    private String lsh;
    //申请人姓名
    private String xm;
    //牌证类型
    private String pzlx;
    //制证计算机IP
    private String zzjsjip;
    //领证窗口编号
    private String lzckbh;

    public String getLzckbh() {
        return lzckbh;
    }

    public void setLzckbh(String lzckbh) {
        this.lzckbh = lzckbh;
    }

    public String getZzjsjip() {
        return zzjsjip;
    }

    public void setZzjsjip(String zzjsjip) {
        this.zzjsjip = zzjsjip;
    }

    public String getPzlx() {
        return pzlx;
    }

    public void setPzlx(String pzlx) {
        this.pzlx = pzlx;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    private LicencePlate(Builder builder){
        this.lsh = builder.lsh;
        this.xm = builder.xm;
        this.pzlx = builder.pzlx;
        this.zzjsjip = builder.zzjsjip;
        this.lzckbh = builder.lzckbh;
    }

    public static class Builder{
        private String lsh;
        private String xm;
        private String pzlx;
        private String zzjsjip;
        private String lzckbh;

        public Builder setLsh(String lsh) {
            this.lsh = lsh;
            return this;
        }

        public Builder setXm(String xm) {
            this.xm = xm;
            return this;
        }

        public Builder setPzlx(String pzlx) {
            this.pzlx = pzlx;
            return this;
        }

        public Builder setZzjsjip(String zzjsjip) {
            this.zzjsjip = zzjsjip;
            return this;
        }

        public Builder setLzckbh(String lzckbh) {
            this.lzckbh = lzckbh;
            return this;
        }

        public LicencePlate bulid(){
            return new LicencePlate(this);
        }
    }
}
