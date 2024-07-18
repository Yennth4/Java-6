package com.yennth.demo.demo.B1_CRUD.controller;

import com.yennth.demo.demo.B1_CRUD.entity.MayTinh;
import com.yennth.demo.demo.B1_CRUD.service.IMayTinh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/may-tinh/")
public class MayTinhController {

    @Autowired
    private IMayTinh mayTinhService;

//    Viết API hiển thị  danh sách các máy tính sử dụng method GET
    @GetMapping("hien-thi")
    public List<MayTinh> getAll() {
        return mayTinhService.findAll();
    }

//    Viết API Thêm 1 đối tượng máy tính sử dụng method POST
    @PostMapping("add")
    public MayTinh addMayTinh(@RequestBody MayTinh mayTinh) {
        return mayTinhService.save(mayTinh);
    }

//    Viết API Update 1 đối tượng máy tính sử dụng method PUT
    @PutMapping("update")
    public String updateMayTinh(@RequestBody MayTinh mayTinh) {
        mayTinhService.save(mayTinh);
        return "Update thanh cong";
    }

//    Viết API xóa 1 đối tượng máy tính sử dụng method DELETE
    @DeleteMapping("delete")
    public void deleteMayTinh(@RequestParam Integer id) {
        mayTinhService.deleteById(id);
    }

//    Viết API Detail 1 đối tượng máy tính sử dụng method GET
    @GetMapping("detail/{id}")
    public MayTinh detail(@PathVariable Integer id) {
        return mayTinhService.findById(id).get();
    }
}
