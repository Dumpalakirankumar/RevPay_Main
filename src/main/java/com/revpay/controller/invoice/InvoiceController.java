package com.revpay.controller.invoice;

import com.revpay.entity.Invoice;
import com.revpay.service.interfaces.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/create")
    public String create(@RequestBody Map<String,String> body) {

        invoiceService.createInvoice(
                body.get("customerEmail"),
                Double.valueOf(body.get("amount")),
                body.get("description"),
                LocalDate.parse(body.get("dueDate"))
        );

        return "Invoice created";
    }

    @GetMapping("/received")
    public List<Invoice> received() {
        return invoiceService.myReceivedInvoices();
    }

    @PostMapping("/pay/{id}")
    public String pay(@PathVariable Long id) {
        invoiceService.payInvoice(id);
        return "Invoice paid";
    }
}