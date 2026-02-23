package com.revpay.service.interfaces;

import com.revpay.entity.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    void createInvoice(String customerEmail, Double amount, String description, LocalDate dueDate);

    List<Invoice> myReceivedInvoices();

    void payInvoice(Long invoiceId);
}
