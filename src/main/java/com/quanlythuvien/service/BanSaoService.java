package com.quanlythuvien.service;

import com.quanlythuvien.domain.BanSao;
import java.util.List;

public interface BanSaoService {

    List<BanSao> findAll();

    List<BanSao> findSanSangMuon();
}
