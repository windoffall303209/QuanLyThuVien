INSERT INTO ChiNhanh (ma_chi_nhanh, ten, dia_chi, so_dien_thoai) VALUES
('CN01', 'Chi nhánh Trung tâm', 'Hà Nội', '0900000001'),
('CN02', 'Chi nhánh Phía Nam', 'TP. Hồ Chí Minh', '0900000002');

INSERT INTO NhaCungCap (ma_ncc, ten_ncc, sdt, email, dia_chi) VALUES
('NCC01', 'Nhà cung cấp A', '0911111111', 'ncc1@mail.com', 'Hà Nội'),
('NCC02', 'Nhà cung cấp B', '0911111112', 'ncc2@mail.com', 'TP. Hồ Chí Minh');

INSERT INTO CaLam (ma_ca_lam, thoi_gian_bat_dau, thoi_gian_ket_thuc) VALUES
('CA01', '07:00:00', '11:00:00'),
('CA02', '13:00:00', '17:00:00');

INSERT INTO Sach (ma_sach, ten_sach, tac_gia, nha_xb, nam_xb, so_trang, gia_tri) VALUES
('S001', 'Cơ sở dữ liệu', 'Nguyễn Văn A', 'NXB Giáo Dục', 2022, 250, 80000),
('S002', 'Lập trình Java', 'Trần Thị B', 'NXB Khoa Học', 2023, 420, 120000),
('S003', 'Tạp chí Công nghệ số 01', 'Ban biên tập', 'NXB Trẻ', 2024, 60, 30000);

INSERT INTO GiaoTrinh (ma_sach, cap_hoc, mon_hoc) VALUES
('S001', 'Đại học', 'Cơ sở dữ liệu');

INSERT INTO TieuThuyet (ma_sach, the_loai) VALUES
('S002', 'Khoa học viễn tưởng');

INSERT INTO TapChi (ma_sach, so_phat_hanh, thang_phat_hanh) VALUES
('S003', 1, 1);

INSERT INTO BanSao (ma_sach, copy_no, ngay_nhap, tinh_trang_muon, tinh_trang_vat_ly, ghi_chu, ma_chi_nhanh) VALUES
('S001', 1, '2024-01-10', 'Chưa mượn', 'Tốt', NULL, 'CN01'),
('S002', 1, '2024-01-11', 'Đang mượn', 'Tốt', NULL, 'CN01'),
('S003', 1, '2024-01-12', 'Chưa mượn', 'Tốt', NULL, 'CN02');

INSERT INTO ThanhVien (ma_thanh_vien, ho_ten, sdt, dia_chi, ngay_dang_ky, tinh_trang_the, ghi_chu) VALUES
('TV01', 'Nguyễn Minh Anh', '0901234567', 'Hà Nội', '2024-01-05', 'Hoạt động', NULL),
('TV02', 'Trần Quang Huy', '0901234568', 'TP. Hồ Chí Minh', '2024-01-06', 'Hoạt động', NULL);

INSERT INTO ThuThu (ma_thu_thu, ho_ten, sdt, email, ma_chi_nhanh) VALUES
('TT01', 'Thủ thư Một', '0902234567', 'tt1@mail.com', 'CN01'),
('TT02', 'Thủ thư Hai', '0902234568', 'tt2@mail.com', 'CN02');

INSERT INTO CungCap (ma_ncc, ma_chi_nhanh) VALUES
('NCC01', 'CN01'),
('NCC02', 'CN02');

INSERT INTO ChamCong (ma_ca_lam, ma_thu_thu, ngay_lam_viec) VALUES
('CA01', 'TT01', '2024-03-01'),
('CA02', 'TT02', '2024-03-01');

INSERT INTO Muon (ma_thanh_vien, ma_thu_thu, ma_sach, copy_no, ngay_muon, ngay_hen_tra, ngay_tra, trang_thai, ghi_chu) VALUES
('TV01', 'TT01', 'S002', 1, '2024-03-01', '2024-03-10', NULL, 'Đang mượn', 'Mượn tại quầy chính'),
('TV02', 'TT02', 'S001', 1, '2024-02-20', '2024-03-01', '2024-02-28', 'Đã trả', 'Trả sớm');
