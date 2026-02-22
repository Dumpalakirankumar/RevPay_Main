package com.revpay.repository;

import com.revpay.entity.Invoice;
import com.revpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCustomer(User customer);
    List<Invoice> findByBusiness(User business);
}
