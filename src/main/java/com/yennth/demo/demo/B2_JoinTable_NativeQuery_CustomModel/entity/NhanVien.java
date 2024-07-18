package com.yennth.demo.demo.B2_JoinTable_NativeQuery_CustomModel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "nhan_vien")
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Ma kh de trong")
    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Ten kh de trong")
    @Column(name = "ten")
    private String ten;

    @NotBlank(message = "Ten dem kh de trong")
    @Column(name = "ten_dem")
    private String tenDem;

    @NotBlank(message = "Ho kh de trong")
    @Column(name = "ho")
    private String ho;

    @NotBlank(message = "Gioi tinh kh de trong")
    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @NotNull(message = "Ngay sinh kh de trong")
    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @NotBlank(message = "Dia chi kh de trong")
    @Column(name = "dia_chi")
    private String diaChi;

    @NotBlank(message = "SDT kh de trong")
    @Column(name = "sdt")
    private String sdt;

    @NotBlank(message = "Mat khau kh de trong")
    @Column(name = "mau_khau")
    private String matKhau;

    @NotNull(message = "Chuc vu kh de trong")
    @ManyToOne
    @JoinColumn(name = "id_chuc_vu", referencedColumnName = "id")
    private ChucVu chucVu;

    @NotNull(message = "Trang thai kh de trong")
    @Column(name = "trang_thai")
    private Integer trangThai;

}
