package com.yennth.demo.btvn.B2_SanPham_LoaiSP.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SanPhamResponse {

    private String ma;

    private String ten;

    private Date ngaySanXuat;

    private BigDecimal giaBan;

    private Integer soLuong;

    private String tenLoaiSanPham;
}
