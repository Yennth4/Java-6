package com.yennth.demo.btvn.B1_Sach_NXB.request;

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
public class SachRequest {

    private Integer id;

    @NotBlank(message = "Ma trong")
    private String ma;

    @NotBlank(message = "Ten trong")
    private String ten;

    @NotNull(message = "Ngay xuat ban trong")
    private Date ngayXuatBan;

    @NotNull(message = "So trang trong")
    private Integer soTrang;

    @NotNull(message = "Don gia trong")
    private Double donGia;

    @NotNull(message = "Id nha xuat ban trong")
    private Integer idNhaXuatBan; // chu y thay doi du lieu truyen vao

    @NotNull(message = "Trang thai trong")
    private Integer trangThai;
}
