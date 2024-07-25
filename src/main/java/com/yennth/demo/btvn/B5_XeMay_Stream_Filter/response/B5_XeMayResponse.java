package com.yennth.demo.btvn.B5_XeMay_Stream_Filter.response;

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
public class B5_XeMayResponse {

    private String ma;

    private String ten;

    private Date ngaySanXuat;

    private BigDecimal giaNhap;

    private BigDecimal giaBan;

    private Integer soLuong;

    private String tenLoaiXeMay;
}
