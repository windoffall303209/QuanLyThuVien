package com.quanlythuvien.domain;

import java.io.Serializable;
import java.util.Objects;

public class BanSaoId implements Serializable {

    private String maSach;
    private Integer copyNo;

    public BanSaoId() {
    }

    public BanSaoId(String maSach, Integer copyNo) {
        this.maSach = maSach;
        this.copyNo = copyNo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BanSaoId banSaoId)) {
            return false;
        }
        return Objects.equals(maSach, banSaoId.maSach) && Objects.equals(copyNo, banSaoId.copyNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSach, copyNo);
    }
}
