package com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.service;

import com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.entity.NhanVien;
import com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.response.NhanVienRespose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface INhanVien extends JpaRepository<NhanVien, Integer> {

    /*
    // C1 : native query SQL
    @Query(value = """
            SELECT nv.ma, nv.ten, gioi_tinh, sdt, cv.ten as ten_chuc_vu
            FROM nhan_vien nv JOIN chuc_vu cv ON nv.id_chuc_vu = cv.id
            """, nativeQuery = true
    )
    List<NhanVienRespose> listNhanVienResponse();
     */

    // C2 : Custom model
    @Query(value = """
            SELECT new com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.response.NhanVienRespose(nv.ma,nv.ten,nv.gioiTinh,cv.ten) 
            FROM NhanVien nv JOIN ChucVu cv ON nv.chucVu.id = cv.id
            """)
    List<NhanVienRespose> listNhanVienResponse();


//    Phan trang
    @Query(value = """
            SELECT new com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.response.NhanVienRespose(nv.ma,nv.ten,nv.gioiTinh,cv.ten) 
            FROM NhanVien nv JOIN ChucVu cv ON nv.chucVu.id = cv.id
            """)
    Page<NhanVienRespose> listNhanVienResponse(Pageable pageable);

}
