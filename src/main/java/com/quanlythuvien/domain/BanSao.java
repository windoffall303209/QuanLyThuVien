package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "BanSao")
@IdClass(BanSaoId.class)
public class BanSao {

    @Id
    @Column(name = "ma_sach", length = 20)
    private String maSach;

    @Id
    @Column(name = "copy_no")
    private Integer copyNo;

    @Column(name = "ngay_nhap")
    private LocalDate ngayNhap;

    @Column(name = "tinh_trang_muon", length = 100)
    private String tinhTrangMuon;

    @Column(name = "tinh_trang_vat_ly", length = 100)
    private String tinhTrangVatLy;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @Column(name = "ma_chi_nhanh", length = 20)
    private String maChiNhanh;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Integer getCopyNo() {
        return copyNo;
    }

    public void setCopyNo(Integer copyNo) {
        this.copyNo = copyNo;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTinhTrangMuon() {
        return tinhTrangMuon;
    }

    public void setTinhTrangMuon(String tinhTrangMuon) {
        this.tinhTrangMuon = tinhTrangMuon;
    }

    public String getTinhTrangVatLy() {
        return tinhTrangVatLy;
    }

    public void setTinhTrangVatLy(String tinhTrangVatLy) {
        this.tinhTrangVatLy = tinhTrangVatLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }
}
