import React from 'react';
import { Routes, Route } from 'react-router-dom';
import AdminSidebar from '../components/AdminSidebar';
import SachForm_Admin from '../components/book/SachForm';
import DanhSachSach from '../components/book/DanhSachSach';
import CapNhatSach from "../components/book/CapNhatSach";
import UserComponent from "../components/user";

const AdminLayout: React.FC = () => {
  return (
    <div className="d-flex">
      <AdminSidebar />
      <main className="flex-grow-1 p-4" style={{marginLeft: '250px'}}>
        <Routes>
            <Route path="dashboard" element={<div>Dashboard</div>} />
            <Route path="/danh-sach-sach" element={<DanhSachSach />} />
            <Route path="/them-sach" element={<SachForm_Admin />} />
            <Route path="/cap-nhat-sach/:maSach" element={<CapNhatSach />} />
            <Route path="/danh-sach-nguoi-dung" element={<UserComponent />} />
        </Routes>
      </main>
    </div>
  );
};

export default AdminLayout;