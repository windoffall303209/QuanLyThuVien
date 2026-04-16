package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "CungCap")
@IdClass(CungCapId.class)
public class CungCap {

    @Id
    @Column(name = "ma_ncc", length = 20)
    private String maNcc;

    @Id
    @Column(name = "ma_chi_nhanh", length = 20)
    private String maChiNhanh;

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }
}
