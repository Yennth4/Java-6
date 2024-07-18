package com.yennth.demo.demo.B5_CRUDCMS.controller;

import com.yennth.demo.demo.B5_CRUDCMS.entity.B5_NXB;
import com.yennth.demo.demo.B5_CRUDCMS.entity.B5_Sach;
import com.yennth.demo.demo.B5_CRUDCMS.repository.B5_NXBRepository;
import com.yennth.demo.demo.B5_CRUDCMS.repository.B5_SachRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sach")
public class SachController {

    @Autowired
    B5_SachRepository sachRepo;

    @Autowired
    B5_NXBRepository nxbRepo;

    @GetMapping("/hien-thi")
    public List<B5_Sach> getAll() {
        return sachRepo.findAll();
    }

    @GetMapping("/page")
    public Page<B5_Sach> sachPage(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return sachRepo.findAll(pageable);
    }

    @PostMapping("/add")
    public ResponseEntity<?> store(@Valid B5_Sach sach, BindingResult result, @RequestParam(required = false) Integer idNXB) {
        if (idNXB != null) {
            boolean nxbNull = true;
            for (B5_NXB nxbCheck : nxbRepo.findAll()) {
                if (nxbCheck.getId().equals(idNXB)) {
                    sach.setNhaXuatBan(nxbRepo.findById(idNXB).get());
                    nxbNull = false;
                    break;
                }
            }

            if (nxbNull) {
                return ResponseEntity.badRequest().body("NXB not found");
            }
        }

        if (result.hasErrors()) {
            StringBuilder mess = new StringBuilder();
            result.getFieldErrors().forEach(error ->
                    mess.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(".").append("\n"));
            return ResponseEntity.badRequest().body(mess.toString());
        }

        String newMa = sach.getMa();
        if (!checkFormat(newMa, "^S\\d{3}$")) {
            return ResponseEntity.badRequest().body("Mã không đúng định dạng");
        }

        for (B5_Sach sachCheck : sachRepo.findAll()) {
            if (newMa.equals(sachCheck.getMa())) {
                return ResponseEntity.badRequest().body("Mã trùng không thể thêm");
            }
        }
        return ResponseEntity.ok("Thêm thành công");

    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> delete(@RequestParam(required = false) Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("Phải truyền id sách để xóa");
        } else {
            Optional<B5_Sach> optionalSach = sachRepo.findById(id);
            if (optionalSach.isPresent()) {
                sachRepo.deleteById(id);
                return ResponseEntity.ok("Xóa thành công");
            } else {
                return ResponseEntity.badRequest().body("Id sach not found");
            }
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detail(@RequestParam Integer id) {
        return ResponseEntity.ok().body(sachRepo.getById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(B5_Sach sach) {
        if (sach.getId() == null) {
            return ResponseEntity.badRequest().body("Không sửa được vì thiếu id");
        }
        return ResponseEntity.ok(sachRepo.save(sach));
    }

    public static boolean checkFormat(String input, String pattern) {
        return input.matches(pattern);
    }
}
