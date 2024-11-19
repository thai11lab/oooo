import React, { useState, useEffect } from 'react';
import SachModel from '../../../../models/SachModel';
import { getAllBook, xoaSach } from '../../../../api/SachApi';
import { Link, useNavigate } from 'react-router-dom';
import { PhanTrang } from '../../../utils/PhanTrang';
import NguoiDungModel from "../../../../models/NguoiDungModel";
import {findAll} from "../../../../api/UserApi";

export default function UserComponent() {
  const [userList, setUserList] = useState<NguoiDungModel[]>([]);
  const [dangTaiDuLieu, setDangTaiDuLieu] = useState(true);
  const [baoLoi, setBaoLoi] = useState<string | null>(null);
  const [trangHienTai, setTrangHienTai] = useState(1);
  const [tongSoTrang, setTongSoTrang] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    findAll(trangHienTai - 1)
        .then((kq) => {
          setUserList(kq.ketQua);
          setTongSoTrang(kq.tongSoTrang);
          setDangTaiDuLieu(false);
        })
        .catch((error) => {
          setBaoLoi(error.message);
          setDangTaiDuLieu(false);
        });
  }, [trangHienTai]);

  const phanTrang = (trang: number) => setTrangHienTai(trang);



  const handleAdd = () => {
    try {
      navigate(`/quan-ly/them-sach`);
    } catch (error) {
      setBaoLoi('Có lỗi khi chuyển đến trang cập nhật');
    }
  };



  if (dangTaiDuLieu) {
    return <div>Đang tải dữ liệu...</div>;
  }

  if (baoLoi) {
    return <div>Có lỗi xảy ra: {baoLoi}</div>;
  }

  return (
    <div className="container-fluid px-4">
      <h1 className="mt-4">Quản lý user</h1>
      <ol className="breadcrumb mb-4">
        <li className="breadcrumb-item"><Link to="/danh-sach-nguoi-dung">Dashboard</Link></li>
        <li className="breadcrumb-item active">Danh sách user</li>
      </ol>
      <div className="card mb-4">
        <div className="card-header">
          <i className="fas fa-table me-1"></i>
          Danh sách user
        </div>
        <div className="card-body">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Mã người dùng</th>
                <th>Họ đệm</th>
                <th>Tên</th>
                <th>Email</th>
                <th>Số điện thoại</th>
              </tr>
            </thead>
            <tbody>
              {userList.map((user) => (
                <tr key={user.maNguoiDung}>
                  <td>{user.maNguoiDung}</td>
                  <td>{user.hoDem} </td>
                  <td>{user.ten}</td>
                  <td>{user.email}</td>
                  <td>{user.soDienThoai}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <PhanTrang 
        trangHienTai={trangHienTai}
        tongSoTrang={tongSoTrang}
        phanTrang={phanTrang}
      />
    </div>
  );
}

export {}; 