package com.example.book_be.controller;

import com.example.book_be.dao.NguoiDungRepository;
import com.example.book_be.entity.NguoiDung;
import com.example.book_be.entity.SuDanhGia;
import com.example.book_be.services.review.DanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/danh-gia")
@CrossOrigin(origins = "http://localhost:3000")
public class DanhGiaController {
    @Autowired
    private DanhGiaService danhGiaService;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @PostMapping("/them-danh-gia/{maSach}")
    public SuDanhGia addReview(@PathVariable Long maSach,@RequestBody SuDanhGia danhGia) {
        if (danhGia.getNguoiDung() == null || danhGia.getNguoiDung().getMaNguoiDung() == 0) {
            throw new IllegalArgumentException("MaNguoiDung không hợp lệ hoặc không tồn tại.");
        }
        NguoiDung nguoiDung = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName() != null) {
            nguoiDung = nguoiDungRepository.findByTenDangNhap(authentication.getName());
        }
        NguoiDung finalNguoiDung = nguoiDung;
        return danhGiaService.addReview(
                danhGia.getNhanXet(),
                danhGia.getDiemXepHang(),
                (long) finalNguoiDung.getMaNguoiDung(),
                maSach
        );
    }

    @PostMapping("/sua-danh-gia/{maDanhGia}")
    public SuDanhGia updateReview(@PathVariable Long maDanhGia, @RequestBody SuDanhGia danhGia) {

        return danhGiaService.updateReview(
                maDanhGia,danhGia
        );
    }

    @PostMapping("/xoa-danh-gia/{maDanhGia}")
    public SuDanhGia deleteReview(@PathVariable Long maDanhGia) {
        return danhGiaService.deleteReview(
                maDanhGia
        );
    }

}
