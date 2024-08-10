package com.yennth.demo.demo.B5_CRUDCMS.entity;

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
public class B5_Sach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_xuat_ban")
    private Date ngayXuatBan;

    @Column(name = "so_trang")
    private Integer soTrang;

    @Column(name = "don_gia")
    private Double donGia;

    @ManyToOne
    @JoinColumn(name = "id_nha_xuat_ban", referencedColumnName = "id")
    private B5_NXB nhaXuatBan;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
