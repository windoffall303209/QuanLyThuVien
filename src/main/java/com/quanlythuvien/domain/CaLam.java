package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "CaLam")
public class CaLam {

    @Id
    @Column(name = "ma_ca_lam", length = 20)
    private String maCaLam;

    @Column(name = "thoi_gian_bat_dau")
    private LocalTime thoiGianBatDau;

    @Column(name = "thoi_gian_ket_thuc")
    private LocalTime thoiGianKetThuc;

    public String getMaCaLam() {
        return maCaLam;
    }

    public void setMaCaLam(String maCaLam) {
        this.maCaLam = maCaLam;
    }

    public LocalTime getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(LocalTime thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public LocalTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }
}
