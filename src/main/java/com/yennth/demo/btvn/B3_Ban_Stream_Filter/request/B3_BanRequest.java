package com.yennth.demo.btvn.B3_Ban_Stream_Filter.request;

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

    private String ma;

    private String ten;

    private Date ngaySinh;

    private String soThich;

    private Integer gioiTinh;

    private Integer idMQH;

    private Integer trangThai;
}
