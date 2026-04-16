package com.quanlythuvien.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ChamCongId implements Serializable {

    private String maCaLam;
    private String maThuThu;
    private LocalDate ngayLamViec;

    public ChamCongId() {
    }

    public ChamCongId(String maCaLam, String maThuThu, LocalDate ngayLamViec) {
        this.maCaLam = maCaLam;
        this.maThuThu = maThuThu;
        this.ngayLamViec = ngayLamViec;
    }

    public String getMaCaLam() {
        return maCaLam;
    }

    public void setMaCaLam(String maCaLam) {
        this.maCaLam = maCaLam;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public LocalDate getNgayLamViec() {
        return ngayLamViec;
    }

    public void setNgayLamViec(LocalDate ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChamCongId that)) {
            return false;
        }
        return Objects.equals(maCaLam, that.maCaLam)
                && Objects.equals(maThuThu, that.maThuThu)
                && Objects.equals(ngayLamViec, that.ngayLamViec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maCaLam, maThuThu, ngayLamViec);
    }
}
