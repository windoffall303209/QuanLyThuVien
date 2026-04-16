package com.quanlythuvien.web.form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class SachForm {

    @NotBlank(message = "Mã sách không được để trống")
    @Size(max = 20, message = "Mã sách tối đa 20 ký tự")
    private String maSach;

    @NotBlank(message = "Tên sách không được để trống")
    @Size(max = 255, message = "Tên sách tối đa 255 ký tự")
    private String tenSach;

    @NotBlank(message = "Tác giả không được để trống")
    @Size(max = 255, message = "Tác giả tối đa 255 ký tự")
    private String tacGia;

    @Size(max = 255, message = "Nhà xuất bản tối đa 255 ký tự")
    private String nhaXb;

    @Min(value = 0, message = "Năm xuất bản không hợp lệ")
    private Integer namXb;

    @Min(value = 1, message = "Số trang phải lớn hơn 0")
    private Integer soTrang;

    @DecimalMin(value = "0", inclusive = false, message = "Giá trị phải lớn hơn 0")
    private BigDecimal giaTri;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXb() {
        return nhaXb;
    }

    public void setNhaXb(String nhaXb) {
        this.nhaXb = nhaXb;
    }

    public Integer getNamXb() {
        return namXb;
    }

    public void setNamXb(Integer namXb) {
        this.namXb = namXb;
    }

    public Integer getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(Integer soTrang) {
        this.soTrang = soTrang;
    }

    public BigDecimal getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(BigDecimal giaTri) {
        this.giaTri = giaTri;
    }
}
