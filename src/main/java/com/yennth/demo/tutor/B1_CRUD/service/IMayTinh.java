package com.yennth.demo.tutor.B1_CRUD.service;

import com.yennth.demo.tutor.B1_CRUD.entity.MayTinh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMayTinh extends JpaRepository<MayTinh , Integer> {
}
