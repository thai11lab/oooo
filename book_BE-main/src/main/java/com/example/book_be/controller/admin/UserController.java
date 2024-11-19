package com.example.book_be.controller.admin;

import com.example.book_be.bo.UserBo;
import com.example.book_be.entity.NguoiDung;
import com.example.book_be.services.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("api/admin/user")
public class UserController {
    @Autowired
    private AdminUserService adminUserService;

    @GetMapping
    public ResponseEntity<Page<NguoiDung>> findAll(@RequestParam("page") Integer page) {
        UserBo model = new UserBo();
        model.setPage(page);
        model.setPageSize(10);
        Page<NguoiDung> result = adminUserService.findAll(model); // or pass multiple params if needed
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
