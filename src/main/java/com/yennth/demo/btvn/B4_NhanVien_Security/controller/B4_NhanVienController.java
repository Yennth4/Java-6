package com.yennth.demo.btvn.B4_NhanVien_Security.controller;

import com.yennth.demo.btvn.B4_NhanVien_Security.entity.B4_NhanVien;
import com.yennth.demo.btvn.B4_NhanVien_Security.repository.B4_IChucVu;
import com.yennth.demo.btvn.B4_NhanVien_Security.repository.B4_INhanVien;
import com.yennth.demo.btvn.B4_NhanVien_Security.response.B4_NhanVienResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/de4/nhan-vien/")
public class B4_NhanVienController {

    @Autowired
    public B4_INhanVien nhanVienService;

    @Autowired
    public B4_IChucVu chucVuService;

    @GetMapping("hien-thi")
    public List<B4_NhanVienResponse> getAll() {
        return nhanVienService.listNhanVienResponse();
    }

    @GetMapping("phan-trang")
    public List<B4_NhanVienResponse> phanTrang(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return nhanVienService.listNhanVienResponse(pageable);
    }

    @GetMapping("detail/{id}")
    public B4_NhanVien detail(@RequestParam Integer id) {
        return nhanVienService.findById(id).get();
    }

    @DeleteMapping("delete/{id}")
    public String delete(@RequestParam Integer id) {
        nhanVienService.deleteById(id);
        return "Xoa thanh cong nhan vien id " + id;
    }

    @PostMapping("add")
    public B4_NhanVien add(@RequestBody B4_NhanVien nhanVien) {
        return nhanVienService.save(nhanVien);
    }

    @PutMapping("update")
    public B4_NhanVien update(@RequestBody B4_NhanVien nhanVien) {
        return nhanVienService.save(nhanVien);
    }
}
