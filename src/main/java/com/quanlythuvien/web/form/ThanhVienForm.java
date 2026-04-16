package com.quanlythuvien.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class ThanhVienForm {

    @NotBlank(message = "Mã thành viên không được để trống")
    @Size(max = 20, message = "Mã thành viên tối đa 20 ký tự")
    private String maThanhVien;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 255, message = "Họ tên tối đa 255 ký tự")
    private String hoTen;

    @Size(max = 20, message = "Số điện thoại tối đa 20 ký tự")
    private String sdt;

    @Size(max = 255, message = "Địa chỉ tối đa 255 ký tự")
    private String diaChi;

    @NotNull(message = "Ngày đăng ký không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayDangKy;

    @NotBlank(message = "Tình trạng thẻ không được để trống")
    @Size(max = 100, message = "Tình trạng thẻ tối đa 100 ký tự")
    private String tinhTrangThe;

    @Size(max = 255, message = "Ghi chú tối đa 255 ký tự")
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
