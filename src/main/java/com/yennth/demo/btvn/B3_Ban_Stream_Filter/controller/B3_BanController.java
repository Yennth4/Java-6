package com.yennth.demo.btvn.B3_Ban_Stream_Filter.controller;

import com.yennth.demo.btvn.B3_Ban_Stream_Filter.entity.B3_Ban;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.repository.B3_IBan;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.repository.B3_IMQH;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.request.B3_BanRequest;
import com.yennth.demo.btvn.B3_Ban_Stream_Filter.response.B3_BanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("add")
    public String add(B3_BanRequest banRequest) {
        B3_Ban ban = new B3_Ban();
        ban.setMa(banRequest.getMa());
        ban.setTen(banRequest.getTen());
        ban.setNgaySinh(banRequest.getNgaySinh());
        ban.setSoThich(banRequest.getSoThich());
        ban.setGioiTinh(banRequest.getGioiTinh());
        ban.setTrangThai(banRequest.getTrangThai());
        ban.setMqh(mqhService.findById(banRequest.getIdMQH()).get());

        banService.save(ban);
        return "Them thanh cong";
    }
}
