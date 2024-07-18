package com.yennth.demo.btvn.B2_SanPham_LoaiSP.controller;

import com.yennth.demo.btvn.B2_SanPham_LoaiSP.entity.SanPham;
import com.yennth.demo.btvn.B2_SanPham_LoaiSP.repository.ILoaiSanPham;
import com.yennth.demo.btvn.B2_SanPham_LoaiSP.repository.ISanPham;
import com.yennth.demo.btvn.B2_SanPham_LoaiSP.request.SanPhamRequest;
import com.yennth.demo.btvn.B2_SanPham_LoaiSP.response.SanPhamResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/de2/san-pham/")
public class B2_SanPhamController {

    @Autowired
    private ISanPham sanPhamService;

    @Autowired
    private ILoaiSanPham loaiSanPhamService;

    @GetMapping("hien-thi")
    private List<SanPhamResponse> getAll() {
        return sanPhamService.listSanPhamResponse();
    }

    @GetMapping("phan-trang")
    private List<SanPhamResponse> phanTrang(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return sanPhamService.listSanPhamResponse(pageable);
    }

    @GetMapping("detail")
    private SanPham detail(@RequestParam Integer id) {
        return sanPhamService.findById(id).get();
    }

    @DeleteMapping("delete")
    private String delete(@RequestParam Integer id) {
        sanPhamService.deleteById(id);
        return "Delete success id " + id;
    }

    /**
     * Validate
     * Check trống các trường khi add
     * Check trùng mã khi add
     * Check mã đúng định dạng ví dụ: SP001
     */
    @PostMapping("add")
    private String add(@RequestBody @Valid SanPhamRequest sanPhamRequest, BindingResult result) {

        if (result.hasErrors()) {
            return "Add thất bại: " + result.getFieldError().getDefaultMessage();
        }

        String ma = sanPhamRequest.getMa();
        // Kiểm tra mã đúng định dạng SP001
        if (!Pattern.matches("^SP\\d{3}$", ma)) {
            return "Add thất bại: Mã không đúng định dạng SP001";
        }

        // Kiểm tra mã đã tồn tại hay chưa
        SanPham exisSanPham = sanPhamService.findByMa(ma);
        if (exisSanPham != null && !exisSanPham.getId().equals(sanPhamRequest.getId())) {
            return "Add thất bại: Mã đã tồn tại";
        }

        SanPham sanPham = new SanPham();
        sanPham.setMa(ma);
        sanPham.setTen(sanPhamRequest.getTen());
        sanPham.setNgaySanXuat(sanPhamRequest.getNgaySanXuat());
        sanPham.setWebsite(sanPhamRequest.getWebsite());
        sanPham.setMoTa(sanPhamRequest.getMoTa());
        sanPham.setGiaBan(sanPhamRequest.getGiaBan());
        sanPham.setSoLuong(sanPhamRequest.getSoLuong());
        sanPham.setTrangThai(sanPhamRequest.getTrangThai());
        sanPham.setLoaiSanPham(loaiSanPhamService.findById(sanPhamRequest.getIdLoaiSanPham()).get());

        sanPhamService.save(sanPham);
        return "Add thành công";
    }

    @PutMapping("update")
    private SanPham update(@RequestBody SanPham sanPham) {
        return sanPhamService.save(sanPham);
    }

}
