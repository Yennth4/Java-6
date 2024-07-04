package com.yennth.demo.tutor.B4_De2_SameB3.response;

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
public class GiaoVienResponse {

    private String ma;

    private String ten;

    private Date ngaySinh;

    private String sdt;

    private String email;

    private Integer soNamCongTac;

    private String tenLop;
}
