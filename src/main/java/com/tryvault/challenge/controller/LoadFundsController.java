package com.tryvault.challenge.controller;

import com.tryvault.challenge.exception.RepeatedIdException;
import com.tryvault.challenge.model.dto.TransactionDTO;
import com.tryvault.challenge.model.dto.TransactionResultDTO;
import com.tryvault.challenge.service.LoadFundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadFundsController {

    private final LoadFundsService loadFundsService;

    @Autowired
    LoadFundsController(LoadFundsService loadFundsService) {
        this.loadFundsService = loadFundsService;
    }

    @PostMapping("/funds/load")
    TransactionResultDTO execute(@RequestBody TransactionDTO transactionDTO) throws RepeatedIdException {
        return loadFundsService.execute(transactionDTO);
    }

    @ExceptionHandler({RepeatedIdException.class})
    public ResponseEntity<Object> handleException() {
        return ResponseEntity.noContent().build();
    }
}
