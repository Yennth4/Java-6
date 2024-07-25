package com.yennth.demo.btvn.B4_NhanVien_Security.repository;

import com.yennth.demo.btvn.B4_NhanVien_Security.entity.B4_NhanVien;
import com.yennth.demo.btvn.B4_NhanVien_Security.response.B4_NhanVienResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface B4_INhanVien extends JpaRepository<B4_NhanVien, Integer> {

    @Query(value = """
            SELECT new com.yennth.demo.btvn.B4_NhanVien_Security.response.B4_NhanVienResponse(nv.ma, nv.ten, nv.gioiTinh, nv.sdt, cv.ten)
            FROM B4_NhanVien nv JOIN B4_ChucVu cv ON nv.chucVu.id = cv.id 
            """)
    List<B4_NhanVienResponse> listNhanVienResponse();

    // phan-trang
    @Query(value = """
            SELECT new com.yennth.demo.btvn.B4_NhanVien_Security.response.B4_NhanVienResponse(nv.ma, nv.ten, nv.gioiTinh, nv.sdt, cv.ten)
            FROM B4_NhanVien nv JOIN B4_ChucVu cv ON nv.chucVu.id = cv.id 
            """)
    List<B4_NhanVienResponse> listNhanVienResponse(Pageable pageable);
}
