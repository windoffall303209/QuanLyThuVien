create database QuanLyThuVien;
Go

use QuanLyThuVien;
Go

CREATE TABLE Sach (
    ma_sach NVARCHAR(20) PRIMARY KEY,
    ten_sach NVARCHAR(255),
    tac_gia NVARCHAR(255),
    nha_xb NVARCHAR(255),
    nam_xb INT,
    so_trang INT,
    gia_tri DECIMAL(18,2)
);
GO

CREATE TABLE GiaoTrinh (
    ma_sach NVARCHAR(20) PRIMARY KEY,
    cap_hoc NVARCHAR(50),
    mon_hoc NVARCHAR(100),
    FOREIGN KEY (ma_sach) REFERENCES Sach(ma_sach)
);
GO

CREATE TABLE TieuThuyet (
    ma_sach NVARCHAR(20) PRIMARY KEY,
    the_loai NVARCHAR(100),
    FOREIGN KEY (ma_sach) REFERENCES Sach(ma_sach)
);
GO

CREATE TABLE TapChi (
    ma_sach NVARCHAR(20) PRIMARY KEY,
    so_phat_hanh INT,
    thang_phat_hanh INT,
    FOREIGN KEY (ma_sach) REFERENCES Sach(ma_sach)
);
GO

CREATE TABLE ChiNhanh (
    ma_chi_nhanh NVARCHAR(20) PRIMARY KEY,
    ten NVARCHAR(255),
    dia_chi NVARCHAR(255),
    so_dien_thoai NVARCHAR(20)
);
GO

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
GO

CREATE TABLE ThanhVien (
    ma_thanh_vien NVARCHAR(20) PRIMARY KEY,
    ho_ten NVARCHAR(255),
    sdt NVARCHAR(20),
    dia_chi NVARCHAR(255),
    ngay_dang_ky DATE,
    tinh_trang_the NVARCHAR(100),
    ghi_chu NVARCHAR(255)
);
GO

CREATE TABLE ThuThu (
    ma_thu_thu NVARCHAR(20) PRIMARY KEY,
    ho_ten NVARCHAR(255),
    sdt NVARCHAR(20),
    email NVARCHAR(100),
    ma_chi_nhanh NVARCHAR(20),

    FOREIGN KEY (ma_chi_nhanh) REFERENCES ChiNhanh(ma_chi_nhanh)
);
GO

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
GO

CREATE TABLE CaLam (
    ma_ca_lam NVARCHAR(20) PRIMARY KEY,
    thoi_gian_bat_dau TIME,
    thoi_gian_ket_thuc TIME
);
GO

CREATE TABLE ChamCong (
    ma_ca_lam NVARCHAR(20),
    ma_thu_thu NVARCHAR(20),
    ngay_lam_viec DATE,

    PRIMARY KEY (ma_ca_lam, ma_thu_thu, ngay_lam_viec),

    FOREIGN KEY (ma_ca_lam) REFERENCES CaLam(ma_ca_lam),
    FOREIGN KEY (ma_thu_thu) REFERENCES ThuThu(ma_thu_thu)
);
GO

CREATE TABLE NhaCungCap (
    ma_ncc NVARCHAR(20) PRIMARY KEY,
    ten_ncc NVARCHAR(255),
    sdt NVARCHAR(20),
    email NVARCHAR(100),
    dia_chi NVARCHAR(255)
);
GO

CREATE TABLE CungCap (
    ma_ncc NVARCHAR(20),
    ma_chi_nhanh NVARCHAR(20),

    PRIMARY KEY (ma_ncc, ma_chi_nhanh),

    FOREIGN KEY (ma_ncc) REFERENCES NhaCungCap(ma_ncc),
    FOREIGN KEY (ma_chi_nhanh) REFERENCES ChiNhanh(ma_chi_nhanh)
);
GO

DELETE FROM CungCap;
DELETE FROM NhaCungCap;
DELETE FROM ChamCong;
DELETE FROM CaLam;
DELETE FROM Muon;
DELETE FROM ThuThu;
DELETE FROM ThanhVien;
DELETE FROM BanSao;
DELETE FROM TapChi;
DELETE FROM TieuThuyet;
DELETE FROM GiaoTrinh;
DELETE FROM Sach;
DELETE FROM ChiNhanh;

SELECT * FROM ChiNhanh;

INSERT INTO ChiNhanh (ma_chi_nhanh, ten, dia_chi, so_dien_thoai)
VALUES
(N'CN01', N'Chi nhánh 1', N'Địa chỉ 1', N'0900000001'),
(N'CN02', N'Chi nhánh 2', N'Địa chỉ 2', N'0900000002'),
(N'CN03', N'Chi nhánh 3', N'Địa chỉ 3', N'0900000003'),
(N'CN04', N'Chi nhánh 4', N'Địa chỉ 4', N'0900000004'),
(N'CN05', N'Chi nhánh 5', N'Địa chỉ 5', N'0900000005'),
(N'CN06', N'Chi nhánh 6', N'Địa chỉ 6', N'0900000006'),
(N'CN07', N'Chi nhánh 7', N'Địa chỉ 7', N'0900000007'),
(N'CN08', N'Chi nhánh 8', N'Địa chỉ 8', N'0900000008'),
(N'CN09', N'Chi nhánh 9', N'Địa chỉ 9', N'0900000009'),
(N'CN10', N'Chi nhánh 10', N'Địa chỉ 10', N'0900000010');
GO


INSERT INTO NhaCungCap (ma_ncc, ten_ncc, sdt, email, dia_chi)
VALUES
(N'NCC01', N'Nhà cung cấp 1', N'0911111111', N'ncc1@mail.com', N'Địa chỉ NCC1'),
(N'NCC02', N'Nhà cung cấp 2', N'0911111112', N'ncc2@mail.com', N'Địa chỉ NCC2'),
(N'NCC03', N'Nhà cung cấp 3', N'0911111113', N'ncc3@mail.com', N'Địa chỉ NCC3'),
(N'NCC04', N'Nhà cung cấp 4', N'0911111114', N'ncc4@mail.com', N'Địa chỉ NCC4'),
(N'NCC05', N'Nhà cung cấp 5', N'0911111115', N'ncc5@mail.com', N'Địa chỉ NCC5'),
(N'NCC06', N'Nhà cung cấp 6', N'0911111116', N'ncc6@mail.com', N'Địa chỉ NCC6'),
(N'NCC07', N'Nhà cung cấp 7', N'0911111117', N'ncc7@mail.com', N'Địa chỉ NCC7'),
(N'NCC08', N'Nhà cung cấp 8', N'0911111118', N'ncc8@mail.com', N'Địa chỉ NCC8'),
(N'NCC09', N'Nhà cung cấp 9', N'0911111119', N'ncc9@mail.com', N'Địa chỉ NCC9'),
(N'NCC10', N'Nhà cung cấp 10', N'0911111120', N'ncc10@mail.com', N'Địa chỉ NCC10');
GO


INSERT INTO CaLam (ma_ca_lam, thoi_gian_bat_dau, thoi_gian_ket_thuc)
VALUES
(N'CA01', '07:00', '11:00'),
(N'CA02', '11:00', '15:00'),
(N'CA03', '15:00', '19:00'),
(N'CA04', '19:00', '23:00'),
(N'CA05', '08:00', '12:00'),
(N'CA06', '12:00', '16:00'),
(N'CA07', '16:00', '20:00'),
(N'CA08', '07:30', '11:30'),
(N'CA09', '13:00', '17:00'),
(N'CA10', '17:00', '21:00');
GO


INSERT INTO Sach (ma_sach, ten_sach, tac_gia, nha_xb, nam_xb, so_trang, gia_tri)
VALUES
(N'S001', N'Sách 1', N'Tác giả 1', N'NXB A', 2020, 150, 50000),
(N'S002', N'Sách 2', N'Tác giả 2', N'NXB B', 2019, 200, 75000),
(N'S003', N'Sách 3', N'Tác giả 3', N'NXB C', 2018, 300, 90000),
(N'S004', N'Sách 4', N'Tác giả 4', N'NXB D', 2021, 250, 60000),
(N'S005', N'Sách 5', N'Tác giả 5', N'NXB A', 2017, 180, 45000),
(N'S006', N'Sách 6', N'Tác giả 6', N'NXB B', 2022, 270, 85000),
(N'S007', N'Sách 7', N'Tác giả 7', N'NXB C', 2016, 320, 95000),
(N'S008', N'Sách 8', N'Tác giả 8', N'NXB D', 2020, 210, 52000),
(N'S009', N'Sách 9', N'Tác giả 9', N'NXB A', 2015, 290, 88000),
(N'S010', N'Sách 10', N'Tác giả 10', N'NXB B', 2023, 310, 99000);
GO


INSERT INTO GiaoTrinh (ma_sach, cap_hoc, mon_hoc)
VALUES
(N'S001', N'Đại học', N'Toán cao cấp'),
(N'S002', N'Đại học', N'Vật lý đại cương'),
(N'S003', N'Cao đẳng', N'Tin học cơ bản'),
(N'S004', N'Đại học', N'Triết học'),
(N'S005', N'Cao đẳng', N'Giải tích'),
(N'S006', N'Đại học', N'Xác suất thống kê'),
(N'S007', N'Đại học', N'Lập trình'),
(N'S008', N'Cao đẳng', N'Mạng máy tính'),
(N'S009', N'Đại học', N'Hóa đại cương'),
(N'S010', N'Đại học', N'Kinh tế vi mô');
GO


INSERT INTO TieuThuyet (ma_sach, the_loai)
VALUES
(N'S001', N'Phiêu lưu'),
(N'S002', N'Tình cảm'),
(N'S003', N'Trinh thám'),
(N'S004', N'Kinh dị'),
(N'S005', N'Hành động'),
(N'S006', N'Lịch sử'),
(N'S007', N'Viễn tưởng'),
(N'S008', N'Tâm lý'),
(N'S009', N'Thiếu nhi'),
(N'S010', N'Hài hước');
GO


INSERT INTO TapChi (ma_sach, so_phat_hanh, thang_phat_hanh)
VALUES
('S001', 1, 1),
('S002', 2, 2),
('S003', 3, 3),
('S004', 4, 4),
('S005', 5, 5),
('S006', 6, 6),
('S007', 7, 7),
('S008', 8, 8),
('S009', 9, 9),
('S010', 10, 10);
GO


INSERT INTO CungCap (ma_ncc, ma_chi_nhanh)
VALUES
('NCC01','CN01'),
('NCC02','CN02'),
('NCC03','CN03'),
('NCC04','CN04'),
('NCC05','CN05'),
('NCC06','CN06'),
('NCC07','CN07'),
('NCC08','CN08'),
('NCC09','CN09'),
('NCC10','CN10');
GO


INSERT INTO BanSao (ma_sach, copy_no, ngay_nhap, tinh_trang_muon, tinh_trang_vat_ly, ghi_chu, ma_chi_nhanh)
VALUES
('S001', 1, '2023-01-01', N'Chưa mượn', N'Tốt', NULL, 'CN01'),
('S002', 1, '2023-01-02', N'Đang mượn', N'Tốt', NULL, 'CN02'),
('S003', 1, '2023-01-03', N'Chưa mượn', N'Trung bình', NULL, 'CN03'),
('S004', 1, '2023-01-04', N'Chưa mượn', N'Tốt', NULL, 'CN04'),
('S005', 1, '2023-01-05', N'Đang mượn', N'Xấu', NULL, 'CN05'),
('S006', 1, '2023-01-06', N'Chưa mượn', N'Tốt', NULL, 'CN06'),
('S007', 1, '2023-01-07', N'Chưa mượn', N'Tốt', NULL, 'CN07'),
('S008', 1, '2023-01-08', N'Đang mượn', N'Tốt', NULL, 'CN08'),
('S009', 1, '2023-01-09', N'Chưa mượn', N'Tốt', NULL, 'CN09'),
('S010', 1, '2023-01-10', N'Chưa mượn', N'Tốt', NULL, 'CN10');
GO




INSERT INTO ThanhVien (ma_thanh_vien, ho_ten, sdt, dia_chi, ngay_dang_ky, tinh_trang_the, ghi_chu)
VALUES
('TV01', N'Nguyễn Văn A', '0901111111', N'HN', '2023-01-01', N'Hoạt động', NULL),
('TV02', N'Trần Thị B', '0902222222', N'HCM', '2023-01-02', N'Hoạt động', NULL),
('TV03', N'Lê Văn C', '0903333333', N'DN', '2023-01-03', N'Khóa', NULL),
('TV04', N'Phạm D', '0904444444', N'Huế', '2023-01-04', N'Hoạt động', NULL),
('TV05', N'Hoàng E', '0905555555', N'HN', '2023-01-05', N'Hoạt động', NULL),
('TV06', N'Nguyễn F', '0906666666', N'HCM', '2023-01-06', N'Khóa', NULL),
('TV07', N'Trần G', '0907777777', N'DN', '2023-01-07', N'Hoạt động', NULL),
('TV08', N'Lê H', '0908888888', N'Huế', '2023-01-08', N'Hoạt động', NULL),
('TV09', N'Phạm I', '0909999999', N'HN', '2023-01-09', N'Hoạt động', NULL),
('TV10', N'Hoàng J', '0910000000', N'HCM', '2023-01-10', N'Khóa', NULL);
GO

INSERT INTO ThuThu (ma_thu_thu, ho_ten, sdt, email, ma_chi_nhanh)
VALUES
('TT01', N'Thủ thư 1', '0901111111', 'tt1@mail.com', 'CN01'),
('TT02', N'Thủ thư 2', '0902222222', 'tt2@mail.com', 'CN02'),
('TT03', N'Thủ thư 3', '0903333333', 'tt3@mail.com', 'CN03'),
('TT04', N'Thủ thư 4', '0904444444', 'tt4@mail.com', 'CN04'),
('TT05', N'Thủ thư 5', '0905555555', 'tt5@mail.com', 'CN05'),
('TT06', N'Thủ thư 6', '0906666666', 'tt6@mail.com', 'CN06'),
('TT07', N'Thủ thư 7', '0907777777', 'tt7@mail.com', 'CN07'),
('TT08', N'Thủ thư 8', '0908888888', 'tt8@mail.com', 'CN08'),
('TT09', N'Thủ thư 9', '0909999999', 'tt9@mail.com', 'CN09'),
('TT10', N'Thủ thư 10', '0910000000', 'tt10@mail.com', 'CN10');
GO



INSERT INTO Muon (ma_thanh_vien, ma_thu_thu, ma_sach, copy_no, ngay_muon, ngay_hen_tra, ngay_tra, trang_thai, ghi_chu)
VALUES
('TV01','TT01','S001',1,'2023-02-01','2023-02-10',NULL,N'Đang mượn',NULL),
('TV02','TT02','S002',1,'2023-02-02','2023-02-12',NULL,N'Đang mượn',NULL),
('TV03','TT03','S003',1,'2023-02-03','2023-02-13','2023-02-12',N'Đã trả',NULL),
('TV04','TT04','S004',1,'2023-02-04','2023-02-14',NULL,N'Đang mượn',NULL),
('TV05','TT05','S005',1,'2023-02-05','2023-02-15',NULL,N'Đang mượn',NULL),
('TV06','TT06','S006',1,'2023-02-06','2023-02-16','2023-02-15',N'Đã trả',NULL),
('TV07','TT07','S007',1,'2023-02-07','2023-02-17',NULL,N'Đang mượn',NULL),
('TV08','TT08','S008',1,'2023-02-08','2023-02-18',NULL,N'Đang mượn',NULL),
('TV09','TT09','S009',1,'2023-02-09','2023-02-19','2023-02-18',N'Đã trả',NULL),
('TV10','TT10','S010',1,'2023-02-10','2023-02-20',NULL,N'Đang mượn',NULL);
GO


INSERT INTO ChamCong (ma_ca_lam, ma_thu_thu, ngay_lam_viec)
VALUES
('CA01','TT01','2023-03-01'),
('CA02','TT02','2023-03-02'),
('CA03','TT03','2023-03-03'),
('CA04','TT04','2023-03-04'),
('CA05','TT05','2023-03-05'),
('CA06','TT06','2023-03-06'),
('CA07','TT07','2023-03-07'),
('CA08','TT08','2023-03-08'),
('CA09','TT09','2023-03-09'),
('CA10','TT10','2023-03-10');
GO


SELECT 
    m.ma_sach,
    s.ten_sach,
    m.copy_no,
    m.ngay_muon,
    m.ngay_hen_tra,
    m.ngay_tra,
    tv.ho_ten AS ten_thanh_vien,
    tt.ho_ten AS ten_thu_thu
FROM Muon m
JOIN Sach s ON m.ma_sach = s.ma_sach
JOIN ThanhVien tv ON m.ma_thanh_vien = tv.ma_thanh_vien
JOIN ThuThu tt ON m.ma_thu_thu = tt.ma_thu_thu;

SELECT 
    m.ma_sach,
    s.ten_sach,
    tv.ho_ten AS thanh_vien,
    m.ngay_hen_tra,
    DATEDIFF(day, m.ngay_hen_tra, GETDATE()) AS so_ngay_qua_han
FROM Muon m
JOIN Sach s ON m.ma_sach = s.ma_sach
JOIN ThanhVien tv ON m.ma_thanh_vien = tv.ma_thanh_vien
WHERE m.trang_thai = N'Đang mượn' AND m.ngay_hen_tra < GETDATE();

SELECT 
    tt.ma_thu_thu,
    tt.ho_ten,
    COUNT(cc.ma_ca_lam) AS so_ca_lam
FROM ChamCong cc
JOIN ThuThu tt ON cc.ma_thu_thu = tt.ma_thu_thu
WHERE MONTH(cc.ngay_lam_viec) = 3 AND YEAR(cc.ngay_lam_viec) = 2023
GROUP BY tt.ma_thu_thu, tt.ho_ten;

SELECT 
    gt.mon_hoc AS loai_sach,
    COUNT(s.ma_sach) AS so_luong
FROM Sach s
JOIN GiaoTrinh gt ON s.ma_sach = gt.ma_sach
GROUP BY gt.mon_hoc
UNION
SELECT 
    tt.the_loai,
    COUNT(s2.ma_sach)
FROM Sach s2
JOIN TieuThuyet tt ON s2.ma_sach = tt.ma_sach
GROUP BY tt.the_loai;

SELECT 
    tv.ma_thanh_vien,
    tv.ho_ten,
    COUNT(*) AS so_lan_muon
FROM Muon m
JOIN ThanhVien tv ON m.ma_thanh_vien = tv.ma_thanh_vien
GROUP BY tv.ma_thanh_vien, tv.ho_ten
ORDER BY so_lan_muon DESC;

SELECT tv.ma_thanh_vien, tv.ho_ten, m.ma_sach, m.ngay_muon, m.ngay_hen_tra
FROM Muon m
JOIN ThanhVien tv ON m.ma_thanh_vien = tv.ma_thanh_vien
WHERE m.ngay_hen_tra < GETDATE()
  AND m.ngay_tra IS NULL;

SELECT cn.ma_chi_nhanh, cn.ten, COUNT(s.ma_sach) AS tong_sach
FROM ChiNhanh cn
LEFT JOIN BanSao bs ON cn.ma_chi_nhanh = bs.ma_chi_nhanh
LEFT JOIN Sach s ON s.ma_sach = bs.ma_sach
GROUP BY cn.ma_chi_nhanh, cn.ten
ORDER BY tong_sach DESC;

SELECT ncc.ma_ncc, ncc.ten_ncc, COUNT(*) AS so_chi_nhanh
FROM CungCap cc
JOIN NhaCungCap ncc ON cc.ma_ncc = ncc.ma_ncc
GROUP BY ncc.ma_ncc, ncc.ten_ncc
ORDER BY so_chi_nhanh DESC;

SELECT TOP 1 tv.ma_thanh_vien, tv.ho_ten, COUNT(*) AS so_sach_muon
FROM Muon m
JOIN ThanhVien tv ON m.ma_thanh_vien = tv.ma_thanh_vien
GROUP BY tv.ma_thanh_vien, tv.ho_ten
ORDER BY so_sach_muon DESC;

SELECT bs.ma_sach, bs.copy_no, bs.tinh_trang_vat_ly
FROM BanSao bs
WHERE bs.tinh_trang_vat_ly LIKE N'%xấu%' 
   OR bs.tinh_trang_vat_ly LIKE N'%hỏng%';
