package com.yennth.demo.btvn.B2_SanPham_LoaiSP.repository;

import com.yennth.demo.btvn.B2_SanPham_LoaiSP.entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoaiSanPham extends JpaRepository<LoaiSanPham, Integer> {
}
