package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap {

    @Id
    @Column(name = "ma_ncc", length = 20)
    private String maNcc;

    @Column(name = "ten_ncc", length = 255)
    private String tenNcc;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
