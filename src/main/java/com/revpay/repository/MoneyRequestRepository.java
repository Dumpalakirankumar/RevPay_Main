package com.revpay.repository;

import com.revpay.entity.MoneyRequest;
import com.revpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MoneyRequestRepository extends JpaRepository<MoneyRequest, Long> {

    List<MoneyRequest> findBySenderAndStatus(User sender, String status);
    List<MoneyRequest> findByReceiver(User receiver);
}