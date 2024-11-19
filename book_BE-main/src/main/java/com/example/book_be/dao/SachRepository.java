package com.example.book_be.dao;

import com.example.book_be.entity.HinhAnh;
import com.example.book_be.entity.Sach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestResource(path = "sach")
public interface SachRepository extends JpaRepository<Sach, Long>, JpaSpecificationExecutor {
    Page<Sach> findByTenSachContaining(@RequestParam("tensach") String tenSach, Pageable pageable);
    Page<Sach> findByListTheLoai_MaTheLoai(@RequestParam("maTheLoai") int maTheLoai, Pageable pageable);
    Page<Sach> findByTenSachContainingAndListTheLoai_MaTheLoai(@RequestParam("tensach") String tenSach,@RequestParam("maTheLoai") int maTheLoai, Pageable pageable);

}
