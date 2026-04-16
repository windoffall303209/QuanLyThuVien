package com.quanlythuvien.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GiaoTrinh")
public class GiaoTrinh {

    @Id
    @Column(name = "ma_sach", length = 20)
    private String maSach;

    @Column(name = "cap_hoc", length = 50)
    private String capHoc;

    @Column(name = "mon_hoc", length = 100)
    private String monHoc;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getCapHoc() {
        return capHoc;
    }

    public void setCapHoc(String capHoc) {
        this.capHoc = capHoc;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }
}
