package com.tryvault.challenge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tryvault.challenge.exception.RepeatedIdException;
import com.tryvault.challenge.model.dto.TransactionDTO;
import com.tryvault.challenge.model.dto.TransactionResultDTO;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Component
public class ChallengeSolver {

    @Autowired
    LoadFundsService service;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws JsonProcessingException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/test/resources/request/input");
        List<String> lines = FileUtil.readAsLines(file);
        for (String line: lines) {
            TransactionDTO transaction = mapper.readValue(line, TransactionDTO.class);
            TransactionResultDTO result;
            try {
                result = service.execute(transaction);
            } catch (RepeatedIdException e) {
                continue;
            }
            System.out.printf("%s%n", mapper.writeValueAsString(result));
        }
    }

}
