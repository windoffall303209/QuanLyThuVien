package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ThuThu")
public class ThuThu {

    @Id
    @Column(name = "ma_thu_thu", length = 20)
    private String maThuThu;

    @Column(name = "ho_ten", length = 255)
    private String hoTen;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "ma_chi_nhanh", length = 20)
    private String maChiNhanh;

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }
}
