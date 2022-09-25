package com.tryvault.challenge.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tryvault.challenge.exception.RepeatedIdException;
import com.tryvault.challenge.model.dto.TransactionDTO;
import com.tryvault.challenge.model.dto.TransactionResultDTO;
import com.tryvault.challenge.service.LoadFundsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.TimeZone;

import static com.tryvault.challenge.TestUtils.readFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    LoadFundsService service;

    @Test
    public void run() throws JsonProcessingException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ObjectMapper mapper = new ObjectMapper();
        List<TransactionDTO> transactionDTOS = readFile("src/test/resources/request/input", TransactionDTO.class);
        List<TransactionResultDTO> expectedResults = readFile("src/test/resources/result/output", TransactionResultDTO.class);
        int offset = 0; // a quick hack to avoid repeated ignored transactions

        for (int i = 0; i < transactionDTOS.size(); i++) {
            TransactionDTO transactionDTO = transactionDTOS.get(i);
            TransactionResultDTO result;
            try {
                result = service.execute(transactionDTO);
            } catch (RepeatedIdException e) {
                offset += 1;
                continue;
            }
            System.out.printf("%s%n", mapper.writeValueAsString(result));
            assertEquals(expectedResults.get(i - offset), result);
        }
    }
}
