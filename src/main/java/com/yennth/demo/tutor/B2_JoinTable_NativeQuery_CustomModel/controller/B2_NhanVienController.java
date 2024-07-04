package com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.controller;

import com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.entity.NhanVien;
import com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.request.NhanVienRequest;
import com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.response.NhanVienRespose;
import com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.service.IChucVu;
import com.yennth.demo.tutor.B2_JoinTable_NativeQuery_CustomModel.service.INhanVien;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import java.util.List;

@RestController
@RequestMapping("/B2/nhan-vien/")
public class B2_NhanVienController {

    @Autowired
    private INhanVien nhanVienService;

    @Autowired
    private IChucVu chucVuService;

    //    Viết API hiển thị danh sách nhân viên gồm (Mã nhân viên, Tên nhân viên, Giới tính, SĐT, Tên chức vụ)  sử dụng method GET
    @GetMapping("hien-thi")
    public List<NhanVienRespose> getAll() {
        return nhanVienService.listNhanVienResponse();
    }


    //    Viết API add 1 đối tượng nhân viên vào DB sử dụng method POST
    //    Check trong
    @PostMapping("add")
    public String add(@RequestBody @Valid NhanVien nhanVien, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            return "Them that bai";
        }
        nhanVienService.save(nhanVien);
        return "Them thanh cong";
    }

    //    Viết API update 1 đối tượng nhân viên vào DB sử dụng method PUT
    @PutMapping("update")
    public String update(@RequestBody NhanVien nhanVien) {
        nhanVienService.save(nhanVien);
        return "Update thanh cong";
    }

    @GetMapping("detail") // detail?id = value
    public NhanVien detail(@RequestParam Integer id) {
        return nhanVienService.findById(id).get();
    }

    //    Viết API xóa 1 đối tượng nhân viên vào DB theo ID nhân viên sử dụng method DELETE
//    Khong su dung @PathVariable (/{id}) - thi co the lam @RequestParam nhu detail
    @DeleteMapping("delete/{id}") // delete/value
    public String delete(@PathVariable Integer id) {
        nhanVienService.deleteById(id);
        return "Xoa thanh cong id " + id;
    }

    //      Viết API hiển thị danh sách nhân viên có phân trang trên table 5 phần tử trên 1 trang sử dụng method GET
    @GetMapping("phan-trang")
    public List<NhanVienRespose> phanTrang(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
//        required = false : kh cần điền gtri page, defaultValue = "0": mặc định page là trang đầu tiên

        Pageable pageable = PageRequest.of(page, 2);
        return nhanVienService.listNhanVienResponse(pageable).getContent(); // getContent(): tra ve 1 list
    }

/*
    //   C2: NhanVienRequest: add
    @PostMapping("add")
    public String add(@RequestBody @Valid NhanVienRequest nhanVienRequest, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            return "Them that bai";
        }

        NhanVien nhanVien = new NhanVien();
        nhanVien.setMa(nhanVienRequest.getMa());
        nhanVien.setTen(nhanVienRequest.getTen());
        nhanVien.setTenDem(nhanVienRequest.getTenDem());
        nhanVien.setHo(nhanVienRequest.getHo());
        nhanVien.setGioiTinh(nhanVienRequest.getGioiTinh());
        nhanVien.setNgaySinh(nhanVienRequest.getNgaySinh());
        nhanVien.setDiaChi(nhanVienRequest.getDiaChi());
        nhanVien.setSdt(nhanVienRequest.getSdt());
        nhanVien.setMatKhau(nhanVienRequest.getMatKhau());
        nhanVien.setTrangThai(nhanVienRequest.getTrangThai());
        nhanVien.setChucVu(chucVuService.findById(nhanVienRequest.getIdChucVu()).get());
        // idChucVu: value

        nhanVienService.save(nhanVien);
        return "Them thanh cong";
    }

 */
}
