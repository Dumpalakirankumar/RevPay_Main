package com.revpay.controller.wallet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.revpay.dto.request.AddMoneyRequest;
import com.revpay.dto.request.CardDepositRequest;
import com.revpay.dto.request.SendMoneyRequest;
import com.revpay.entity.Wallet;
import com.revpay.service.interfaces.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance() {
        Wallet wallet = walletService.getWalletOfCurrentUser();

        return ResponseEntity.ok(
                Map.of(
                        "balance", wallet.getBalance(),
                        "status", wallet.getWalletStatus()
                )
        );
    }

  
    @PostMapping("/add-money")
    public ResponseEntity<?> addMoney(@RequestBody AddMoneyRequest request) {

        walletService.addMoney(request.getAmount());

        Wallet wallet = walletService.getWalletOfCurrentUser();

        return ResponseEntity.ok(
                Map.of(
                        "message", "Money added successfully",
                        "newBalance", wallet.getBalance()
                )
        );
    }

 
    @PostMapping("/send")
    public Map<String, Object> sendMoney(@RequestBody SendMoneyRequest request) {

        walletService.sendMoney(
                request.getReceiverEmail(),
                request.getAmount(),
                request.getRemark()
        );

        return Map.of("message", "Transfer successful");
    }
    
    @PostMapping("/deposit-card")
    public String depositViaCard(@RequestBody CardDepositRequest request) {

        walletService.addMoneyViaCard(
                request.getCardId(),
                request.getAmount()
        );

        return "Money added using card";
    }
}