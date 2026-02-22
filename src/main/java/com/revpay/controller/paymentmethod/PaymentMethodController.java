package com.revpay.controller.paymentmethod;

import com.revpay.entity.PaymentMethod;
import com.revpay.service.interfaces.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cards")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/add")
    public String addCard(@RequestBody Map<String,String> body) {

        paymentMethodService.addCard(
                body.get("number"),
                body.get("holder"),
                body.get("expiry"),
                body.get("cvv")
        );

        return "Card added successfully";
    }

    @GetMapping
    public List<PaymentMethod> myCards() {
        return paymentMethodService.myCards();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        paymentMethodService.deleteCard(id);
        return "Card removed";
    }
}