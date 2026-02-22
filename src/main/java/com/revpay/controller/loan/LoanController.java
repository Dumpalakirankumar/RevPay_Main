package com.revpay.controller.loan;

import com.revpay.entity.Loan;
import com.revpay.service.interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public String apply(@RequestBody Map<String,String> body) {

        loanService.applyLoan(
                Double.valueOf(body.get("amount")),
                Integer.parseInt(body.get("months"))
        );

        return "Loan applied";
    }

    @PostMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {
        loanService.approveLoan(id);
        return "Loan approved & credited";
    }

    @PostMapping("/repay/{id}")
    public String repay(@PathVariable Long id) {
        loanService.repayEmi(id);
        return "EMI paid";
    }

    @GetMapping
    public List<Loan> myLoans() {
        return loanService.myLoans();
    }
}