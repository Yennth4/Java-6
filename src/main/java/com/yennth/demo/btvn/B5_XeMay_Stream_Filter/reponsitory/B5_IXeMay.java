package com.yennth.demo.btvn.B5_XeMay_Stream_Filter.reponsitory;

import com.yennth.demo.btvn.B5_XeMay_Stream_Filter.entity.B5_XeMay;
import com.yennth.demo.btvn.B5_XeMay_Stream_Filter.response.B5_XeMayResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface B5_IXeMay extends JpaRepository<B5_XeMay, Integer> {

    @Query(value = """
            SELECT new com.yennth.demo.btvn.B5_XeMay_Stream_Filter.response.B5_XeMayResponse(xm.ma, xm.ten, xm.ngaySanXuat, xm.giaNhap, xm.giaBan, xm.soLuong, lxm.ten)
            FROM B5_XeMay xm JOIN B5_LoaiXeMay lxm ON xm.loaiXeMay.id = lxm.id
            """)
    List<B5_XeMayResponse> listXeMayResponse();

    // phan-trang
    @Query(value = """
            SELECT new com.yennth.demo.btvn.B5_XeMay_Stream_Filter.response.B5_XeMayResponse(xm.ma, xm.ten, xm.ngaySanXuat, xm.giaNhap, xm.giaBan, xm.soLuong, lxm.ten)
            FROM B5_XeMay xm JOIN B5_LoaiXeMay lxm ON xm.loaiXeMay.id = lxm.id
            """)
    List<B5_XeMayResponse> listXeMayResponse(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM B5_XeMay x WHERE x.ma = :ma")
    void deleteByMa(@Param("ma") String ma);

    @Transactional
    @Query(value = "SELECT x FROM B5_XeMay x WHERE x.ma = :ma")
    B5_XeMay findByMa(@Param("ma") String ma);
}
