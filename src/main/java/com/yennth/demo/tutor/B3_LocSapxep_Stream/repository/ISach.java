package com.yennth.demo.tutor.B3_LocSapxep_Stream.repository;

import com.yennth.demo.tutor.B3_LocSapxep_Stream.entity.Sach;
import com.yennth.demo.tutor.B3_LocSapxep_Stream.response.SachRespose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISach extends JpaRepository<Sach, Integer> {

    @Query(value = """
            SELECT new com.yennth.demo.tutor.B3_LocSapxep_Stream.response.SachRespose(s.ma, s.ten, s.ngayXuatBan, s.soTrang, s.donGia, nxb.ten) 
            FROM B1_Sach s JOIN NhaXuatBan nxb ON s.nhaXuatBan.id = nxb.id
            """
    )
    public List<SachRespose> listSachRespose();

    @Query(value = """
            SELECT new com.yennth.demo.tutor.B3_LocSapxep_Stream.response.SachRespose(s.ma, s.ten, s.ngayXuatBan, s.soTrang, s.donGia, nxb.ten) 
            FROM B1_Sach s JOIN NhaXuatBan nxb ON s.nhaXuatBan.id = nxb.id
            """
    )
    public List<SachRespose> listSachRespose(Pageable page);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM B1_Sach WHERE ma = :ma ")
    void deleteByMa(@Param("ma") String ma);
}
