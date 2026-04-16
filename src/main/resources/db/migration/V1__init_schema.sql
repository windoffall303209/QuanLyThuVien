CREATE TABLE Sach (
    ma_sach NVARCHAR(20) PRIMARY KEY,
    ten_sach NVARCHAR(255),
    tac_gia NVARCHAR(255),
    nha_xb NVARCHAR(255),
    nam_xb INT,
    so_trang INT,
    gia_tri DECIMAL(18,2)
);

CREATE TABLE GiaoTrinh (
    ma_sach NVARCHAR(20) PRIMARY KEY,
    cap_hoc NVARCHAR(50),
    mon_hoc NVARCHAR(100),
    FOREIGN KEY (ma_sach) REFERENCES Sach(ma_sach)
);

CREATE TABLE TieuThuyet (
    ma_sach NVARCHAR(20) PRIMARY KEY,
    the_loai NVARCHAR(100),
    FOREIGN KEY (ma_sach) REFERENCES Sach(ma_sach)
);

CREATE TABLE TapChi (
    ma_sach NVARCHAR(20) PRIMARY KEY,
    so_phat_hanh INT,
    thang_phat_hanh INT,
    FOREIGN KEY (ma_sach) REFERENCES Sach(ma_sach)
);

CREATE TABLE ChiNhanh (
    ma_chi_nhanh NVARCHAR(20) PRIMARY KEY,
    ten NVARCHAR(255),
    dia_chi NVARCHAR(255),
    so_dien_thoai NVARCHAR(20)
);

CREATE TABLE BanSao (
    ma_sach NVARCHAR(20),
    copy_no INT,
    ngay_nhap DATE,
    tinh_trang_muon NVARCHAR(100),
    tinh_trang_vat_ly NVARCHAR(100),
    ghi_chu NVARCHAR(255),
    ma_chi_nhanh NVARCHAR(20),
    PRIMARY KEY (ma_sach, copy_no),
    FOREIGN KEY (ma_sach) REFERENCES Sach(ma_sach),
    FOREIGN KEY (ma_chi_nhanh) REFERENCES ChiNhanh(ma_chi_nhanh)
);

CREATE TABLE ThanhVien (
    ma_thanh_vien NVARCHAR(20) PRIMARY KEY,
    ho_ten NVARCHAR(255),
    sdt NVARCHAR(20),
    dia_chi NVARCHAR(255),
    ngay_dang_ky DATE,
    tinh_trang_the NVARCHAR(100),
    ghi_chu NVARCHAR(255)
);

CREATE TABLE ThuThu (
    ma_thu_thu NVARCHAR(20) PRIMARY KEY,
    ho_ten NVARCHAR(255),
    sdt NVARCHAR(20),
    email NVARCHAR(100),
    ma_chi_nhanh NVARCHAR(20),
    FOREIGN KEY (ma_chi_nhanh) REFERENCES ChiNhanh(ma_chi_nhanh)
);

CREATE TABLE Muon (
    ma_thanh_vien NVARCHAR(20),
    ma_thu_thu NVARCHAR(20),
    ma_sach NVARCHAR(20),
    copy_no INT,
    ngay_muon DATE,
    ngay_hen_tra DATE,
    ngay_tra DATE,
    trang_thai NVARCHAR(50),
    ghi_chu NVARCHAR(255),
    PRIMARY KEY (ma_thanh_vien, ma_sach, copy_no, ngay_muon),
    FOREIGN KEY (ma_thanh_vien) REFERENCES ThanhVien(ma_thanh_vien),
    FOREIGN KEY (ma_thu_thu) REFERENCES ThuThu(ma_thu_thu),
    FOREIGN KEY (ma_sach, copy_no) REFERENCES BanSao(ma_sach, copy_no)
);

CREATE TABLE CaLam (
    ma_ca_lam NVARCHAR(20) PRIMARY KEY,
    thoi_gian_bat_dau TIME,
    thoi_gian_ket_thuc TIME
);

CREATE TABLE ChamCong (
    ma_ca_lam NVARCHAR(20),
    ma_thu_thu NVARCHAR(20),
    ngay_lam_viec DATE,
    PRIMARY KEY (ma_ca_lam, ma_thu_thu, ngay_lam_viec),
    FOREIGN KEY (ma_ca_lam) REFERENCES CaLam(ma_ca_lam),
    FOREIGN KEY (ma_thu_thu) REFERENCES ThuThu(ma_thu_thu)
);

CREATE TABLE NhaCungCap (
    ma_ncc NVARCHAR(20) PRIMARY KEY,
    ten_ncc NVARCHAR(255),
    sdt NVARCHAR(20),
    email NVARCHAR(100),
    dia_chi NVARCHAR(255)
);

CREATE TABLE CungCap (
    ma_ncc NVARCHAR(20),
    ma_chi_nhanh NVARCHAR(20),
    PRIMARY KEY (ma_ncc, ma_chi_nhanh),
    FOREIGN KEY (ma_ncc) REFERENCES NhaCungCap(ma_ncc),
    FOREIGN KEY (ma_chi_nhanh) REFERENCES ChiNhanh(ma_chi_nhanh)
);
