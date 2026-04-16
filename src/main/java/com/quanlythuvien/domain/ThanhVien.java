package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ThanhVien")
public class ThanhVien {

    @Id
    @Column(name = "ma_thanh_vien", length = 20)
    private String maThanhVien;

    @Column(name = "ho_ten", length = 255)
    private String hoTen;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "ngay_dang_ky")
    private LocalDate ngayDangKy;

    @Column(name = "tinh_trang_the", length = 100)
    private String tinhTrangThe;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    public String getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(String maThanhVien) {
        this.maThanhVien = maThanhVien;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDate getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(LocalDate ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public String getTinhTrangThe() {
        return tinhTrangThe;
    }

    public void setTinhTrangThe(String tinhTrangThe) {
        this.tinhTrangThe = tinhTrangThe;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
