package com.yennth.demo.tutor.B3_LocSapxep_Stream.request;

import com.yennth.demo.tutor.B3_LocSapxep_Stream.entity.NhaXuatBan;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class SachRequest {

    @NotBlank(message = "Dien ma")
    private String ma;

    @NotBlank(message = "Dien ten")
    private String ten;

    @NotNull(message = "Dien ngayXuatBan")
    private Date ngayXuatBan;

    @NotNull(message = "Dien soTrang")
    private Integer soTrang;

    @NotNull(message = "Dien donGia")
    private Double donGia;

    @NotNull(message = "Dien idNXB")
    private Integer idNXB;

    @NotNull(message = "Dien trangThai")
    private Integer trangThai;
}
