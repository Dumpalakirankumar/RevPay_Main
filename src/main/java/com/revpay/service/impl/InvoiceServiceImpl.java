package com.revpay.service.impl;

import com.revpay.entity.Invoice;
import com.revpay.entity.User;
import com.revpay.repository.InvoiceRepository;
import com.revpay.repository.UserRepository;
import com.revpay.service.interfaces.InvoiceService;
import com.revpay.service.interfaces.UserService;
import com.revpay.service.interfaces.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired private InvoiceRepository invoiceRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private WalletService walletService;

    @Override
    public void createInvoice(String customerEmail, Double amount, String description, LocalDate dueDate) {

        User business = userService.getCurrentUser();
        User customer = userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Invoice invoice = new Invoice();
        invoice.setBusiness(business);
        invoice.setCustomer(customer);
        invoice.setAmount(amount);
        invoice.setDescription(description);
        invoice.setStatus("PENDING");
        invoice.setDueDate(dueDate);
        invoice.setCreatedAt(LocalDateTime.now());

        invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> myReceivedInvoices() {
        return invoiceRepository.findByCustomer(userService.getCurrentUser());
    }

    @Override
    @Transactional
    public void payInvoice(Long invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        if (!invoice.getStatus().equals("PENDING"))
            throw new RuntimeException("Invoice already processed");

        // transfer money customer -> business
        walletService.sendMoney(
                invoice.getBusiness().getEmail(),
                invoice.getAmount(),
                "Invoice Payment #" + invoiceId
        );

        invoice.setStatus("PAID");
        invoiceRepository.save(invoice);
    }
}
