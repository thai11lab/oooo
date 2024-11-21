package com.example.book_be.controller;

import com.example.book_be.entity.DonHang;
import com.example.book_be.entity.Sach;
import com.example.book_be.services.VNPayService;
import com.example.book_be.services.cart.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/don-hang")
public class DonHangController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/them")
    public DonHang add(@RequestBody List<Sach> sachList) {
        DonHang donHang = null;
        try {
            donHang = orderService.saveOrUpdate(sachList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return donHang;
    }

    @GetMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {
        String baseUrl = "";
        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        return vnpayUrl;
    }

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}
