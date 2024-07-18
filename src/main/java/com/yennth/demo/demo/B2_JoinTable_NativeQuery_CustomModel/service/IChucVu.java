package com.yennth.demo.demo.B2_JoinTable_NativeQuery_CustomModel.service;

import com.yennth.demo.demo.B2_JoinTable_NativeQuery_CustomModel.entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChucVu extends JpaRepository<ChucVu, Integer> {
}
