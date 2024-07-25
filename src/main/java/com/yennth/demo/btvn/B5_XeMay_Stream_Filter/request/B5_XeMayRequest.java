package com.yennth.demo.btvn.B5_XeMay_Stream_Filter.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class B5_XeMayRequest {

    private Integer id;

    @NotBlank(message = "Dien ma")
    private String ma;

    @NotBlank(message = "Dien ten")
    private String ten;

    @NotNull(message = "Dien ngay san xuat")
    private Date ngaySanXuat;

    @NotBlank(message = "Dien mo ta")
    private String moTa;

    @NotNull(message = "Dien gia nhap")
    private BigDecimal giaNhap;

    @NotNull(message = "Dien gia ban")
    private BigDecimal giaBan;

    @NotNull(message = "Dien so luong")
    @Min(value = 0, message = "So luong phai lon hon 0")
    private Integer soLuong;

    @NotBlank(message = "Dien website")
    private String website;

    @NotNull(message = "Dien id loai xe may")
    @Min(value = 0, message = "Khong ton tai")
    private Integer idLoaiXeMay;

    @NotNull(message = "Dien trang thai")
    private Integer trangThai;
}
