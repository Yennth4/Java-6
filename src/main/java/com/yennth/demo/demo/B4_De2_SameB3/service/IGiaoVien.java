package com.yennth.demo.demo.B4_De2_SameB3.service;

import com.yennth.demo.demo.B4_De2_SameB3.entity.GiaoVien;
import com.yennth.demo.demo.B4_De2_SameB3.response.GiaoVienResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IGiaoVien extends JpaRepository<GiaoVien, Integer> {

    @Query(value = """
            SELECT new com.yennth.demo.demo.B4_De2_SameB3.response.GiaoVienResponse(gv.ma, gv.ten, gv.ngaySinh, gv.sdt, gv.email, gv.soNamCongTac, l.ten)
            FROM GiaoVien gv JOIN Lop l ON gv.lop.id = l.id
            """)
    List<GiaoVienResponse> listGiaoVienRespose();

    // Phan trang
    @Query(value = """
            SELECT new com.yennth.demo.demo.B4_De2_SameB3.response.GiaoVienResponse(gv.ma, gv.ten, gv.ngaySinh, gv.sdt, gv.email, gv.soNamCongTac, l.ten)
            FROM GiaoVien gv JOIN Lop l ON gv.lop.id = l.id
            """)
    List<GiaoVienResponse> listGiaoVienRespose(Pageable page);

//    Xoa theo ma
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM GiaoVien WHERE ma = :ma")
    void deleteByMa(@Param("ma") String ma);

}
