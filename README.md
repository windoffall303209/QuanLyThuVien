# QuanLyThuVien

Ứng dụng quản lý thư viện xây dựng bằng Spring Boot, Thymeleaf, Spring Security, Flyway và SQL Server.

## Công nghệ sử dụng
- Java 21
- Spring Boot 3.3.5
- Spring MVC + Thymeleaf
- Spring Data JPA
- Spring Security
- Flyway
- SQL Server
- Maven

## Yêu cầu cài đặt
Trước khi chạy project, máy cần có:
- JDK 21
- Maven 3.9+ (hoặc dùng Maven trong IDE)
- SQL Server đang chạy và cho phép kết nối TCP/IP
- Cổng mặc định ứng dụng: `8080`
- Cổng mặc định SQL Server theo cấu hình hiện tại: `1433`

## Link cài đặt môi trường cần thiết
- JDK 21 (Oracle): https://www.oracle.com/java/technologies/downloads/
- JDK 21 (Eclipse Temurin): https://adoptium.net/temurin/releases/
- Maven: https://maven.apache.org/download.cgi
- SQL Server Developer / Express: https://www.microsoft.com/en-us/sql-server/sql-server-downloads
- SQL Server Management Studio (SSMS): https://learn.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms
- Hướng dẫn bật TCP/IP cho SQL Server: https://learn.microsoft.com/en-us/sql/database-engine/configure-windows/enable-or-disable-a-server-network-protocol

## Cấu hình database mặc định
File cấu hình: [src/main/resources/application.yml](src/main/resources/application.yml)

```yml
spring:
  datasource:
    url: jdbc:sqlserver://127.0.0.1:1433;databaseName=QuanLyThuVien;encrypt=true;trustServerCertificate=true
    username: qltv_user
    password: 123456Abc@
```

Bạn có thể override bằng biến môi trường:
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

## Cách chạy project
Từ thư mục gốc project, chạy:

```bash
mvn spring-boot:run
```

Hoặc build và test:

```bash
mvn clean test
```

Sau khi chạy thành công, mở:
- `http://localhost:8080`

## Đăng nhập
Ứng dụng hiện dùng **In-Memory UserDetails** (không phụ thuộc bảng users trong DB).

Tài khoản mặc định:
- `admin / admin123`
- `librarian / lib123`

Cấu hình tại:
- [src/main/java/com/quanlythuvien/config/SecurityConfig.java](src/main/java/com/quanlythuvien/config/SecurityConfig.java)

## Database schema hiện tại
Schema hiện tại bám theo file:
- [database/schema.sql](database/schema.sql)

Flyway migration đang dùng:
- [src/main/resources/db/migration/V1__init_schema.sql](src/main/resources/db/migration/V1__init_schema.sql)
- [src/main/resources/db/migration/V2__seed_data.sql](src/main/resources/db/migration/V2__seed_data.sql)

Các bảng chính:
- `Sach`
- `GiaoTrinh`
- `TieuThuyet`
- `TapChi`
- `ChiNhanh`
- `BanSao`
- `ThanhVien`
- `ThuThu`
- `Muon`
- `CaLam`
- `ChamCong`
- `NhaCungCap`
- `CungCap`

## Route chính của web
### Auth + Dashboard
- `/login`
- `/dashboard`

### Nghiệp vụ chính
- `/sach`
- `/thanh-vien`
- `/muon-tra`
- `/muon-tra/qua-han`

### Module theo full schema
- `/chi-nhanh`
- `/ban-sao`
- `/thu-thu`
- `/ca-lam`
- `/cham-cong`
- `/nha-cung-cap`
- `/cung-cap`
- `/giao-trinh`
- `/tieu-thuyet`
- `/tap-chi`

## Chạy test
```bash
mvn test
```

## Cấu trúc chính của project
- [src/main/java/com/quanlythuvien/](src/main/java/com/quanlythuvien/) — mã nguồn Java chính
- [src/main/resources/templates/](src/main/resources/templates/) — giao diện Thymeleaf
- [src/main/resources/db/migration/](src/main/resources/db/migration/) — migration và seed dữ liệu
- [database/schema.sql](database/schema.sql) — schema nguồn tham chiếu
- [src/test/java/com/quanlythuvien/](src/test/java/com/quanlythuvien/) — test

## Ghi chú
- Ứng dụng dùng `Flyway` và `spring.jpa.hibernate.ddl-auto=validate`, nên schema DB phải khớp entity.
- Nếu lỗi kết nối DB, hãy kiểm tra SQL Server, TCP/IP, cổng `1433`, và thông tin user/password trong cấu hình.