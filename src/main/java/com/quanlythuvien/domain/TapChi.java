package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TapChi")
public class TapChi {

    @Id
    @Column(name = "ma_sach", length = 20)
    private String maSach;

    @Column(name = "so_phat_hanh")
    private Integer soPhatHanh;

    @Column(name = "thang_phat_hanh")
    private Integer thangPhatHanh;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Integer getSoPhatHanh() {
        return soPhatHanh;
    }

    public void setSoPhatHanh(Integer soPhatHanh) {
        this.soPhatHanh = soPhatHanh;
    }

    public Integer getThangPhatHanh() {
        return thangPhatHanh;
    }

    public void setThangPhatHanh(Integer thangPhatHanh) {
        this.thangPhatHanh = thangPhatHanh;
    }
}
