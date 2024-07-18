package com.yennth.demo.demo.B3_LocSapxep_Stream.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SachRespose {

    private String ma;

    private String ten;

    private Date ngayXuatBan;

    private Integer soTrang;

    private Double donGia;

    private String tenNhaXuatBan;
}
