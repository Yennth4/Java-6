package com.yennth.demo.btvn.B3_Ban_Stream_Filter.controller;

import com.yennth.demo.btvn.B3_Ban_Stream_Filter.entity.B3_Ban;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.repository.B3_IBan;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.repository.B3_IMQH;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.request.B3_BanRequest;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.response.B3_BanResponse;
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
@RequestMapping("/de3/ban/")
public class B3_BanController {

    @Autowired
    private B3_IBan banService;

    @Autowired
    private B3_IMQH mqhService;

    @GetMapping("hien-thi")
    public List<B3_BanResponse> getAll(){
        return banService.listBanResponse();
    };

    @GetMapping("phan-trang")
    public List<B3_BanResponse> phanTrang(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page){
        Pageable pageable = PageRequest.of(page, 2);
        return banService.listBanResponse(pageable);
    };

    @GetMapping("detail/{id}")
    public B3_Ban detail(@PathVariable Integer id){
        return banService.findById(id).get();
    }

    @DeleteMapping("delete/{ma}")
    public String delete(@PathVariable String ma){
        banService.deleteByMa(ma);
        return "Delete success ma " + ma;
    }

    // check trùng mã khi add
    // check mã đúng định dạng ví dụ: B001
    @PostMapping("add")
    public String add(@RequestBody @Valid B3_BanRequest banRequest, BindingResult result) {

        if (result.hasErrors()) {
            return ("Error: " + result.getFieldError().getDefaultMessage());
        }

        String ma = banRequest.getMa();
        if (!Pattern.matches("^B\\d{3}$" , ma)) {
            return "Them that bai: Ma khong dung dinh dang B001";
        }

        B3_Ban exisMa = banService.findByMa(ma);
        if (exisMa != null && !exisMa.getId().equals(banRequest.getId())) {
            return "Them that bai: Ma da ton tai";
        }

        B3_Ban ban = new B3_Ban();
        ban.setMa(banRequest.getMa());
        ban.setTen(banRequest.getTen());
        ban.setNgaySinh(banRequest.getNgaySinh());
        ban.setSoThich(banRequest.getSoThich());
        ban.setGioiTinh(banRequest.getGioiTinh());
        ban.setTrangThai(banRequest.getTrangThai());
        ban.setMqh(mqhService.findById(banRequest.getIdMQH()).get());

        banService.save(ban);
        return "Them thanh cong id " + ban.getId();
    }

    @PutMapping("update")
    public String update(@RequestBody B3_BanRequest banRequest) {
        B3_Ban ban = new B3_Ban();
        ban.setId(banRequest.getId());
        ban.setMa(banRequest.getMa());
        ban.setTen(banRequest.getTen());
        ban.setNgaySinh(banRequest.getNgaySinh());
        ban.setSoThich(banRequest.getSoThich());
        ban.setGioiTinh(banRequest.getGioiTinh());
        ban.setTrangThai(banRequest.getTrangThai());
        ban.setMqh(mqhService.findById(banRequest.getIdMQH()).get());

        banService.save(ban);
        return "Update thanh cong ma " + ban.getMa();
    }

    /** Sử dụng Stream để xử lý danh sách Ban và thực hiện các tác vụ sau:
            * Lọc ra danh sách Ban có tên mối quan hệ là “Bạn bè"
            * Sắp xếp danh sách Ban có đơn giá giảm dần theo năm sinh
            * Lọc ra danh sách Ban có tên mối quan hệ chứa chữ “a" và có năm sinh từ 2004 - 2010
    */

    @GetMapping("loc-ten-mqh")
    public List<B3_BanResponse> listLocTenMoiQuanHe(){
        return banService.listBanResponse().stream()
                .filter(item -> item.getTenMQH().equalsIgnoreCase("c"))
                .toList();
    };

    @GetMapping("sap-xep")
    public List<B3_BanResponse> listNamSinhGiamDan(){
        return banService.listBanResponse().stream()
                .sorted(Comparator.comparing((B3_BanResponse b) -> {
                    return b.getNgaySinh().getYear();
                }).reversed())
                .toList();
    };

    @GetMapping("loc-ten-and-nam-sinh")
    public List<B3_BanResponse> listLocTenMoiQuanHeAndNamSinh(){
        return banService.listBanResponse().stream()
                .filter(item -> item.getTenMQH().equalsIgnoreCase("c"))
                .filter(item -> item.getNgaySinh().getYear() > 2004 && item.getNgaySinh().getYear() < 2010)
                .toList();
    };
}
