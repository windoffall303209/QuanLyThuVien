INSERT INTO categories(name, description) VALUES
(N'Văn học', N'Tiểu thuyết, truyện ngắn và tác phẩm văn học'),
(N'Công nghệ', N'Sách về lập trình và công nghệ thông tin'),
(N'Kinh tế', N'Sách quản trị, tài chính, marketing'),
(N'Kỹ năng sống', N'Rèn luyện kỹ năng và phát triển bản thân');

INSERT INTO books(isbn, title, author, publisher, published_year, total_copies, available_copies, active, category_id) VALUES
(N'9786040010011', N'Dế Mèn Phiêu Lưu Ký', N'Tô Hoài', N'Kim Đồng', 2020, 8, 6, 1, 1),
(N'9786040010012', N'Tắt Đèn', N'Ngô Tất Tố', N'Văn Học', 2019, 5, 4, 1, 1),
(N'9786040010013', N'Spring Boot Căn Bản', N'Nguyễn Văn A', N'Giáo Dục', 2024, 10, 8, 1, 2),
(N'9786040010014', N'Java Web Thực Chiến', N'Trần Minh Khang', N'Thống Kê', 2023, 7, 5, 1, 2),
(N'9786040010015', N'Nguyên Lý Kế Toán', N'Lê Thị B', N'Tài Chính', 2022, 6, 6, 1, 3),
(N'9786040010016', N'7 Thói Quen Hiệu Quả', N'Stephen Covey', N'Tổng Hợp', 2021, 9, 8, 1, 4);

INSERT INTO readers(reader_code, full_name, email, phone, address, active) VALUES
(N'DG001', N'Nguyễn Minh Anh', N'minhanh@example.com', N'0901000001', N'Hà Nội', 1),
(N'DG002', N'Trần Quang Huy', N'quanghuy@example.com', N'0901000002', N'Hải Phòng', 1),
(N'DG003', N'Lê Thu Trang', N'thutrang@example.com', N'0901000003', N'Đà Nẵng', 1),
(N'DG004', N'Phạm Gia Bảo', N'giabao@example.com', N'0901000004', N'TP. Hồ Chí Minh', 1),
(N'DG005', N'Võ Khánh Linh', N'khanhlinh@example.com', N'0901000005', N'Cần Thơ', 1);

INSERT INTO borrow_records(reader_id, book_id, borrow_date, due_date, return_date, status, notes) VALUES
(1, 1, DATEADD('DAY', -5, CURRENT_DATE), DATEADD('DAY', 9, CURRENT_DATE), NULL, 'BORROWED', N'Đang mượn bình thường'),
(2, 3, DATEADD('DAY', -20, CURRENT_DATE), DATEADD('DAY', -6, CURRENT_DATE), NULL, 'OVERDUE', N'Quá hạn cần nhắc trả'),
(3, 4, DATEADD('DAY', -12, CURRENT_DATE), DATEADD('DAY', 2, CURRENT_DATE), NULL, 'BORROWED', N'Mượn giáo trình'),
(4, 2, DATEADD('DAY', -15, CURRENT_DATE), DATEADD('DAY', -1, CURRENT_DATE), CURRENT_DATE, 'RETURNED', N'Đã trả đúng quy trình');

UPDATE books SET available_copies = total_copies - 2 WHERE id = 1;
UPDATE books SET available_copies = total_copies - 1 WHERE id = 2;
UPDATE books SET available_copies = total_copies - 1 WHERE id = 3;
UPDATE books SET available_copies = total_copies - 1 WHERE id = 4;
