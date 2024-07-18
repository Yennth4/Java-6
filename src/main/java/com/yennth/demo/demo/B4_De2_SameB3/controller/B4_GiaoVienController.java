package com.yennth.demo.demo.B4_De2_SameB3.controller;

import com.yennth.demo.demo.B4_De2_SameB3.entity.GiaoVien;
import com.yennth.demo.demo.B4_De2_SameB3.request.GiaoVienRequest;
import com.yennth.demo.demo.B4_De2_SameB3.response.GiaoVienResponse;
import com.yennth.demo.demo.B4_De2_SameB3.service.IGiaoVien;
import com.yennth.demo.demo.B4_De2_SameB3.service.ILop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/B4/giao-vien/")
public class B4_GiaoVienController {

    @Autowired
    private IGiaoVien giaoVienService;

    @Autowired
    private ILop lopService;

    @GetMapping("hien-thi")
    public List<GiaoVienResponse> getAll() {
        return giaoVienService.listGiaoVienRespose();
    }

    @GetMapping("phan-trang")
    public List<GiaoVienResponse> phanTrang(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return giaoVienService.listGiaoVienRespose(pageable);
    }

    @PostMapping("add")
    public GiaoVien add(@RequestBody GiaoVien giaoVien) {
        return giaoVienService.save(giaoVien);
    }

//    UpdateRequest
    @PutMapping("update")
    public String update(@RequestBody GiaoVienRequest giaoVienRequest) {

        GiaoVien giaoVien = new GiaoVien();
        giaoVien.setId(giaoVienRequest.getId());
        giaoVien.setMa(giaoVienRequest.getMa());
        giaoVien.setTen(giaoVienRequest.getTen());
        giaoVien.setDiaChi(giaoVienRequest.getDiaChi());
        giaoVien.setNgaySinh(giaoVienRequest.getNgaySinh());
        giaoVien.setSdt(giaoVienRequest.getSdt());
        giaoVien.setEmail(giaoVienRequest.getEmail());
        giaoVien.setSoNamCongTac(giaoVienRequest.getSoNamCongTac());
        giaoVien.setGioiTinh(giaoVienRequest.getGioiTinh());
        giaoVien.setLop(lopService.findById(giaoVienRequest.getIdLop()).get());

        giaoVienService.save(giaoVien);
        return "Update thanh cong";
    }

    @GetMapping("detail")
    public GiaoVien detail(@RequestParam Integer id) {
        return giaoVienService.findById(id).get();
    }

    @DeleteMapping("delete/{ma}")
    public String delete(@PathVariable String ma) {
        giaoVienService.deleteByMa(ma);
        return "Xoa thanh cong ma " + ma;
    }

    /**
     * Sử dụng Stream để xử lý danh sách Giao Vien và thực hiện các tác vụ sau:
     * Lọc ra danh sách Giáo viên có tên lớp bắt đầu bằng “SD”
     * Sắp xếp danh sách Giao viên có năm công tác giảm dần
     * Lọc ra danh sách Giao viên có tên lớp chứa chữ “IT" và  có số năm công tác trong khoảng 10 - 20 năm
     */


    @GetMapping("loc-ten")
    public List<GiaoVienResponse> listTen() {
        return giaoVienService.listGiaoVienRespose().stream()
                .filter(item -> item.getTenLop().equalsIgnoreCase("SD"))
                .toList();
    }

    @GetMapping("sap-xep")
    public List<GiaoVienResponse> listSapXepGiamDan() {
        return giaoVienService.listGiaoVienRespose().stream()
                .sorted(Comparator.comparing(GiaoVienResponse::getSoNamCongTac).reversed())
                .toList();
    }

    @GetMapping("loc-ten-va-nam-cong-tac")
    public List<GiaoVienResponse> listTenAndNamCongTac() {
        return giaoVienService.listGiaoVienRespose().stream()
                .filter(item -> item.getTen().equalsIgnoreCase("IT"))
                .filter(item -> item.getSoNamCongTac()>=10 && item.getSoNamCongTac()<=20)
                .toList();
    }

}
