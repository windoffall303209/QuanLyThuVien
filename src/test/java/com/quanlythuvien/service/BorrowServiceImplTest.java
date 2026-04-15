package com.quanlythuvien.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.quanlythuvien.config.properties.AppProperties;
import com.quanlythuvien.domain.Book;
import com.quanlythuvien.domain.BorrowRecord;
import com.quanlythuvien.domain.BorrowStatus;
import com.quanlythuvien.domain.Category;
import com.quanlythuvien.domain.Reader;
import com.quanlythuvien.exception.BusinessException;
import com.quanlythuvien.repository.BookRepository;
import com.quanlythuvien.repository.BorrowRecordRepository;
import com.quanlythuvien.repository.ReaderRepository;
import com.quanlythuvien.service.impl.BorrowServiceImpl;
import com.quanlythuvien.web.form.BorrowForm;
import java.lang.reflect.Field;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowServiceImplTest {

    @Mock
    private BorrowRecordRepository borrowRecordRepository;

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private BookRepository bookRepository;

    private BorrowService borrowService;

    @BeforeEach
    void setUp() {
        borrowService = new BorrowServiceImpl(borrowRecordRepository, readerRepository, bookRepository, new AppProperties(14));
    }

    @Test
    void shouldDecreaseAvailableCopiesWhenBorrowingBook() {
        Reader reader = activeReader();
        Book book = availableBook();
        BorrowForm form = borrowForm();

        when(readerRepository.findById(1L)).thenReturn(java.util.Optional.of(reader));
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
        when(borrowRecordRepository.save(any(BorrowRecord.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BorrowRecord record = borrowService.create(form);

        assertThat(book.getAvailableCopies()).isEqualTo(2);
        assertThat(record.getStatus()).isEqualTo(BorrowStatus.BORROWED);
    }

    @Test
    void shouldRejectBorrowWhenNoAvailableCopies() {
        Reader reader = activeReader();
        Book book = availableBook();
        book.setAvailableCopies(0);
        BorrowForm form = borrowForm();

        when(readerRepository.findById(1L)).thenReturn(java.util.Optional.of(reader));
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));

        assertThatThrownBy(() -> borrowService.create(form))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Sách đã hết lượt mượn");
    }

    @Test
    void shouldBuildDefaultBorrowDates() {
        BorrowForm form = borrowService.buildDefaultForm();

        assertThat(form.getBorrowDate()).isEqualTo(LocalDate.now());
        assertThat(form.getDueDate()).isEqualTo(LocalDate.now().plusDays(14));
    }

    private BorrowForm borrowForm() {
        BorrowForm form = new BorrowForm();
        form.setReaderId(1L);
        form.setCategoryId(10L);
        form.setBookId(1L);
        form.setBorrowDate(LocalDate.now());
        form.setDueDate(LocalDate.now().plusDays(7));
        return form;
    }

    private Reader activeReader() {
        Reader reader = new Reader();
        reader.setReaderCode("DG100");
        reader.setFullName("Nguyễn Văn Test");
        reader.setActive(true);
        return reader;
    }

    private Book availableBook() {
        Category category = new Category();
        category.setName("Công nghệ");
        setId(category, 10L);

        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Test Book");
        book.setAuthor("Author");
        book.setCategory(category);
        book.setActive(true);
        book.setTotalCopies(3);
        book.setAvailableCopies(3);
        return book;
    }

    private void setId(Object target, Long id) {
        try {
            Field field = target.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(target, id);
        } catch (ReflectiveOperationException ex) {
            throw new AssertionError(ex);
        }
    }
}
