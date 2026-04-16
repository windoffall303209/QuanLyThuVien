package com.quanlythuvien.service;

import com.quanlythuvien.domain.Muon;
import com.quanlythuvien.domain.MuonId;
import com.quanlythuvien.web.form.MuonForm;
import java.util.List;

public interface MuonService {

    List<Muon> findDangMuon();

    List<Muon> findTatCa();

    List<Muon> findQuaHan();

    List<Muon> findGanDay();

    List<Muon> findByThanhVien(String maThanhVien);

    MuonForm buildDefaultForm();

    Muon create(MuonForm form);

    void traSach(MuonId id);
}
