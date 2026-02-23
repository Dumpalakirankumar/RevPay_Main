package com.revpay.service.interfaces;

import com.revpay.dto.response.PageResponse;

public interface TransactionService {

	PageResponse<?> myTransactions(int page, int size);
	
	PageResponse<?> searchTransactions(
	        int page,
	        int size,
	        String type,
	        String from,
	        String to,
	        String sort
	);
}