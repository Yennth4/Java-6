package com.yennth.demo.btvn.B5_XeMay_Stream_Filter.controller;

import com.yennth.demo.btvn.B5_XeMay_Stream_Filter.entity.B5_XeMay;
import com.yennth.demo.btvn.B5_XeMay_Stream_Filter.reponsitory.B5_ILoaiXeMay;
import com.yennth.demo.btvn.B5_XeMay_Stream_Filter.reponsitory.B5_IXeMay;
import com.yennth.demo.btvn.B5_XeMay_Stream_Filter.request.B5_XeMayRequest;
import com.yennth.demo.btvn.B5_XeMay_Stream_Filter.response.B5_XeMayResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
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
import java.util.regex.Pattern;

@RestController
@RequestMapping("/de5/xe-may/")
public class B5_XeMayController {

    @Autowired
    public B5_IXeMay xeMayService;

    @Autowired
    public B5_ILoaiXeMay loaiXeMayService;

    @GetMapping("hien-thi")
    public List<B5_XeMayResponse> getAll() {
        return xeMayService.listXeMayResponse();
    }

    @GetMapping("phan-trang")
    public List<B5_XeMayResponse> phanTrang(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return xeMayService.listXeMayResponse(pageable);
    }

    @GetMapping("detail/{id}")
    public B5_XeMay detail(@PathVariable Integer id) {
        return xeMayService.findById(id).get();
    }

    @DeleteMapping("delete/{ma}")
    public String delete(@PathVariable String ma) {
        xeMayService.deleteByMa(ma);
        return "Xoa thanh cong ma " + ma;
    }

    @PostMapping("add")
    public String add(@RequestBody @Valid B5_XeMayRequest xeMayRequest, BindingResult result) {
        if (result.hasErrors()){
            return "Error: " + result.getFieldError().getDefaultMessage();
        }

        String ma = xeMayRequest.getMa();
        // Mã đúng định dạng ví dụ: XM001
        if (!Pattern.matches("^XM\\d{3}$", ma)) {
            return "Add error: Ma khong dung dinh dang";
        }

        // Mã đã tồn tại
        B5_XeMay exisMa = xeMayService.findByMa(ma);
        if (exisMa != null && !exisMa.getId().equals(xeMayRequest.getId())) {
            return "Add thất bại: Mã đã tồn tại";
        }

        B5_XeMay xeMay = new B5_XeMay();
        xeMay.setMa(xeMayRequest.getMa());
        xeMay.setTen(xeMayRequest.getTen());
        xeMay.setNgaySanXuat(xeMayRequest.getNgaySanXuat());
        xeMay.setMoTa(xeMayRequest.getMoTa());
        xeMay.setGiaNhap(xeMayRequest.getGiaNhap());
        xeMay.setGiaBan(xeMayRequest.getGiaBan());
        xeMay.setSoLuong(xeMayRequest.getSoLuong());
        xeMay.setTrangThai(xeMayRequest.getSoLuong());
        xeMay.setLoaiXeMay(loaiXeMayService.findById(xeMayRequest.getIdLoaiXeMay()).get());

        xeMayService.save(xeMay);
        return "Them thanh cong id " + xeMay.getId();
    }

    @PutMapping("update")
    public B5_XeMay update(@RequestBody B5_XeMay xeMay) {
        return xeMayService.save(xeMay);
    }

    /** Sử dụng Stream để xử lý danh sách Xe máy và thực hiện các tác vụ sau:
         * Lọc ra danh sách Xe máy có tên loại xe máy là “Xe tay ga"
         * Sắp xếp danh sách Xe máy có  giá  bán giảm dần
         * Lọc ra danh sách Xe máy có tên chứa chữ “SH" và có số lượng 100 - 200
     */

    @GetMapping("loc-ten-loai")
    public List<B5_XeMayResponse> locTenLoaiXe() {
        return xeMayService.listXeMayResponse().stream()
                .filter(item -> item.getTenLoaiXeMay().equalsIgnoreCase("Xe tay ga"))
                .toList();
    }

    @GetMapping("sap-xep")
    public List<B5_XeMayResponse> sapXep() {
        return xeMayService.listXeMayResponse().stream()
                .sorted(Comparator.comparing(B5_XeMayResponse::getGiaBan).reversed())
                .toList();
    }

    @GetMapping("loc-ten-and-so-luong")
    public List<B5_XeMayResponse> locTenAndSoLuong() {
        return xeMayService.listXeMayResponse().stream()
                .filter(item -> item.getTenLoaiXeMay().equalsIgnoreCase("SH"))
                .filter(item -> item.getSoLuong() > 100 && item.getSoLuong() < 200)
                .toList();
    }


}
