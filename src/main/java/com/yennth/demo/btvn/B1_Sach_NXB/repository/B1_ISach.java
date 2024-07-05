package com.yennth.demo.btvn.B1_Sach_NXB.repository;

import com.yennth.demo.btvn.B1_Sach_NXB.entity.B1_Sach;
import com.yennth.demo.btvn.B1_Sach_NXB.response.SachResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface B1_ISach extends JpaRepository<B1_Sach, Integer> {

    @Query(value = """
            SELECT new com.yennth.demo.btvn.B1_Sach_NXB.response.SachResponse(s.ma, s.ten, s.ngayXuatBan, s.soTrang, s.donGia, nxb.ten)
            FROM B1_Sach s JOIN NhaXuatBan nxb ON s.nhaXuatBan.id = nxb.id
            """)
    List<SachResponse> listSachResponse();

    //    Phan trang
    @Query(value = """
            SELECT new com.yennth.demo.btvn.B1_Sach_NXB.response.SachResponse(s.ma, s.ten, s.ngayXuatBan, s.soTrang, s.donGia, nxb.ten)
            FROM B1_Sach s JOIN NhaXuatBan nxb ON s.nhaXuatBan.id = nxb.id
            """)
    List<SachResponse> listSachResponse(Pageable page);

    //    Xoa theo ma
    @Transactional
    @Modifying
    @Query(value = """
            DELETE FROM B1_Sach WHERE ma = :ma
            """)
    void deleteByMa(@Param("ma") String ma);

    @Transactional
    @Query(value = """
            SELECT s FROM B1_Sach s WHERE s.ma = :ma
            """)
    B1_Sach findByMa(@Param("ma") String ma);
}
