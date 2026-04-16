package com.quanlythuvien.domain;

import java.io.Serializable;
import java.util.Objects;

public class CungCapId implements Serializable {

    private String maNcc;
    private String maChiNhanh;

    public CungCapId() {
    }

    public CungCapId(String maNcc, String maChiNhanh) {
        this.maNcc = maNcc;
        this.maChiNhanh = maChiNhanh;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CungCapId that)) {
            return false;
        }
        return Objects.equals(maNcc, that.maNcc) && Objects.equals(maChiNhanh, that.maChiNhanh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNcc, maChiNhanh);
    }
}
