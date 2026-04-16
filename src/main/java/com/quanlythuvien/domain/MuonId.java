package com.quanlythuvien.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MuonId implements Serializable {

    private String maThanhVien;
    private String maSach;
    private Integer copyNo;
    private LocalDate ngayMuon;

    public MuonId() {
    }

    public MuonId(String maThanhVien, String maSach, Integer copyNo, LocalDate ngayMuon) {
        this.maThanhVien = maThanhVien;
        this.maSach = maSach;
        this.copyNo = copyNo;
        this.ngayMuon = ngayMuon;
    }

    public String getMaThanhVien() {
        return maThanhVien;
    }

    public void setMaThanhVien(String maThanhVien) {
        this.maThanhVien = maThanhVien;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MuonId muonId)) {
            return false;
        }
        return Objects.equals(maThanhVien, muonId.maThanhVien)
                && Objects.equals(maSach, muonId.maSach)
                && Objects.equals(copyNo, muonId.copyNo)
                && Objects.equals(ngayMuon, muonId.ngayMuon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maThanhVien, maSach, copyNo, ngayMuon);
    }
}
