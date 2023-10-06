package com.reza.accounting.controller;

import com.reza.accounting.model.Account;
import com.reza.accounting.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("{id}")
   public ResponseEntity<Account> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
