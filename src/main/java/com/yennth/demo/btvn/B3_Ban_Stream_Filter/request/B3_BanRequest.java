package com.yennth.demo.btvn.B3_Ban_Stream_Filter.request;

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
public class B3_BanRequest {

    private Integer id;

    @NotBlank(message = "Dien ma")
    private String ma;

    @NotBlank(message = "Dien ma")
    private String ten;

    @NotNull(message = "Dien ngay sinh")
    private Date ngaySinh;

    @NotBlank(message = "Dien ma")
    private String soThich;

    @NotNull(message = "Dien gioi tinh")
    private Integer gioiTinh;

    @NotNull(message = "Dien id moi quan he")
    private Integer idMQH;

    @NotNull(message = "Dien trang thai")
    private Integer trangThai;
}
