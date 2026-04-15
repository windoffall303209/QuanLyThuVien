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

Gợi ý cài nhanh:
- Nếu chưa có Java, nên cài **JDK 21**.
- Nếu chưa có SQL Server, nên cài **SQL Server Developer** hoặc **SQL Server Express**.
- Nên cài thêm **SSMS** để tạo database, user và chạy script SQL dễ hơn.
- Sau khi cài SQL Server, nhớ kiểm tra đã bật **TCP/IP** và đúng cổng `1433`.

## Cấu hình database mặc định
File cấu hình: [src/main/resources/application.yml](src/main/resources/application.yml)

```yml
spring:
  datasource:
    url: jdbc:sqlserver://127.0.0.1:1433;databaseName=QuanLyThuVien;encrypt=true;trustServerCertificate=true
    username: qltv_user
    password: 123456Abc@
```

Ứng dụng mặc định sẽ kết nối tới:
- Host: `127.0.0.1`
- Port: `1433`
- Database: `QuanLyThuVien`
- Username: `qltv_user`
- Password: `123456Abc@`

Bạn có thể override bằng biến môi trường:
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Ví dụ:

```bash
export DB_URL='jdbc:sqlserver://127.0.0.1:1433;databaseName=QuanLyThuVien;encrypt=true;trustServerCertificate=true'
export DB_USERNAME='qltv_user'
export DB_PASSWORD='123456Abc@'
```

Trên Windows PowerShell:

```powershell
$env:DB_URL='jdbc:sqlserver://127.0.0.1:1433;databaseName=QuanLyThuVien;encrypt=true;trustServerCertificate=true'
$env:DB_USERNAME='qltv_user'
$env:DB_PASSWORD='123456Abc@'
```

## Tạo database và user trên SQL Server
Nếu máy chưa có database, bạn có thể tạo nhanh bằng SQL sau:

```sql
CREATE DATABASE QuanLyThuVien;
GO

USE master;
GO

IF NOT EXISTS (SELECT * FROM sys.sql_logins WHERE name = 'qltv_user')
BEGIN
    CREATE LOGIN qltv_user WITH PASSWORD = '123456Abc@';
END
GO

USE QuanLyThuVien;
GO

IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'qltv_user')
BEGIN
    CREATE USER qltv_user FOR LOGIN qltv_user;
END
GO

ALTER ROLE db_owner ADD MEMBER qltv_user;
GO
```

## Cách chạy project
Từ thư mục gốc project, chạy:

```bash
mvn spring-boot:run
```

Hoặc build file jar rồi chạy:

```bash
mvn clean package
mvn spring-boot:run
```

Sau khi chạy thành công, mở trình duyệt tại:
- `http://localhost:8080`

## Đăng nhập
Dữ liệu tài khoản ban đầu được seed bởi Flyway và `DataSeeder` khi ứng dụng chạy thành công với database.

Nếu bạn muốn kiểm tra tài khoản mặc định, hãy xem thêm tại:
- [src/main/java/com/quanlythuvien/config/DataSeeder.java](src/main/java/com/quanlythuvien/config/DataSeeder.java)
- [src/main/resources/db/migration/V2__seed_data.sql](src/main/resources/db/migration/V2__seed_data.sql)

## Dữ liệu mẫu
Khi ứng dụng khởi động thành công, Flyway sẽ tự chạy migration và seed dữ liệu mẫu gồm:
- Thể loại sách
- Danh sách sách
- Danh sách độc giả
- Một số phiếu mượn / trả mẫu

Các file migration:
- [src/main/resources/db/migration/V1__init_schema.sql](src/main/resources/db/migration/V1__init_schema.sql)
- [src/main/resources/db/migration/V2__seed_data.sql](src/main/resources/db/migration/V2__seed_data.sql)

## Chạy test
Chạy toàn bộ test:

```bash
mvn test
```

## Một số lỗi thường gặp

### 1. Không chạy được app do lỗi kết nối SQL Server
Nếu gặp lỗi kiểu:
- `The TCP/IP connection to the host 127.0.0.1, port 1433 has failed`
- `Connection refused`

Hãy kiểm tra:
- SQL Server đã chạy chưa
- Đã bật TCP/IP trong SQL Server Configuration Manager chưa
- SQL Server có lắng nghe cổng `1433` không
- Database `QuanLyThuVien` đã được tạo chưa
- Username/password trong `application.yml` hoặc biến môi trường có đúng không

### 2. Flyway lỗi khi khởi động
Nguyên nhân thường là:
- database chưa tồn tại
- user chưa có quyền
- cấu hình DB sai

### 3. Cổng 8080 bị chiếm
Có thể đổi trong [src/main/resources/application.yml](src/main/resources/application.yml):

```yml
server:
  port: 8081
```

## Cấu trúc chính của project
- [src/main/java/com/quanlythuvien/](src/main/java/com/quanlythuvien/) — mã nguồn Java chính
- [src/main/resources/templates/](src/main/resources/templates/) — giao diện Thymeleaf
- [src/main/resources/db/migration/](src/main/resources/db/migration/) — migration và seed dữ liệu
- [src/test/java/com/quanlythuvien/](src/test/java/com/quanlythuvien/) — test

## Ghi chú
- Ứng dụng dùng `Flyway` và `spring.jpa.hibernate.ddl-auto=validate`, nên cần schema đúng trước khi app hoạt động.
- Trong môi trường hiện tại, test Maven chạy được, nhưng muốn mở giao diện thật thì SQL Server local phải chạy đúng theo cấu hình.
