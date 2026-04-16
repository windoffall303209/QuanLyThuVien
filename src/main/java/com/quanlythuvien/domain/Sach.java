package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Sach")
public class Sach {

    @Id
    @Column(name = "ma_sach", length = 20)
    private String maSach;

    @Column(name = "ten_sach", length = 255)
    private String tenSach;

    @Column(name = "tac_gia", length = 255)
    private String tacGia;

    @Column(name = "nha_xb", length = 255)
    private String nhaXb;

    @Column(name = "nam_xb")
    private Integer namXb;

    @Column(name = "so_trang")
    private Integer soTrang;

    @Column(name = "gia_tri", precision = 18, scale = 2)
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
