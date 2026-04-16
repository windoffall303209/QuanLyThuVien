package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ChamCong")
@IdClass(ChamCongId.class)
public class ChamCong {

    @Id
    @Column(name = "ma_ca_lam", length = 20)
    private String maCaLam;

    @Id
    @Column(name = "ma_thu_thu", length = 20)
    private String maThuThu;

    @Id
    @Column(name = "ngay_lam_viec")
    private LocalDate ngayLamViec;

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
}
