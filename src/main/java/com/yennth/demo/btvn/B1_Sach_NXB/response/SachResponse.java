package com.yennth.demo.btvn.B1_Sach_NXB.response;

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
public class SachResponse {

    private String ma;

    private String ten;

    private Date ngayXuatBan;

    private Integer soTrang;

    private Double donGia;

    private String tenNhaXuatBan;

}

