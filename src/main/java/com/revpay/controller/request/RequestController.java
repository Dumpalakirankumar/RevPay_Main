package com.revpay.controller.request;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revpay.entity.MoneyRequest;
import com.revpay.service.interfaces.RequestService;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired private RequestService requestService;

    @PostMapping("/create")
    public String create(@RequestBody Map<String,Object> body) {

        requestService.createRequest(
                body.get("email").toString(),
                Double.valueOf(body.get("amount").toString()),
                body.get("note").toString()
        );
        return "Request sent";
    }

    @GetMapping("/incoming")
    public List<MoneyRequest> incoming() {
        return requestService.myIncomingRequests();
    }

    @PostMapping("/accept/{id}")
    public String accept(@PathVariable Long id) {
        requestService.acceptRequest(id);
        return "Request accepted";
    }

    @PostMapping("/decline/{id}")
    public String decline(@PathVariable Long id) {
        requestService.declineRequest(id);
        return "Request declined";
    }
}