package com.revpay.service.interfaces;

import com.revpay.entity.User;
import com.revpay.entity.Wallet;

public interface WalletService {

    void createWalletForUser(User user);

    Wallet getCurrentUserWallet();

    Wallet getWalletOfCurrentUser();

 
    void addMoney(Double amount);

    Wallet getMyWallet();
    
    void sendMoney(String receiverEmail, Double amount, String remark);
    
    void addMoneyViaCard(Long cardId, Double amount);
}