package org.example;

import java.util.Objects;

public class UsinasVO {

    private double id;
    private double vmax;
    private double vmin;
    private double pcv0;
    private double pcv1;
    private double pcv2;
    private double pcv3;
    private double pcv4;
    private double pcv5;
    private double prodesp;
    private double teif;
    private double iph;
    private double pinst;
    private double cfuga;
    private double cphid;


    public UsinasVO() {
    }

    public UsinasVO(double id, double vmax, double vmin, double pcv0, double pcv1, double pcv2, double pcv3, double pcv4, double pcv5, double prodesp, double teif, double iph, double pinst, double cfuga, double cphid) {
        this.id = id;
        this.vmax = vmax;
        this.vmin = vmin;
        this.pcv0 = pcv0;
        this.pcv1 = pcv1;
        this.pcv2 = pcv2;
        this.pcv3 = pcv3;
        this.pcv4 = pcv4;
        this.pcv5 = pcv5;
        this.prodesp = prodesp;
        this.teif = teif;
        this.iph = iph;
        this.pinst = pinst;
        this.cfuga = cfuga;
        this.cphid = cphid;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getVmax() {
        return vmax;
    }

    public void setVmax(double vmax) {
        this.vmax = vmax;
    }

    public double getVmin() {
        return vmin;
    }

    public void setVmin(double vmin) {
        this.vmin = vmin;
    }

    public double getPcv0() {
        return pcv0;
    }

    public void setPcv0(double pcv0) {
        this.pcv0 = pcv0;
    }

    public double getPcv1() {
        return pcv1;
    }

    public void setPcv1(double pcv1) {
        this.pcv1 = pcv1;
    }

    public double getPcv2() {
        return pcv2;
    }

    public void setPcv2(double pcv2) {
        this.pcv2 = pcv2;
    }

    public double getPcv3() {
        return pcv3;
    }

    public void setPcv3(double pcv3) {
        this.pcv3 = pcv3;
    }

    public double getPcv4() {
        return pcv4;
    }

    public void setPcv4(double pcv4) {
        this.pcv4 = pcv4;
    }

    public double getPcv5() {
        return pcv5;
    }

    public void setPcv5(double pcv5) {
        this.pcv5 = pcv5;
    }

    public double getProdesp() {
        return prodesp;
    }

    public void setProdesp(double prodesp) {
        this.prodesp = prodesp;
    }

    public double getTeif() {
        return teif;
    }

    public void setTeif(double teif) {
        this.teif = teif;
    }

    public double getIph() {
        return iph;
    }

    public void setIph(double iph) {
        this.iph = iph;
    }

    public double getPinst() {
        return pinst;
    }

    public void setPinst(double pinst) {
        this.pinst = pinst;
    }

    public double getCfuga() {
        return cfuga;
    }

    public void setCfuga(double cfuga) {
        this.cfuga = cfuga;
    }

    public double getCphid() {
        return cphid;
    }

    public void setCfugacphid(double cphid) {
        this.cphid = cphid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsinasVO hidroVO = (UsinasVO) o;
        return Double.compare(id, hidroVO.id) == 0 && Double.compare(vmax, hidroVO.vmax) == 0 && Double.compare(vmin, hidroVO.vmin) == 0 && Double.compare(pcv0, hidroVO.pcv0) == 0 && Double.compare(pcv1, hidroVO.pcv1) == 0 && Double.compare(pcv2, hidroVO.pcv2) == 0 && Double.compare(pcv3, hidroVO.pcv3) == 0 && Double.compare(pcv4, hidroVO.pcv4) == 0 && Double.compare(pcv5, hidroVO.pcv5) == 0 && Double.compare(prodesp, hidroVO.prodesp) == 0 && Double.compare(teif, hidroVO.teif) == 0 && Double.compare(iph, hidroVO.iph) == 0 && Double.compare(pinst, hidroVO.pinst) == 0 && Double.compare(cfuga, hidroVO.cfuga) == 0 && Double.compare(cphid, hidroVO.cphid) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vmax, vmin, pcv0, pcv1, pcv2, pcv3, pcv4, pcv5, prodesp, teif, iph, pinst, cfuga, cphid);
    }
}
