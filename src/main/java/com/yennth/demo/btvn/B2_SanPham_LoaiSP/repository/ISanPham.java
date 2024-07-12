package com.yennth.demo.btvn.B2_SanPham_LoaiSP.repository;

import com.yennth.demo.btvn.B2_SanPham_LoaiSP.entity.SanPham;
import com.yennth.demo.btvn.B2_SanPham_LoaiSP.response.SanPhamResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISanPham extends JpaRepository<SanPham, Integer> {

    @Query(value = """
            SELECT new com.yennth.demo.btvn.B2_SanPham_LoaiSP.response.SanPhamResponse(sp.ma, sp.ten, sp.ngaySanXuat, sp.giaBan, sp.soLuong, lsp.ten)
            FROM SanPham sp JOIN LoaiSanPham lsp ON sp.loaiSanPham.id = lsp.id
            """)
    List<SanPhamResponse> listSanPhamResponse();

    // Phan trang
    @Query(value = """
            SELECT new com.yennth.demo.btvn.B2_SanPham_LoaiSP.response.SanPhamResponse(sp.ma, sp.ten, sp.ngaySanXuat, sp.giaBan, sp.soLuong, lsp.ten)
            FROM SanPham sp JOIN LoaiSanPham lsp ON sp.loaiSanPham.id = lsp.id
            """)
    List<SanPhamResponse> listSanPhamResponse(Pageable page);

    @Transactional
    @Query(value = """
            SELECT s FROM SanPham s WHERE s.ma = :ma
            """)
    SanPham findByMa(@Param("ma") String ma);
}
