package com.revpay.service.interfaces;

import java.util.List;

import com.revpay.entity.MoneyRequest;

public interface RequestService {

    void createRequest(String senderEmail, Double amount, String note);

    void acceptRequest(Long requestId);

    void declineRequest(Long requestId);

    List<MoneyRequest> myIncomingRequests();
}