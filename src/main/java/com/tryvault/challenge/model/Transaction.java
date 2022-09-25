package com.tryvault.challenge.model;

import com.tryvault.challenge.model.dto.TransactionDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    @Id
    public Long id;
    public Integer customerId;
    public BigDecimal loadAmount;
    public ZonedDateTime time;
    public boolean accepted;

    public static Transaction fromTransactionDTO(TransactionDTO dto) {
        BigDecimal amount = new BigDecimal(dto.getLoadAmount().substring(1));
        ZonedDateTime date = ZonedDateTime.parse(dto.getTime()).withZoneSameInstant(ZoneId.of("UTC"));
        return new Transaction(dto.getId(), Integer.parseInt(dto.getCustomerId()), amount, date, false);
    }
}
