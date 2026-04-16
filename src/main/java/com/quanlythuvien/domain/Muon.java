package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Muon")
@IdClass(MuonId.class)
public class Muon {

    @Id
    @Column(name = "ma_thanh_vien", length = 20)
    private String maThanhVien;

    @Column(name = "ma_thu_thu", length = 20)
    private String maThuThu;

    @Id
    @Column(name = "ma_sach", length = 20)
    private String maSach;

    @Id
    @Column(name = "copy_no")
    private Integer copyNo;

    @Id
    @Column(name = "ngay_muon")
    private LocalDate ngayMuon;

    @Column(name = "ngay_hen_tra")
    private LocalDate ngayHenTra;

    @Column(name = "ngay_tra")
    private LocalDate ngayTra;

    @Column(name = "trang_thai", length = 50)
    private String trangThai;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    public String getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(String maThanhVien) {
        this.maThanhVien = maThanhVien;
    }

    public String getMaThuThu() {
        return maThuThu;
    }

    public void setMaThuThu(String maThuThu) {
        this.maThuThu = maThuThu;
    }

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

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public LocalDate getNgayHenTra() {
        return ngayHenTra;
    }

    public void setNgayHenTra(LocalDate ngayHenTra) {
        this.ngayHenTra = ngayHenTra;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
