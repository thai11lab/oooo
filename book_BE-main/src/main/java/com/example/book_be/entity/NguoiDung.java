package com.example.book_be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nguoi_dung")
    private int maNguoiDung;
    @Column(name = "ho_dem", length = 256)
    private String hoDem;
    @Column(name = "ten", length = 256)
    private String ten;
    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;
    @Column(name = "mat_khau", length = 512)
    private String matKhau;
    @Column(name = "gioi_tinh")
    private char gioiTinh;
    @Column(name = "email")
    private String email;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @Column(name = "dia_chi_mua_hang")
    private String diaChiMuaHang;
    @Column(name = "dia_chi_giao_hang")
    private String diaChiGiaoHang;
    @Column(name = "da_kich_hoat", nullable = false)
    private Boolean daKichHoat = false;
    @Column(name = "ma_kich_hoat")
    private String maKichHoat;
    //    private String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<SuDanhGia> danhSachSuDanhGia;
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<SachYeuThich> danhSachSachYeuThich;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "nguoidung_quyen", joinColumns = @JoinColumn(name = "ma_nguoi_dung"), inverseJoinColumns = @JoinColumn(name = "ma_quyen"))
    private List<Quyen> danhSachQuyen;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<DonHang> danhSachDonhang;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GioHang> danhSachGioHang;

    @Override
    public String toString() {
        return "NguoiDung{" +
                "maNguoiDung=" + maNguoiDung +
                ", hoDem='" + hoDem + '\'' +
                ", ten='" + ten + '\'' +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", email='" + email + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChiMuaHang='" + diaChiMuaHang + '\'' +
                ", diaChiGiaoHang='" + diaChiGiaoHang + '\'' +
                ", daKichHoat=" + daKichHoat +
                ", maKichHoat='" + maKichHoat + '\'' +
                ", danhSachSuDanhGia=" + danhSachSuDanhGia +
                ", danhSachSachYeuThich=" + danhSachSachYeuThich +
                ", danhSachQuyen=" + danhSachQuyen +
                ", danhSachDonhang=" + danhSachDonhang +
                '}';
    }
}
