package com.yennth.demo.btvn.B2_SanPham_LoaiSP.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class SanPhamRequest {

    private Integer id;

    @NotBlank(message = "Ma null")
    private String ma;

    @NotBlank(message = "Ten null")
    private String ten;

    @NotNull(message = "Ngay san xuat null")
    private Date ngaySanXuat;

    @NotBlank(message = "Website null")
    private String website;

    @NotBlank(message = "Mo ta null")
    private String moTa;

    @NotNull(message = "Gia ban null")
    private BigDecimal giaBan;

    @NotNull(message = "So luong null")
    @Min(value = 0)
    private Integer soLuong;

    @NotNull(message = "Trang thai null")
    private Integer trangThai;

    @NotNull(message = "Id loai san pham null")
    @Min(value = 0)
    private Integer idLoaiSanPham;
}
