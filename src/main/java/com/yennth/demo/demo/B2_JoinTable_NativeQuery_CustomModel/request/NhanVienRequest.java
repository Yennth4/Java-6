package com.yennth.demo.demo.B2_JoinTable_NativeQuery_CustomModel.request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class NhanVienRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Ma kh de trong")
    private String ma;

    @NotBlank(message = "Ten kh de trong")
    private String ten;

    @NotBlank(message = "Ten dem kh de trong")
    private String tenDem;

    @NotBlank(message = "Ho kh de trong")
    private String ho;

    @NotBlank(message = "Gioi tinh kh de trong")
    private String gioiTinh;

    @NotNull(message = "Ngay sinh kh de trong")
    private Date ngaySinh;

    @NotBlank(message = "Dia chi kh de trong")
    private String diaChi;

    @NotBlank(message = "SDT kh de trong")
    private String sdt;

    @NotBlank(message = "Mat khau kh de trong")
    private String matKhau;

    @NotNull(message = "Chuc vu kh de trong")
    private Integer idChucVu;

    @NotNull(message = "Trang thai kh de trong")
    private Integer trangThai;

}
