package com.quanlythuvien.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class MuonForm {

    @NotBlank(message = "Thành viên không được để trống")
    private String maThanhVien;

    @NotBlank(message = "Thủ thư không được để trống")
    private String maThuThu;

    @NotBlank(message = "Sách không được để trống")
    private String maSach;

    @NotNull(message = "Số bản sao không được để trống")
    private Integer copyNo;

    @NotNull(message = "Ngày mượn không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayMuon;

    @NotNull(message = "Ngày hẹn trả không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayHenTra;

    @Size(max = 255, message = "Ghi chú tối đa 255 ký tự")
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
