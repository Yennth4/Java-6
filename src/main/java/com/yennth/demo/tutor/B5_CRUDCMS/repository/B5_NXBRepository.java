package com.yennth.demo.tutor.B5_CRUDCMS.repository;

import com.yennth.demo.tutor.B5_CRUDCMS.entity.B5_NXB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface B5_NXBRepository extends JpaRepository<B5_NXB, Integer> {

}
