package com.yennth.demo.btvn.B3_Ban_Stream_Filter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "ban")
public class B3_Ban {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "so_thich")
    private String soThich;

    @Column(name = "gioi_tinh")
    private Integer gioiTinh;

    @ManyToOne
    @JoinColumn(name = "id_moi_quan_he" , referencedColumnName = "id")
    private B3_MQH mqh;

    @Column(name = "trang_thai")
    private Integer trangThai;

}
