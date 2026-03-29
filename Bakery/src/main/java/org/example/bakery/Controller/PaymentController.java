package org.example.bakery.Controller;

import org.example.bakery.DTO.OrderResponseDTO;
import org.example.bakery.DTO.PaymentDTO;
import org.example.bakery.Service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    @PostMapping("/pay")
    public String pay(@RequestBody PaymentDTO dto){
        return paymentService.pay(dto);
    }
    @GetMapping("/paid-list")
    public List<OrderResponseDTO> getPaidOrders(){
        return paymentService.getPaidOrders();
    }
}
