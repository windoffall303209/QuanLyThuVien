package com.quanlythuvien.web.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.quanlythuvien.config.SecurityConfig;
import com.quanlythuvien.service.DashboardService;
import com.quanlythuvien.service.MuonService;
import com.quanlythuvien.service.dto.DashboardStats;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {AuthController.class, DashboardController.class})
@Import({SecurityConfig.class, AuthControllerSecurityTest.TestConfig.class})
class AuthControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

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
        MuonService muonService() {
            return new MuonService() {
                @Override
                public java.util.List<com.quanlythuvien.domain.Muon> findDangMuon() {
                    return List.of();
                }

                @Override
                public java.util.List<com.quanlythuvien.domain.Muon> findTatCa() {
                    return List.of();
                }

                @Override
                public java.util.List<com.quanlythuvien.domain.Muon> findQuaHan() {
                    return List.of();
                }

                @Override
                public java.util.List<com.quanlythuvien.domain.Muon> findGanDay() {
                    return List.of();
                }

                @Override
                public java.util.List<com.quanlythuvien.domain.Muon> findByThanhVien(String maThanhVien) {
                    return List.of();
                }

                @Override
                public com.quanlythuvien.web.form.MuonForm buildDefaultForm() {
                    return new com.quanlythuvien.web.form.MuonForm();
                }

                @Override
                public com.quanlythuvien.domain.Muon create(com.quanlythuvien.web.form.MuonForm form) {
                    return null;
                }

                @Override
                public void traSach(com.quanlythuvien.domain.MuonId id) {
                }
            };
        }
    }
}
