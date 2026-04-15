package com.quanlythuvien.web.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.quanlythuvien.config.CustomUserDetailsService;
import com.quanlythuvien.config.SecurityConfig;
import com.quanlythuvien.domain.BorrowRecord;
import com.quanlythuvien.service.BorrowService;
import com.quanlythuvien.service.DashboardService;
import com.quanlythuvien.service.dto.DashboardStats;
import com.quanlythuvien.web.form.BorrowForm;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {AuthController.class, DashboardController.class})
@Import({SecurityConfig.class, AuthControllerSecurityTest.TestConfig.class})
class AuthControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUpSecurityMock() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        when(customUserDetailsService.loadUserByUsername(anyString()))
                .thenAnswer(invocation -> User.withUsername(invocation.getArgument(0, String.class))
                        .password(encoder.encode("password"))
                        .roles("ADMIN")
                        .build());
    }

    @Test
    void shouldRedirectAnonymousUserToLogin() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void shouldAllowAuthenticatedUserToOpenDashboard() throws Exception {
        mockMvc.perform(get("/dashboard").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        DashboardService dashboardService() {
            return () -> new DashboardStats(0, 0, 0, 0, 0);
        }

        @Bean
        BorrowService borrowService() {
            return new BorrowService() {
                @Override
                public List<BorrowRecord> findAll() {
                    return List.of();
                }

                @Override
                public List<BorrowRecord> findActiveBorrows() {
                    return List.of();
                }

                @Override
                public List<BorrowRecord> findOverdueBorrows() {
                    return List.of();
                }

                @Override
                public List<BorrowRecord> findRecentBorrows() {
                    return List.of();
                }

                @Override
                public List<BorrowRecord> findActiveBorrowsByReader(Long readerId) {
                    return List.of();
                }

                @Override
                public List<BorrowRecord> findReturnedBorrowsByReader(Long readerId) {
                    return List.of();
                }

                @Override
                public BorrowRecord findById(Long id) {
                    return null;
                }

                @Override
                public BorrowForm buildDefaultForm() {
                    return new BorrowForm();
                }

                @Override
                public BorrowRecord create(BorrowForm form) {
                    return null;
                }

                @Override
                public void returnBook(Long id) {
                }

                @Override
                public void refreshOverdueStatus() {
                }
            };
        }
    }
}
