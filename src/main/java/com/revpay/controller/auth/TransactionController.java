package com.revpay.controller.auth;

import com.revpay.entity.Transaction;
import com.revpay.entity.Wallet;
import com.revpay.service.interfaces.TransactionService;
import com.revpay.service.interfaces.WalletService;
<<<<<<< HEAD

import org.springframework.beans.factory.annotation.Autowired;
=======
import com.revpay.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
>>>>>>> 23cbf15 (Transactions with Pagination and Sorting)
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@PreAuthorize("isAuthenticated()")
public class TransactionController {

    @Autowired
    private WalletService walletService;
    
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
<<<<<<< HEAD
    public List<Transaction> history() {

     
        Wallet wallet = walletService.getWalletOfCurrentUser();

        return transactionRepository.findByWalletOrderByCreatedAtDesc(wallet);
=======
    public ApiResponse<?> transactions(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="5") int size,
            @RequestParam(required=false) String type,
            @RequestParam(required=false) String from,
            @RequestParam(required=false) String to,
            @RequestParam(required=false) String sort
    ) {

        return new ApiResponse<>(
                true,
                "Transactions fetched",
                transactionService.searchTransactions(page,size,type,from,to,sort)
        );
>>>>>>> 23cbf15 (Transactions with Pagination and Sorting)
    }
}