package com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienRespose {

    private String maNV;

    private String tenNV;

    private String gioiTinh;

    private String tenChucVu;
}
