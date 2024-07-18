package com.yennth.demo.btvn.B3_Ban_Stream_Filter.repository;

import com.yennth.demo.btvn.B3_Ban_Stream_Filter.entity.B3_Ban;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.response.B3_BanResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface B3_IBan extends JpaRepository<B3_Ban, Integer> {

    @Query(value = """
            SELECT new com.yennth.demo.btvn.B3_Ban_Stream_Filter.response.B3_BanResponse(b.ma, b.ten, b.ngaySinh, b.soThich, b.gioiTinh, mqh.ten)
            FROM B3_Ban b JOIN B3_MQH mqh ON b.mqh.id = mqh.id 
            """)
    List<B3_BanResponse> listBanResponse();

    // phan-trang
    @Query(value = """
            SELECT new com.yennth.demo.btvn.B3_Ban_Stream_Filter.response.B3_BanResponse(b.ma, b.ten, b.ngaySinh, b.soThich, b.gioiTinh, mqh.ten)
            FROM B3_Ban b JOIN B3_MQH mqh ON b.mqh.id = mqh.id 
            """)
    List<B3_BanResponse> listBanResponse(Pageable page);

    // delete by ma
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM B3_Ban WHERE ma = :ma")
    void deleteByMa(@Param("ma") String ma);
}
