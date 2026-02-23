package com.revpay.service.impl;

import com.revpay.entity.PaymentMethod;
import com.revpay.entity.User;
import com.revpay.repository.PaymentMethodRepository;
import com.revpay.service.interfaces.PaymentMethodService;
import com.revpay.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private UserService userService;

    @Override
    public void addCard(String number, String holder, String expiry, String cvv) {

        User user = userService.getCurrentUser();

        PaymentMethod card = new PaymentMethod();
        card.setUser(user);
        card.setCardNumber(number);
        card.setCardHolderName(holder);
        card.setExpiry(expiry);
        card.setCvv(cvv);
        card.setDefault(false);
        card.setCreatedAt(LocalDateTime.now());

        paymentMethodRepository.save(card);
    }

    @Override
    public List<PaymentMethod> myCards() {
        return paymentMethodRepository.findByUser(userService.getCurrentUser());
    }

    @Override
    public void deleteCard(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}