package com.yennth.demo.demo.B3_LocSapxep_Stream.controller;

import com.yennth.demo.demo.B3_LocSapxep_Stream.entity.Sach;
import com.yennth.demo.demo.B3_LocSapxep_Stream.repository.INhaXuatBan;
import com.yennth.demo.demo.B3_LocSapxep_Stream.repository.ISach;
import com.yennth.demo.demo.B3_LocSapxep_Stream.request.SachRequest;
import com.yennth.demo.demo.B3_LocSapxep_Stream.response.SachRespose;
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

@RestController
@RequestMapping("/B3/sach/")
public class B3_SachController {

    @Autowired
    private ISach sachService;

    @Autowired
    private INhaXuatBan nhaXuatBanService;

    @GetMapping("hien-thi")
    public List<SachRespose> getAll() {
        return sachService.listSachRespose();
    }

    @GetMapping("phan-trang")
    public List<SachRespose> phanTrang(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 2);
        return sachService.listSachRespose(pageable);
    }

    @GetMapping("detail")
    public Sach detail(@RequestParam Integer id) {
        return sachService.findById(id).get();
    }

    @DeleteMapping("delete/{ma}")
    public String delete(@PathVariable("ma") String ma) {
        sachService.deleteByMa(ma);
        return "Xoa thanh cong id " + ma;
    }

    @PutMapping("update")
    public Sach update(@RequestBody Sach sach) {
        return sachService.save(sach);
    }

    @PostMapping("add")
    public String add(@RequestBody @Valid SachRequest sachRequest, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getFieldError());
            return "Error validation!";
        }

        Sach sach = new Sach();
        sach.setMa(sachRequest.getMa());
        sach.setTen(sachRequest.getTen());
        sach.setNgayXuatBan(sachRequest.getNgayXuatBan());
        sach.setSoTrang(sachRequest.getSoTrang());
        sach.setDonGia(sachRequest.getDonGia());
        sach.setTrangThai(sachRequest.getTrangThai());
        sach.setNhaXuatBan(nhaXuatBanService.findById(sachRequest.getIdNXB()).get());

        sachService.save(sach);
        return "Them thanh cong";
    }

    // Loc ten sach "NGUYEN VAN AN"
    @GetMapping("loc-ten-sach")
    public List<SachRespose> locTenSach() {
        return sachService.listSachRespose().stream()
                .filter(item -> item.getTen().equalsIgnoreCase("1"))
                .toList();
    }

    //    Sap xep don gia giam dan
    @GetMapping("sap-xep")
    public List<SachRespose> sapXepGiamDan() {
        return sachService.listSachRespose().stream()
                .sorted(Comparator.comparing(SachRespose::getDonGia).reversed())
                .toList();
    }

    // Loc ten co chu "a" va don gia trong khoang 100-200
    @GetMapping("loc-sach")
    public List<SachRespose> locSach() {
        return sachService.listSachRespose().stream()
                .filter(item -> item.getTen().equalsIgnoreCase("a"))
                .filter(item -> item.getSoTrang() >= 160 && item.getSoTrang() <= 200)
                .toList();
    }
}
