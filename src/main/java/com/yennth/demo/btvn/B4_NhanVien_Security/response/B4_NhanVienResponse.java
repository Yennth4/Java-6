package com.yennth.demo.btvn.B4_NhanVien_Security.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class B4_NhanVienResponse {

    private String ma;

    private String ten;

    private String gioiTinh;

    private String sdt;

    private String tenChucVu;
}
