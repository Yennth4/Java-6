package com.yennth.demo.btvn.B4_NhanVien_Security.request;

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
public class B4_NhanVienRequest {

    private Integer id;

    private String ma;

    private String ten;

    private String tenDem;

    private String ho;

    private String gioiTinh;

    private Date ngaySinh;

    private String diaChi;

    private String sdt;

    private String matKhau;

    private Integer idChucVu;

    private Integer trangThai;
}
