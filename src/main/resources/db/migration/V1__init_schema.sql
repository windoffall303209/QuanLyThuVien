CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password_hash NVARCHAR(255) NOT NULL,
    full_name NVARCHAR(100) NOT NULL,
    role NVARCHAR(20) NOT NULL,
    enabled BIT NOT NULL DEFAULT 1,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL UNIQUE,
    description NVARCHAR(255) NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE books (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    isbn NVARCHAR(20) NOT NULL UNIQUE,
    title NVARCHAR(200) NOT NULL,
    author NVARCHAR(150) NOT NULL,
    publisher NVARCHAR(150) NULL,
    published_year INT NULL,
    total_copies INT NOT NULL,
    available_copies INT NOT NULL,
    active BIT NOT NULL DEFAULT 1,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_books_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT chk_books_copies CHECK (total_copies >= 0 AND available_copies >= 0 AND available_copies <= total_copies)
);

CREATE TABLE readers (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    reader_code NVARCHAR(30) NOT NULL UNIQUE,
    full_name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NULL,
    phone NVARCHAR(20) NULL,
    address NVARCHAR(255) NULL,
    active BIT NOT NULL DEFAULT 1,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE borrow_records (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    reader_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE NULL,
    status NVARCHAR(20) NOT NULL,
    notes NVARCHAR(255) NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_borrow_reader FOREIGN KEY (reader_id) REFERENCES readers(id),
    CONSTRAINT fk_borrow_book FOREIGN KEY (book_id) REFERENCES books(id),
    CONSTRAINT chk_borrow_dates CHECK (due_date >= borrow_date),
    CONSTRAINT chk_borrow_return CHECK (return_date IS NULL OR return_date >= borrow_date)
);

CREATE INDEX idx_books_title ON books(title);
CREATE INDEX idx_books_author ON books(author);
CREATE INDEX idx_readers_name ON readers(full_name);
CREATE INDEX idx_borrow_status_due ON borrow_records(status, due_date);
CREATE INDEX idx_borrow_reader ON borrow_records(reader_id);
CREATE INDEX idx_borrow_book ON borrow_records(book_id);
