package com.revpay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.revpay.dto.response.PageResponse;
import com.revpay.entity.Transaction;
import com.revpay.entity.Wallet;
import com.revpay.repository.TransactionRepository;
import com.revpay.service.interfaces.TransactionService;
import com.revpay.service.interfaces.WalletService;
import java.time.LocalDateTime;
import java.time.LocalDate;


@Service
public class TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public PageResponse<?> myTransactions(int page, int size) {

	    Wallet wallet = walletService.getMyWallet();

	    Pageable pageable = PageRequest.of(page, size);

	    Page<Transaction> transactionPage =
	            transactionRepository.findByWalletOrderByCreatedAtDesc(wallet, pageable);

	    return new PageResponse<>(
	            transactionPage.getContent(),
	            transactionPage.getNumber(),
	            transactionPage.getTotalPages(),
	            transactionPage.getTotalElements()
	    );
	}
	
	@Override
	public PageResponse<?> searchTransactions(
	        int page,
	        int size,
	        String type,
	        String from,
	        String to,
	        String sort) {

	    Wallet wallet = walletService.getMyWallet();

	    // sorting
	    Sort sorting = Sort.by("createdAt").descending();
	    if (sort != null) {
	        String[] parts = sort.split(",");
	        sorting = Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);
	    }

	    Pageable pageable = PageRequest.of(page, size, sorting);

	    // date parsing
	    LocalDateTime fromDate = null;
	    LocalDateTime toDate = null;

	    if (from != null)
	        fromDate = LocalDate.parse(from).atStartOfDay();

	    if (to != null)
	        toDate = LocalDate.parse(to).atTime(23,59,59);

	    Page<Transaction> result =
	            transactionRepository.searchTransactions(
	                    wallet, type, fromDate, toDate, pageable
	            );

	    return new PageResponse<>(
	            result.getContent(),
	            result.getNumber(),
	            result.getTotalPages(),
	            result.getTotalElements()
	    );
	}

}