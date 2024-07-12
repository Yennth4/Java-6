package com.yennth.demo.tutor.B5_CRUDCMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yennth.demo.tutor.B3_LocSapxep_Stream.entity.NhaXuatBan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "sach")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class B5_Sach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "NgayXuatBan")
    private Date ngayXuatBan;

    @Column(name = "SoTrang")
    private Integer soTrang;

    @Column(name = "DonGia")
    private Double donGia;

    @ManyToOne
    @JoinColumn(name = "IdNXB", referencedColumnName = "id")
    private NhaXuatBan nhaXuatBan;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public void setNhaXuatBan(B5_NXB b5Nxb) {
    }
}
