package com.yennth.demo.btvn.B5_XeMay_Stream_Filter.entity;

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

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "xe_may")
public class B5_XeMay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_san_xuat")
    private Date ngaySanXuat;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "gia_nhap")
    private BigDecimal giaNhap;

    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "website")
    private String website;

    @ManyToOne
    @JoinColumn(name = "id_loai_xe_may",referencedColumnName = "id")
    private B5_LoaiXeMay loaiXeMay;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
