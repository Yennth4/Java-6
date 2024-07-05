package com.yennth.demo.btvn.B1_Sach_NXB.controller;

import com.yennth.demo.btvn.B1_Sach_NXB.entity.B1_Sach;
import com.yennth.demo.btvn.B1_Sach_NXB.repository.B1_INhaXuatBan;
import com.yennth.demo.btvn.B1_Sach_NXB.repository.B1_ISach;
import com.yennth.demo.btvn.B1_Sach_NXB.request.SachRequest;
import com.yennth.demo.btvn.B1_Sach_NXB.response.SachResponse;
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
@RequestMapping("/de1/sach/")
public class B1_SachController {

    @Autowired
    private B1_ISach sachService;

    @Autowired
    private B1_INhaXuatBan nhaXuatBanService;

    @GetMapping("hien-thi")
    private List<SachResponse> getAll() {
        return sachService.listSachResponse();
    }

    @GetMapping("phan-trang")
    private List<SachResponse> phanTrang(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return sachService.listSachResponse(pageable);
    }

    @GetMapping("detail")
    private B1_Sach detail(@RequestParam Integer id) {
        return sachService.findById(id).get();
    }

    @DeleteMapping("delete/{ma}")
    private String delete(@PathVariable String ma) {
        sachService.deleteByMa(ma);
        return "Xoa thanh cong ma " + ma;
    }

    @PostMapping("add")
    private B1_Sach add(@RequestBody B1_Sach sach) {
        return sachService.save(sach);
    }

    @PutMapping("update")
    private String update(@RequestBody @Valid SachRequest sachRequest, BindingResult result) {

        if (result.hasErrors()) {
            return "Update thất bại: " + result.getFieldError().getDefaultMessage();
        }

//      Check mã đúng định dạng ví dụ: S001
        String ma = sachRequest.getMa();
        if (!Pattern.matches("^S\\d{3}$" , ma)) {
            return "Update thất bại: Mã không đúng định dạng";
        }

//        Check trùng mã
        B1_Sach sachByMa = sachService.findByMa(ma);
        if (sachByMa != null && !sachByMa.getId().equals(sachRequest.getId())) {
            return "Update thất bại: Mã đã tồn tại";
        }

        B1_Sach sach = new B1_Sach();
        sach.setId(sachRequest.getId());
        sach.setMa(sachRequest.getMa());
        sach.setTen(sachRequest.getTen());
        sach.setNgayXuatBan(sachRequest.getNgayXuatBan());
        sach.setSoTrang(sachRequest.getSoTrang());
        sach.setDonGia(sachRequest.getDonGia());
        sach.setTrangThai(sachRequest.getTrangThai());
        sach.setNhaXuatBan(nhaXuatBanService.findById(sachRequest.getIdNhaXuatBan()).get());

        sachService.save(sach);
        return "Update thanh cong" ;
    }

    /**
     * Validate (0.5 điểm)
            Check trống các trường khi add
            check trùng mã khi add
            check mã đúng định dạng ví dụ: S001
            Sử dụng thêm request, response trong khung project (0.5 điểm)
     */

    /**
     * Viết API:
         * Sử dụng Stream để xử lý danh sách Sách và thực hiện các tác vụ sau:
         * Lọc ra danh sách Sách có tên nhà xuất bản là “Nguyễn Văn An"
         * Sắp xếp danh sách Sách có đơn giá giảm dần
         * Lọc ra danh sách Sách có tên chứa chữ “a" và so trang 150 - 200
     */


    @GetMapping("loc-ten-nxb")
    private List<SachResponse> listLocTenNhaXuatBan() {
        return sachService.listSachResponse().stream()
                .filter(item -> item.getTenNhaXuatBan().equalsIgnoreCase("Nguyễn Văn An"))
                .toList();
    }

    @GetMapping("sap-xep")
    private List<SachResponse> listSapXepGiamDan() {
        return sachService.listSachResponse().stream()
                .sorted(Comparator.comparing(SachResponse::getDonGia).reversed())
                .toList();
        // reversed() : giam dan > defaul khong ghi gi la : tang dan
    }

    @GetMapping("loc-ten")
    private List<SachResponse> listLocTenSachVaSoTrang() {
        return sachService.listSachResponse().stream()
                .filter(item -> item.getTen().equalsIgnoreCase("a"))
                .filter(item -> item.getSoTrang()>=150 && item.getSoTrang()<=200)
                .toList();
    }

}
